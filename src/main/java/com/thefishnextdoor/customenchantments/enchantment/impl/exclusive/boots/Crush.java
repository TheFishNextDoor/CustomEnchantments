package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Crush extends MutuallyExclusiveBootsEnchantment {

    @Override
    public String getName() {
        return "Crush";
    }

    @Override
    public String getDescription() {
        return "Falling on an entity will damage it. Rare drop from ravagers.";
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerTakeDamage(Player player, EntityDamageEvent event) {
        if (event.getCause() != DamageCause.FALL) {
            return;
        }

        int level = EnchantTools.level(player.getInventory().getBoots(), CustomEnchantment.CRUSH);
        if (level < 1) {
            return;
        }

        double damage = calcDamage(event.getDamage(), level);
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).damage(damage, player);
            }
        }
    }

    private static double calcDamage(double damage, int level) {
        switch (level) {
            case 1:
                return damage/3;
            case 2:
                return damage/2;
            case 3:
                return damage;
            case 4:
                return damage * 1.5;
            case 5:
                return damage * 1.75;
            case 6:
                return damage * 2;
            case 7:
                return damage * 2.25;
            case 8:
                return damage * 2.5;
            case 9:
                return damage * 2.75;
            case 10:
                return damage * 3;
            default:
                return 0;
        }
    }
}