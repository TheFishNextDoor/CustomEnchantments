package com.thefishnextdoor.enchantments.util;

import org.bukkit.inventory.ItemStack;

public class Optimize {

    public static boolean couldHaveCustomEnchantment (ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().hasLore();
    }
}
