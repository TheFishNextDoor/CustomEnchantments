package fun.sunrisemc.fishchantments.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import fun.sunrisemc.fishchantments.CustomEnchantment;
import fun.sunrisemc.fishchantments.Settings;
import net.md_5.bungee.api.ChatColor;

public class EnchantUtil {

    public static boolean addEnchant(ItemStack item, Enchantment enchantment, Integer level, boolean force, boolean combine) {
        // Verify
        if (item == null) return false;
        if (level < 1) return false;
        if (level > 255) level = 255;
        int currentLevel = level(item, enchantment);
        if (!force) {
            if (level < currentLevel) return false;
            if (!(enchantment.canEnchantItem(item) || item.getType() == Material.ENCHANTED_BOOK)) return false;
            if (EnchantUtil.hasConflictingEnchantments(item, enchantment)) return false;
        }
    
        // Remove Overridden Enchantments
        if (Settings.REMOVE_OVERRIDDEN_ENCHANTMENTS) {
            if (same(enchantment, CustomEnchantment.UNBREAKABLE)) {
                EnchantUtil.removeEnchant(item, Enchantment.DURABILITY);
                EnchantUtil.removeEnchant(item, Enchantment.MENDING);
            }
            else if (same(enchantment, CustomEnchantment.FIRE_RESISTANCE)) {
                EnchantUtil.removeEnchant(item, Enchantment.PROTECTION_FIRE);
            }
        }
    
        // Fix item
        if (same(enchantment, CustomEnchantment.UNBREAKABLE)) {
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                damageable.setDamage(0);
                item.setItemMeta(meta);
            }
        }
        
        // Add Enchantment
        if (combine && level == currentLevel && currentLevel < enchantment.getMaxLevel()) level++;
        EnchantUtil.removeEnchant(item, enchantment); // Remove old lore
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else item.addUnsafeEnchantment(enchantment, level);
        
        // Add Lore
        if (!EnchantUtil.isCustom(enchantment)) return true;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<String>();
        lore.add(0, lore(enchantment, level));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }

    public static boolean removeEnchant(ItemStack item, Enchantment enchantment) {
        // Remove Enchantment
        if (item == null) return false;
        final boolean HASENCHANT = has(item, enchantment);
        if (HASENCHANT) {
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                meta.removeStoredEnchant(enchantment);
                item.setItemMeta(meta);
            }
            else item.removeEnchantment(enchantment);
        }
        
        // Remove Lore
        if (!EnchantUtil.isCustom(enchantment)) return HASENCHANT;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) return HASENCHANT;
        List<String> newLore = new ArrayList<>();
        String enchantLore = lore(enchantment, 1);
        for (String line : lore) {
            if (!line.contains(enchantLore)) newLore.add(line);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return HASENCHANT;
    }

    public static ArrayList<Enchantment> enchantments(ItemStack item) {
        ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();
        if (item == null) return enchantments;
        if (!item.hasItemMeta()) return enchantments;
        Iterator<Enchantment> iter;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            if (!meta.hasStoredEnchants()) return enchantments;
            iter = meta.getStoredEnchants().keySet().iterator();
        }
        else {
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasEnchants()) return enchantments;
            iter = meta.getEnchants().keySet().iterator();
        }
        while (iter.hasNext()) {
            enchantments.add(iter.next());
        }
        return enchantments;
    }

    public static boolean isCustom(Enchantment enchantment) {
        if (enchantment == null) return false;
        Iterator<Enchantment> fishchantments = CustomEnchantment.all().iterator();
        while (fishchantments.hasNext()) {
            Enchantment fishchantment = fishchantments.next();
            if (same(fishchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean hasCustomEnchantments(ItemStack item) {
        return EnchantUtil.customEnchantments(item).size() > 0;
    }

    public static ArrayList<Enchantment> customEnchantments(ItemStack item) {
        ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
        if (item == null) return foundFishchantments;
        if (!item.hasItemMeta()) return foundFishchantments;
        Iterator<Enchantment> enchantments = enchantments(item).iterator();
        while (enchantments.hasNext()) {
            Enchantment enchantment = enchantments.next();
            if (EnchantUtil.isCustom(enchantment)) foundFishchantments.add(enchantment);
        }
        return foundFishchantments;
    }

    public static boolean sameEnchantments(ItemStack itemA, ItemStack itemB) {
        if (itemA == null || itemB == null) return false;
        if (itemA.getType() != itemB.getType()) return false;
        if (itemA.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta metaA = (EnchantmentStorageMeta) itemA.getItemMeta();
            EnchantmentStorageMeta metaB = (EnchantmentStorageMeta) itemB.getItemMeta();
            if (metaA.getStoredEnchants().size() != metaB.getStoredEnchants().size()) return false;
            Iterator<Enchantment> iter = metaA.getStoredEnchants().keySet().iterator();
            while (iter.hasNext()) {
                Enchantment enchantment = iter.next();
                if (!metaB.hasStoredEnchant(enchantment)) return false;
                if (metaA.getStoredEnchantLevel(enchantment) != metaB.getStoredEnchantLevel(enchantment)) return false;
            }
        }
        else {
            if (itemA.getEnchantments().size() != itemB.getEnchantments().size()) return false;
            Iterator<Enchantment> iter = itemA.getEnchantments().keySet().iterator();
            while (iter.hasNext()) {
                Enchantment enchantment = iter.next();
                if (!itemB.containsEnchantment(enchantment)) return false;
                if (itemA.getEnchantmentLevel(enchantment) != itemB.getEnchantmentLevel(enchantment)) return false;
            }
        }
        return true;
    }

    public static ItemStack enchantedBook(Enchantment enchantment, int level) {
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
        addEnchant(enchantedBook, enchantment, level, true, false);
        return enchantedBook;
    }

    @SuppressWarnings("deprecation")
    public static String lore(Enchantment enchantment, Integer level) {
        if (level < 0) return null;
        String lore = enchantment.isCursed() ? ChatColor.RED + enchantment.getName() : ChatColor.GRAY + enchantment.getName();
        if (level == 1) return lore;
        else return lore + " " + (Settings.USE_ARABIC_NUMERALS ? level.toString() : numeral(level));
    }

    public static boolean hasConflictingEnchantments(ItemStack item, Enchantment enchantment) {
        Iterator<Enchantment> iter = enchantments(item).iterator();
        while (iter.hasNext()) {
            Enchantment ienchantment = iter.next();
            if (same(ienchantment, enchantment)) continue;
            if (enchantment.conflictsWith(ienchantment) || ienchantment.conflictsWith(enchantment)) return true;
        }
        return false;
    }

    @SuppressWarnings("deprecation")
    public static boolean same(Enchantment enchant1, Enchantment enchant2) {
        if (enchant1.getKey().getKey().equals(enchant2.getKey().getKey())) return true;
        String name1 = enchant1.getName(); String name2 = enchant2.getName();
        if (name1 == null || name2 == null) return false;
        if (name1.equalsIgnoreCase(name2)) return true;
        return false;
    }

    public static boolean has(ItemStack item, Enchantment enchant) {
        return level(item, enchant) > 0;
    }

    public static int level(ItemStack item, Enchantment enchant) {
        int level = 0;
        if (item == null) return level;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return level;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) meta;
            if (bookMeta.hasStoredEnchant(enchant)) level = bookMeta.getStoredEnchantLevel(enchant);
        }
        else if (meta.hasEnchant(enchant)) level = meta.getEnchantLevel(enchant);
        if (Settings.CHECK_LORE && level == 0 && meta.hasLore()) {
            String enchantLore = EnchantUtil.lore(enchant, 1);
            Iterator<String> iter = meta.getLore().iterator();
            while (iter.hasNext()) {
                String line = iter.next();
                if (line.equals(enchantLore)) level = 1;
                else if (line.startsWith(enchantLore)) level = number(line.replaceFirst(enchantLore, "").trim());
                if (level != 0) {
                    EnchantUtil.fix(item, enchant, level);
                    break;
                }
            }
        }
        return level;
    }

    public static boolean holding(Player player, Enchantment enchant) {
        return handLevel(player, enchant) > 0;
    }

    public static int handLevel(Player player, Enchantment enchant) {
        return level(InventoryUtil.getItemInUse(player), enchant);
    }

    public static boolean holdingRanged(Player player, Enchantment enchant) {
        return rangedLevel(player, enchant) > 0;
    }

    public static int rangedLevel(Player player, Enchantment enchant) {
        return level(InventoryUtil.getRangedItemInUse(player), enchant);
    }

    public static int weaponLevel(Player player, Enchantment enchant, boolean ranged) {
        if (ranged) return rangedLevel(player, enchant);
        else return handLevel(player, enchant);
    }

    public static boolean holdingHoe(Player player, Enchantment enchant) {
        return hoeLevel(player, enchant) > 0;
    }

    public static int hoeLevel(Player player, Enchantment enchant) {
        return level(InventoryUtil.getHoeInUse(player), enchant);
    }

    public static boolean holdingShield(Player player, Enchantment enchant) {
        return shieldLevel(player, enchant) > 0;
    }

    public static int shieldLevel(Player player, Enchantment enchant) {
        return level(InventoryUtil.getShieldInUse(player), enchant);
    }

    public static boolean wearing(Player player, Enchantment enchant) {
        return armorLevel(player, enchant) > 0;
    }

    public static int armorLevel(Player player, Enchantment enchant) {
        int helmetLevel = level(player.getInventory().getHelmet(), enchant);
        int chestplateLevel = level(player.getInventory().getChestplate(), enchant);
        int leggingsLevel = level(player.getInventory().getLeggings(), enchant);
        int bootsLevel = level(player.getInventory().getBoots(), enchant);
        return Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
    }

    public static int lootingLevel(Player player) {
        PlayerInventory inv = player.getInventory();
        int mainHandLevel = level(inv.getItemInMainHand(), Enchantment.LOOT_BONUS_MOBS);
        int offHandLevel = level(inv.getItemInOffHand(), Enchantment.LOOT_BONUS_MOBS);
        return Math.max(mainHandLevel, offHandLevel);
    }

    public static String numeral(int number) {
        switch (number) {
            case 10: return "X";
            case 9: return "IX";
            case 8: return "VIII";
            case 7: return "VII";
            case 6: return "VI";
            case 5: return "V";
            case 4: return "IV";
            case 3: return "III";
            case 2: return "II";
            case 1: return "I";
            default: return String.valueOf(number);
        }
    }

    public static int number(String numeral) {
        switch (numeral.toUpperCase()) {
            case "X": return 10;
            case "IX": return 9;
            case "VIII": return 8;
            case "VII": return 7;
            case "VI": return 6;
            case "V": return 5;
            case "IV": return 4;
            case "III": return 3;
            case "II": return 2;
            case "I": return 1;
            default: {
                try {
                    return Integer.parseInt(numeral);
                }
                catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
    }

    public static boolean canMergeInAnvil(ItemStack itemA, ItemStack itemB) {
        if (itemA == null || itemB == null) return false;
        if (itemA.getAmount() != 1 || itemB.getAmount() != 1) return false;
        if (!(itemA.getType() == itemB.getType() || itemB.getType() == Material.ENCHANTED_BOOK)) return false;
        if (itemA.getMaxStackSize() != 1) return false;  
        return true;
    }

    public static void verify(ItemStack item) {
        if (item == null) return;
        Iterator<Enchantment> iter = CustomEnchantment.all().iterator();
        while (iter.hasNext()) {
            level(item, iter.next());
        }
    }

    private static void fix(ItemStack item, Enchantment enchantment, Integer level) {
        if (item == null) return;
        if (level < 1) return;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else item.addUnsafeEnchantment(enchantment, level);
    }
}