package com.thefishnextdoor.enchantments.util;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class CommandUtil {

    public static ArrayList<String> recommendedEnchants(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> allNames = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            String name = EnchantUtil.name(enchantment);
            allNames.add(name);
            if (EnchantUtil.has(item, enchantment) || enchantment.canEnchantItem(item)) names.add(name);
        }
        return names.size() == 0 ? allNames : names;
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
