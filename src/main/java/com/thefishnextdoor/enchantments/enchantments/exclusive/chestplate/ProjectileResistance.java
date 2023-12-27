package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

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

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;
        if (!EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.PROJECTILE_RESISTANCE)) return;
        event.setCancelled(true);
    }
}