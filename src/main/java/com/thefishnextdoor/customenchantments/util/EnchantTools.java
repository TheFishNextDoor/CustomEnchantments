package com.thefishnextdoor.customenchantments.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects.ArmorCheckOptimizer;

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

        CustomEnchantment customEnchantment = CustomEnchantment.unWrap(enchant);
        if (CustomEnchantments.getSettings().CHECK_LORE && level == 0 && customEnchantment != null && meta.hasLore()) {
            String enchantLoreBase = CustomEnchantment.nonLeveledLoreString(customEnchantment);
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

    public static boolean addEnchant(ItemStack item, Enchantment enchant, Integer level, boolean force, boolean combine) {

        // Verify
        if (level < 1 || item == null || item.getType() == Material.AIR) {
            return false;
        }

        int currentLevel = level(item, enchant);
        level = Math.min(level, 255);        
        if (!force) {
            if (level < currentLevel) {
                return false;
            }
            if (!(enchant.canEnchantItem(item) || item.getType() == Material.ENCHANTED_BOOK)) {
                return false;
            }
            if (EnchantTools.hasConflictingEnchantments(item, enchant)) {
                return false;
            }
        }
    
        // Remove Overridden Enchantments
        if (CustomEnchantments.getSettings().REMOVE_OVERRIDDEN_ENCHANTMENTS) {
            if (same(enchant, CustomEnchantment.UNBREAKABLE)) {
                EnchantTools.removeEnchant(item, Enchantment.DURABILITY);
                EnchantTools.removeEnchant(item, Enchantment.MENDING);
            }
            else if (same(enchant, CustomEnchantment.FIRE_RESISTANCE)) {
                EnchantTools.removeEnchant(item, Enchantment.PROTECTION_FIRE);
            }
        }
    
        // Fix item
        if (same(enchant, CustomEnchantment.UNBREAKABLE)) {
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                damageable.setDamage(0);
                item.setItemMeta(meta);
            }
        }
        
        // Add Enchantment
        if (combine && level == currentLevel && currentLevel < enchant.getMaxLevel()) {
            level++;
        }

        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchant, level, true);
            item.setItemMeta(meta);
        }
        else {
            item.addUnsafeEnchantment(enchant, level);
        }
        
        CustomEnchantment customEnchantment = CustomEnchantment.unWrap(enchant);
        if (customEnchantment != null) {
            CustomEnchantment.addLore(customEnchantment, item, level);
        }

        return true;
    }

    public static boolean removeEnchant(ItemStack item, Enchantment enchant) {

        // Remove Enchantment
        if (item == null) {
            return false;
        }

        boolean hasEnchant = has(item, enchant);
        if (hasEnchant) {
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                meta.removeStoredEnchant(enchant);
                item.setItemMeta(meta);
            }
            else {
                item.removeEnchantment(enchant);
            }
        }
        
        CustomEnchantment customEnchantment = CustomEnchantment.unWrap(enchant);
        if (customEnchantment != null) {
            CustomEnchantment.removeLore(customEnchantment, item);
        }

        return hasEnchant;
    }

    public static HashMap<Enchantment, Integer> getEnchants(ItemStack item) {
        HashMap<Enchantment, Integer> enchants = new HashMap<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchant = allEnchantments.next();
            int level = level(item, enchant);
            if (level > 0) {
                enchants.put(enchant, level);
            }
        }
        return enchants;
    }

    public static boolean sameEnchants(ItemStack itemA, ItemStack itemB) {
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

            Iterator<Enchantment> metaAEnchantIter = metaA.getStoredEnchants().keySet().iterator();
            while (metaAEnchantIter.hasNext()) {
                Enchantment enchant = metaAEnchantIter.next();
                if (!metaB.hasStoredEnchant(enchant)) {
                    return false;
                }
                if (metaA.getStoredEnchantLevel(enchant) != metaB.getStoredEnchantLevel(enchant)) {
                    return false;
                }
            }
        }
        else {
            if (itemA.getEnchantments().size() != itemB.getEnchantments().size()) {
                return false;
            }

            Iterator<Enchantment> itemAEnchantIter = itemA.getEnchantments().keySet().iterator();
            while (itemAEnchantIter.hasNext()) {
                Enchantment enchant = itemAEnchantIter.next();
                if (!itemB.containsEnchantment(enchant)) {
                    return false;
                }
                if (itemA.getEnchantmentLevel(enchant) != itemB.getEnchantmentLevel(enchant)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public static Enchantment getEnchantmentFromName(String name) {
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchant = allEnchantments.next();
            if (enchant.getKey().getKey().equalsIgnoreCase(name)) {
                return enchant;
            }
        }
        return null;
    }

    public static ItemStack enchantedBook(Enchantment enchant, int level) {
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
        addEnchant(enchantedBook, enchant, level, true, false);
        return enchantedBook;
    }

    public static boolean same(Enchantment enchantA, Enchantment enchantB) {
        if (enchantA == null || enchantB == null) {
            return false;
        }
        return enchantA.getKey().toString().equals(enchantB.getKey().toString());
    }

    public static ArrayList<String> namesOfCompatibleEnchantments(ItemStack item) {
        if (item == null || item.getType() == Material.BOOK) {
            return EnchantTools.namesOfAllEnchantments();
        }
        
        ArrayList<String> names = new ArrayList<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchant = allEnchantments.next();
            if (has(item, enchant) || enchant.canEnchantItem(item)) {
                names.add(enchant.getKey().getKey());
            }
        }
    
        return names.isEmpty() ? EnchantTools.namesOfAllEnchantments() : names;
    }

    public static ArrayList<String> namesOfEnchantmentsOnItem(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchant = allEnchantments.next();
            if (has(item, enchant)) {
                names.add(enchant.getKey().getKey());
            }
        }
        return names;
    }

    public static ArrayList<String> namesOfAllEnchantments() {
        ArrayList<String> names = new ArrayList<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchant = allEnchantments.next();
            names.add(enchant.getKey().getKey());
        }
        return names;
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

    public static boolean hasConflictingEnchantments(ItemStack item, Enchantment enchant) {
        Iterator<Enchantment> itemEnchantIter = getEnchants(item).keySet().iterator();
        while (itemEnchantIter.hasNext()) {
            Enchantment itemEnchant = itemEnchantIter.next();
            if (same(itemEnchant, enchant)) {
                continue;
            }
            if (enchant.conflictsWith(itemEnchant) || itemEnchant.conflictsWith(enchant)) {
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
        for (Enchantment enchant : CustomEnchantment.all()) {
            level(item, enchant);
        }
    }

    private static void fix(ItemStack item, Enchantment enchant, Integer level) {
        if (item == null) {
            return;
        }

        if (level < 1) {
            return;
        }
        
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchant, level, true);
            item.setItemMeta(meta);
        }
        else {
            item.addUnsafeEnchantment(enchant, level);
        }
    }
}