package com.thefishnextdoor.customenchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class ProjectileResistance extends MutuallyExclusiveChestplateEnchantment {

    public ProjectileResistance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Projectile Resistance";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Wearer is immune to projectile damage. Rare drop from wither.";
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