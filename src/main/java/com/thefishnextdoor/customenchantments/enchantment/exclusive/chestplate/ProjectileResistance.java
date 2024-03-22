package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class ProjectileResistance extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Projectile Resistance";
    }

    @Override
    public String getDescription() {
        return "Wearer is immune to projectile damage. Rare drop from wither.";
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
        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getChestplate(), CustomEnchantment.PROJECTILE_RESISTANCE)) {
            return;
        }
        event.setCancelled(true);
    }
}