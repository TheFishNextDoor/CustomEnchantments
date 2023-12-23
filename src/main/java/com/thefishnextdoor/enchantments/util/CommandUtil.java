package com.thefishnextdoor.enchantments.util;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class CommandUtil {

    public static ArrayList<String> recommendedEnchantmentNames(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            if (EnchantUtil.has(item, enchantment) || enchantment.canEnchantItem(item)) {
                names.add(EnchantUtil.name(enchantment));
            }
        }
        return names.isEmpty() ? allEnchantmentNames() : names;
    }

    public static ArrayList<String> allEnchantmentNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            names.add(EnchantUtil.name(enchantment));
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
