package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.elytra;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveElytraEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Boosters extends MutuallyExclusiveElytraEnchantment {

    @Override
    public String getName() {
        return "Boosters";
    }

    @Override
    public String getDescription() {
        return "A small amount of constant thrust is added to the elytra. Rare drop from creeper.";
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
        if (player.isInWater()) {
            return;
        }

        int level = EnchantTools.level(player.getInventory().getChestplate(), CustomEnchantment.BOOSTERS);
        if (level < 1) {
            return;
        }

        Vector velocity = player.getVelocity();
        if (velocity.length() > 1.1) {
            return;
        }
        
        level = Math.min(level, 10);

        Vector increase = player.getLocation().getDirection().clone().normalize().multiply(level * 0.01);
        player.setVelocity(velocity.add(increase));
    }
}