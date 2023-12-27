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

    @Override
    public String getDescription() {
        return "Prevents suffocation damage. Rare drop from silverfish.";
    }

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION) return;
        if (!EnchantUtil.has(player.getInventory().getHelmet(), CustomEnchantment.WORM)) return;
        event.setCancelled(true);
    }
}