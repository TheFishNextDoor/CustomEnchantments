package com.thefishnextdoor.customenchantments.tools;

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

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.Plugin;
import com.thefishnextdoor.customenchantments.ArmorEffects.ArmorCheckOptimizer;

import net.md_5.bungee.api.ChatColor;

public class EnchantTools {

    public static boolean has(ItemStack item, Enchantment enchant) {
        return level(item, enchant) > 0;
    }

    public static int level(ItemStack item, Enchantment enchant) {
        if (item == null) {
            return 0;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return 0;
        }

        int level = 0;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) meta;
            if (bookMeta.hasStoredEnchant(enchant)) {
                level = bookMeta.getStoredEnchantLevel(enchant);
            }
        }
        else if (meta.hasEnchant(enchant)) {
            level = meta.getEnchantLevel(enchant);
        }

        if (Plugin.getSettings().CHECK_LORE && level == 0 && meta.hasLore()) {
            String enchantLoreBase = nonLeveledLoreString(enchant);
            for (String line : meta.getLore()) {
                if (!line.startsWith(enchantLoreBase)) {
                    continue;
                }

                level = line.equals(enchantLoreBase) ? 1 : number(line.replaceFirst(enchantLoreBase, "").trim());
                if (level > 0) {
                    EnchantTools.fix(item, enchant, level);
                    break;
                }
            }
        }

        return level;
    }

    public static boolean wearing(Player player, Enchantment enchant, ArmorCheckOptimizer o) {
        return armorLevel(player, enchant, o) > 0;
    }

    public static int armorLevel(Player player, Enchantment enchant, ArmorCheckOptimizer o) {
        int helmetLevel = 0;
        int chestplateLevel = 0;
        int leggingsLevel = 0;
        int bootsLevel = 0;
        if (o.CHECK_HELMET) {
            helmetLevel = level(o.HELMET, enchant);
        }
        if (o.CHECK_CHESTPLATE) {
            chestplateLevel = level(o.CHESTPLATE, enchant);
        }
        if (o.CHECK_LEGGINGS) {
            leggingsLevel = level(o.LEGGINGS, enchant);
        }
        if (o.CHECK_BOOTS) {
            bootsLevel = level(o.BOOTS, enchant);
        }
        return Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
    }

    public static boolean holdingMeleeWith(Player player, Enchantment enchant) {
        return meleeLevel(player, enchant) > 0;
    }

    public static int meleeLevel(Player player, Enchantment enchant) {
        return level(InventoryTools.getMeleeItemInUse(player), enchant);
    }

    public static boolean holdingRangedWith(Player player, Enchantment enchant) {
        return rangedLevel(player, enchant) > 0;
    }

    public static int rangedLevel(Player player, Enchantment enchant) {
        return level(InventoryTools.getRangedItemInUse(player), enchant);
    }

    public static int weaponLevel(Player player, Enchantment enchant, boolean ranged) {
        if (ranged) {
            return rangedLevel(player, enchant);
        }
        else {
            return meleeLevel(player, enchant);
        }
    }

    public static boolean holdingHoeWith(Player player, Enchantment enchant) {
        return hoeLevel(player, enchant) > 0;
    }

    public static int hoeLevel(Player player, Enchantment enchant) {
        return level(InventoryTools.getHoeInUse(player), enchant);
    }

    public static boolean holdingShieldWith(Player player, Enchantment enchant) {
        return shieldLevel(player, enchant) > 0;
    }

    public static int shieldLevel(Player player, Enchantment enchant) {
        return level(InventoryTools.getShieldInUse(player), enchant);
    }

    public static int lootingLevel(Player player) {
        PlayerInventory inv = player.getInventory();
        int mainHandLevel = level(inv.getItemInMainHand(), Enchantment.LOOT_BONUS_MOBS);
        int offHandLevel = level(inv.getItemInOffHand(), Enchantment.LOOT_BONUS_MOBS);
        return Math.max(mainHandLevel, offHandLevel);
    }

    public static boolean addEnchant(ItemStack item, Enchantment enchantment, Integer level, boolean force, boolean combine) {

        // Verify
        if (level < 1 || item == null || item.getType() == Material.AIR) {
            return false;
        }

        int currentLevel = level(item, enchantment);
        level = Math.min(level, 255);        
        if (!force) {
            if (level < currentLevel) {
                return false;
            }
            if (!(enchantment.canEnchantItem(item) || item.getType() == Material.ENCHANTED_BOOK)) {
                return false;
            }
            if (EnchantTools.hasConflictingEnchantments(item, enchantment)) {
                return false;
            }
        }
    
        // Remove Overridden Enchantments
        if (Plugin.getSettings().REMOVE_OVERRIDDEN_ENCHANTMENTS) {
            if (same(enchantment, CustomEnchantment.UNBREAKABLE)) {
                EnchantTools.removeEnchant(item, Enchantment.DURABILITY);
                EnchantTools.removeEnchant(item, Enchantment.MENDING);
            }
            else if (same(enchantment, CustomEnchantment.FIRE_RESISTANCE)) {
                EnchantTools.removeEnchant(item, Enchantment.PROTECTION_FIRE);
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
        EnchantTools.removeEnchant(item, enchantment); // Remove old lore

        if (combine && level == currentLevel && currentLevel < enchantment.getMaxLevel()) {
            level++;
        }

        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else {
            item.addUnsafeEnchantment(enchantment, level);
        }
        
        // Add Lore
        if (!CustomEnchantment.isCustomEnchantment(enchantment)) {
            return true;
        }

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<String>();
        lore.add(0, leveledLoreString(enchantment, level));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }

    public static boolean removeEnchant(ItemStack item, Enchantment enchantment) {

        // Remove Enchantment
        if (item == null) {
            return false;
        }

        boolean hasEnchant = has(item, enchantment);
        if (hasEnchant) {
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                meta.removeStoredEnchant(enchantment);
                item.setItemMeta(meta);
            }
            else {
                item.removeEnchantment(enchantment);
            }
        }
        
        // Remove Lore
        if (!CustomEnchantment.isCustomEnchantment(enchantment)) {
            return hasEnchant;
        }

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            return hasEnchant;
        }

        List<String> newLore = new ArrayList<>();
        String enchantLoreBase = nonLeveledLoreString(enchantment);
        for (String line : lore) {
            if (!line.startsWith(enchantLoreBase)) {
                newLore.add(line);
            }
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return hasEnchant;
    }

    public static ArrayList<Enchantment> enchantments(ItemStack item) {
        ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();

        if (item == null) {
            return enchantments;
        }

        if (!item.hasItemMeta()) {
            return enchantments;
        }

        Iterator<Enchantment> iter;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            if (!meta.hasStoredEnchants()) {
                return enchantments;
            }
            iter = meta.getStoredEnchants().keySet().iterator();
        }
        else {
            ItemMeta meta = item.getItemMeta();
            if (!meta.hasEnchants()) {
                return enchantments;
            }
            iter = meta.getEnchants().keySet().iterator();
        }

        while (iter.hasNext()) {
            enchantments.add(iter.next());
        }
        return enchantments;
    }

    public static boolean sameEnchantments(ItemStack itemA, ItemStack itemB) {
        if (itemA == null || itemB == null) {
            return false;
        }

        if (itemA.getType() != itemB.getType()) {
            return false;
        }

        if (itemA.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta metaA = (EnchantmentStorageMeta) itemA.getItemMeta();
            EnchantmentStorageMeta metaB = (EnchantmentStorageMeta) itemB.getItemMeta();
            if (metaA.getStoredEnchants().size() != metaB.getStoredEnchants().size()) {
                return false;
            }

            Iterator<Enchantment> iter = metaA.getStoredEnchants().keySet().iterator();
            while (iter.hasNext()) {
                Enchantment enchantment = iter.next();
                if (!metaB.hasStoredEnchant(enchantment)) {
                    return false;
                }
                if (metaA.getStoredEnchantLevel(enchantment) != metaB.getStoredEnchantLevel(enchantment)) {
                    return false;
                }
            }
        }
        else {
            if (itemA.getEnchantments().size() != itemB.getEnchantments().size()) {
                return false;
            }

            Iterator<Enchantment> iter = itemA.getEnchantments().keySet().iterator();
            while (iter.hasNext()) {
                Enchantment enchantment = iter.next();
                if (!itemB.containsEnchantment(enchantment)) {
                    return false;
                }
                if (itemA.getEnchantmentLevel(enchantment) != itemB.getEnchantmentLevel(enchantment)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static Enchantment getEnchantment(String name) {
        for (Enchantment enchantment : Enchantment.values()) {
            if (name(enchantment).equalsIgnoreCase(name)) {
                return enchantment;
            }
        }
        return null;
    }

    public static ItemStack enchantedBook(Enchantment enchantment, int level) {
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
        addEnchant(enchantedBook, enchantment, level, true, false);
        return enchantedBook;
    }

    public static boolean same(Enchantment enchant1, Enchantment enchant2) {
        return name(enchant1).equals(name(enchant2));
    }

    public static String name(Enchantment enchantment) {
        return enchantment.getKey().getKey();
    }
    
    public static String leveledLoreString(Enchantment enchantment, Integer level) {
        String lore = nonLeveledLoreString(enchantment);
        if (level == 1 && enchantment.getMaxLevel() == 1) {
            return lore;
        }
        else {
            return lore + " " + (Plugin.getSettings().USE_ARABIC_NUMERALS ? level.toString() : numeral(level));
        }
    }

    @SuppressWarnings("deprecation")
    public static String nonLeveledLoreString(Enchantment enchantment) {
        return enchantment.isCursed() ? ChatColor.RED + enchantment.getName() : ChatColor.GRAY + enchantment.getName();
    }

    public static String numeral(int number) {
        switch (number) {
            case 10:
                return "X";
            case 9:
                return "IX";
            case 8:
                return "VIII";
            case 7:
                return "VII";
            case 6:
                return "VI";
            case 5:
                return "V";
            case 4:
                return "IV";
            case 3:
                return "III";
            case 2:
                return "II";
            case 1:
                return "I";
            default:
                return String.valueOf(number);
        }
    }

    public static int number(String numeral) {
        switch (numeral.toUpperCase()) {
            case "X":
                return 10;
            case "IX":
                return 9;
            case "VIII":
                return 8;
            case "VII":
                return 7;
            case "VI":
                return 6;
            case "V":
                return 5;
            case "IV":
                return 4;
            case "III":
                return 3;
            case "II":
                return 2;
            case "I":
                return 1;
            default: {
                try {
                    return Integer.parseInt(numeral);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
    }

    public static boolean hasConflictingEnchantments(ItemStack item, Enchantment enchantment) {
        Iterator<Enchantment> iter = enchantments(item).iterator();
        while (iter.hasNext()) {
            Enchantment ienchantment = iter.next();
            if (same(ienchantment, enchantment)) {
                continue;
            }
            if (enchantment.conflictsWith(ienchantment) || ienchantment.conflictsWith(enchantment)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canMergeInAnvil(ItemStack itemA, ItemStack itemB) {
        if (itemA == null || itemB == null) {
            return false;
        }

        if (itemA.getAmount() != 1 || itemB.getAmount() != 1) {
            return false;
        }

        if (!(itemA.getType() == itemB.getType() || itemB.getType() == Material.ENCHANTED_BOOK)) {
            return false;
        }

        if (itemA.getMaxStackSize() != 1) {
            return false;
        }

        return true;
    }

    public static void verify(ItemStack item) {
        for (CustomEnchantment enchant : CustomEnchantment.all()) {
            level(item, enchant);
        }
    }

    private static void fix(ItemStack item, Enchantment enchantment, Integer level) {
        if (item == null) {
            return;
        }

        if (level < 1) {
            return;
        }
        
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else {
            item.addUnsafeEnchantment(enchantment, level);
        }
    }
}