package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class DeathWish extends MutuallyExclusiveChestplateEnchantment {

    public DeathWish(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Death Wish";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Wearer takes increased damage and deals increased damage. Rare drop from wither skeleton.";
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