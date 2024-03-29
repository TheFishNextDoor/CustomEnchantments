package com.thefishnextdoor.customenchantments.enchantment.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

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

    @Override
    public String getDescription() {
        return "Wearer receives no knockback. Rare drop from warden.";
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