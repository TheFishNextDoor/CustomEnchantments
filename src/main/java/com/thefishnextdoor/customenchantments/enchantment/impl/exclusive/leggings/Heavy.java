package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

public class Heavy extends MutuallyExclusiveLeggingsEnchantment {

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public String getDescription() {
        return "Wearer receives no knockback. Rare drop from warden.";
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
        if (event.getCause() != DamageCause.ENTITY_ATTACK) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getLeggings(), CustomEnchantment.HEAVY)) {
            return;
        }
        EntityTools.cancelKnockback(player);
    }
}