package com.thefishnextdoor.enchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class Heavy extends MutuallyExclusiveLeggingsEnchantment {

    public Heavy(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerTakeDamage(Player player, EntityDamageEvent event) {
        if (event.getCause() != DamageCause.ENTITY_ATTACK) return;
        if (!EnchantUtil.has(player.getInventory().getLeggings(), CustomEnchantment.HEAVY)) return;
        EntityUtil.cancelKnockback(player);
    }
}