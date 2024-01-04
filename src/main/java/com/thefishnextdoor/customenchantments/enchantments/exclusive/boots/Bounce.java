package com.thefishnextdoor.customenchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class Bounce extends MutuallyExclusiveBootsEnchantment {

    public Bounce(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Bounce";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Wearer bounces instead of taking fall damage. Rare drop from slime.";
    }

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }

        int level = EnchantTools.level(player.getInventory().getBoots(), CustomEnchantment.BOUNCE);
        if (level < 1) {
            return;
        }
        
        double v = Math.log(event.getDamage()) * (level + 2) / 10;
        player.setVelocity(player.getVelocity().setY(v));
        event.setCancelled(true);
    }
}