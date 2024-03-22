package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class FireResistance extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Fire Resistance";
    }

    @Override
    public String getDescription() {
        return "Wearer is immune to fire damage. Rare drop from blaze.";
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
        DamageCause cause = event.getCause();
        if (cause != DamageCause.FIRE && cause != DamageCause.FIRE_TICK && cause != DamageCause.LAVA && cause != DamageCause.HOT_FLOOR) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getChestplate(), CustomEnchantment.FIRE_RESISTANCE)) {
            return;
        }
        event.setCancelled(true);
    }
}