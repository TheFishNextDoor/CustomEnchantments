package com.thefishnextdoor.enchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Crush extends MutuallyExclusiveBootsEnchantment {

    public Crush(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Crush";
    }

    @Override
    public int getMaxLevel() {
        return 8;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerTakeDamage(Player player, EntityDamageEvent event) {
        if (event.getCause() != DamageCause.FALL) return;
        int level = EnchantUtil.level(player.getInventory().getBoots(), CustomEnchantment.CRUSH);
        if (level < 1) return;
        double damage = calcDamage(event.getDamage(), level);
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).damage(damage, player);
            }
        }
    }

    private static double calcDamage(double damage, int level) {
        if (level == 1) return damage/3;
        else if (level == 2) return damage/2;
        else if (level == 3) return damage;
        else if (level == 4) return damage * 1.5;
        else if (level == 5) return damage * 1.75;
        else if (level == 6) return damage * 2;
        else if (level == 7) return damage * 2.25;
        else if (level == 8) return damage * 2.5;
        else if (level == 9) return damage * 2.75;
        else if (level >= 10) return damage * 3;
        else return 0;
    }
}