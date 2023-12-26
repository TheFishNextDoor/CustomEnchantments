package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Worm extends MutuallyExclusiveHelmetEnchantment {

    public Worm(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Worm";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static boolean resist(Player player, EntityDamageEvent event) {
        return event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && EnchantUtil.has(player.getInventory().getHelmet(), CustomEnchantment.WORM);
    }
}