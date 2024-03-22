package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Worm extends MutuallyExclusiveHelmetEnchantment {

    @Override
    public String getName() {
        return "Worm";
    }

    @Override
    public String getDescription() {
        return "Prevents suffocation damage. Rare drop from silverfish.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getHelmet(), CustomEnchantment.WORM)) {
            return;
        }
        event.setCancelled(true);
    }
}