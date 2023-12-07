package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.enchantments.Plugin;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.Heavy;

public class Damage implements Listener {
    private final Plugin plugin;

    public Damage(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        double damage = event.getFinalDamage();
        boolean ranged = event.getCause() == DamageCause.PROJECTILE;
        ProjectileResistance.onPlayerTakeDamage(player, ranged, event);
        if (event.isCancelled()) return;
        Heavy.onPlayerTakeDamage(plugin, player);
        DeathWish.onPlayerTakeDamage(player, damage, event);
    }
}
