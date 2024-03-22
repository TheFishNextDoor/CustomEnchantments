package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class DeathWish extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Death Wish";
    }

    @Override
    public String getDescription() {
        return "Wearer takes increased damage and deals increased damage. Rare drop from wither skeleton.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    public static void modifyDamage(Player player, EntityDamageByEntityEvent event) {
        if (!EnchantTools.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) {
            return;
        }
        event.setDamage(event.getDamage() * 1.75);
    }

    public static void modifyDamage(Player player, EntityDamageEvent event) {
        if (EnchantTools.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) {
            event.setDamage(event.getDamage() * 1.5);
        }
    }
}