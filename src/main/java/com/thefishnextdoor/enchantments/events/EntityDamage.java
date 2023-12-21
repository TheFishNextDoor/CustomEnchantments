package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Bounce;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Crush;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Worm;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.Heavy;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        boolean ranged = event.getCause() == DamageCause.PROJECTILE;
        if (cancel(player, event, ranged)) return; 
        DeathWish.modifyDamage(player, event);
        Heavy.onPlayerTakeDamage(player);
        Crush.onPlayerTakeDamage(player, event);
    }

    private boolean cancel(Player player, EntityDamageEvent event, boolean ranged) {
        if (ProjectileResistance.resist(player, ranged) || Worm.resist(player, event) || Bounce.bounce(player, event)) {
            event.setCancelled(true);
            return true;
        }
        return false;
    }
}
