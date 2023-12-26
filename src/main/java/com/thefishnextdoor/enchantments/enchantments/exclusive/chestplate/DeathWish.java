package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

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
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    public static void modifyDamage(Player player, EntityDamageByEntityEvent event) {
        if (!EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) return;
        event.setDamage(event.getDamage() * 1.75);
    }

    public static void modifyDamage(Player player, EntityDamageEvent event) {
        if (!EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) return;
        event.setDamage(event.getDamage() * 1.5);
    }
}