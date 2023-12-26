package com.thefishnextdoor.enchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class Heavy extends MutuallyExclusiveLeggingsEnchantment {

    public Heavy(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerTakeDamage(Player player) {
        if (EnchantUtil.has(player.getInventory().getLeggings(), CustomEnchantment.HEAVY)) EntityUtil.cancelKnockback(player);
    }
}