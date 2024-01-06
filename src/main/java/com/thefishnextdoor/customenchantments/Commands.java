package com.thefishnextdoor.customenchantments;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class Commands {
    
    public static final String RELOAD_PERMISSION = "fce.admin.reload";
    public static final String FENCHANT_PERMISSION = "fce.admin.fenchant";
    public static final String ENCHANTINFO_PERMISSION = "fce.user.enchantinfo";
    public static final String DISENCHANT_PERMISSION = "fce.user.disenchant";

    public static ArrayList<String> recommendedEnchantmentNames(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            if (EnchantTools.has(item, enchantment) || enchantment.canEnchantItem(item) || item.getType() == Material.ENCHANTED_BOOK) {
                names.add(EnchantTools.name(enchantment));
            }
        }
        return names.isEmpty() ? allEnchantmentNames() : names;
    }

    public static ArrayList<String> equippedEnchantmentNames(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            if (EnchantTools.has(item, enchantment)) {
                names.add(EnchantTools.name(enchantment));
            }
        }
        return names;
    }

    public static ArrayList<String> allEnchantmentNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            names.add(EnchantTools.name(enchantment));
        }
        return names;
    }

    public static String titleCase(String str) {
        str = str.replace("_", " ");
        String[] words = str.split(" ");
        String titleCase = "";
        for (String word : words) {
            titleCase += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return titleCase.trim();
    }
}
