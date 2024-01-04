package com.thefishnextdoor.customenchantments.enchantments.exclusive.elytra;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveElytraEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class Momentum extends MutuallyExclusiveElytraEnchantment {

    public Momentum(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Momentum";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Wearer will gain speed faster when gliding. Rare drop from ender dragon.";
    }

    public static void whenGliding(Player player) {
        Vector velocity = player.getVelocity();
        double speed = velocity.length();
        if (speed < 0.6 || speed > 2.5) {
            return;
        }

        float pitch = -((float) Math.toDegrees(Math.asin(velocity.getY() / speed)));
        if (pitch <= 0) {
            return;
        }

        int level = EnchantTools.level(player.getInventory().getChestplate(), CustomEnchantment.MOMENTUM);
        if (level < 1) {
            return;
        }

        level = Math.min(level, 10);
        
        Vector increase = velocity.clone().normalize().multiply(level * (pitch/10) * 0.002);
        player.setVelocity(velocity.add(increase));
    }
}