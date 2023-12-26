package com.thefishnextdoor.enchantments.enchantments.exclusive.elytra;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveElytraEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Boosters extends MutuallyExclusiveElytraEnchantment {

    public Boosters(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Boosters";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void whenGliding(Player player) {
        if (player.isInWater()) return;
        Vector velocity = player.getVelocity();
        int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.BOOSTERS);
        if (level < 1) return;
        if (level > 10) level = 10;
        if (velocity.length() > 1.1) return;
        Vector increase = player.getLocation().getDirection().clone().normalize().multiply(level * 0.01);
        player.setVelocity(velocity.add(increase));
    }
}