package com.thefishnextdoor.customenchantments.enchantments.exclusive.elytra;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveElytraEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;

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

    @Override
    public String getDescription() {
        return "A small amount of constant thrust is added to the elytra. Rare drop from creeper.";
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