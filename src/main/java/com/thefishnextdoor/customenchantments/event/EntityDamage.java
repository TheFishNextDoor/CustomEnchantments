package com.thefishnextdoor.customenchantments.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Bounce;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Crush;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.FireResistance;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.Worm;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.leggings.Heavy;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;

        Bounce.modifyCancelStatus(player, event);
        ProjectileResistance.modifyCancelStatus(player, event);
        Worm.modifyCancelStatus(player, event);
        FireResistance.modifyCancelStatus(player, event);

        if (event.isCancelled()) {
            return;
        }

        DeathWish.modifyDamage(player, event);
        
        Heavy.onPlayerTakeDamage(player, event);
        Crush.onPlayerTakeDamage(player, event);
    }
}
