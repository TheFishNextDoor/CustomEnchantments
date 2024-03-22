package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Bounce extends MutuallyExclusiveBootsEnchantment {

    @Override
    public String getName() {
        return "Bounce";
    }

    @Override
    public String getDescription() {
        return "Wearer bounces instead of taking fall damage. Rare drop from slime.";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }

        int level = EnchantTools.level(player.getInventory().getBoots(), CustomEnchantment.BOUNCE);
        if (level < 1) {
            return;
        }
        
        double v = Math.log(event.getDamage()) * (level + 2) / 10;
        player.setVelocity(player.getVelocity().setY(v));
        event.setCancelled(true);
    }
}