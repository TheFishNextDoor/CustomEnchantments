package com.thefishnextdoor.enchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.enchantments.definitions.Generic.Destructive;

public class ProjectileHit implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile == null) return;
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Block block = event.getHitBlock();
        if (block == null) return;
        Destructive.onProjectileHitBlock(player, projectile, block);
    }
}
