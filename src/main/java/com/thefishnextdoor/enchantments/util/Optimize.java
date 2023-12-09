package com.thefishnextdoor.enchantments.util;

import org.bukkit.inventory.ItemStack;

public class Optimize {

    public static boolean couldHaveCustomEnchantment (ItemStack item) {
        if (item == null) return false;
        return item.hasItemMeta() && item.getItemMeta().hasLore();
    }
}
