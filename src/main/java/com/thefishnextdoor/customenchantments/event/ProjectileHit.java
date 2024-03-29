package com.thefishnextdoor.customenchantments.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Destructive;

public class ProjectileHit implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) {
            return;
        }

        Player player = (Player) shooter;
        Block block = event.getHitBlock();
        if (block != null) {
            Destructive.onProjectileHitBlock(player, projectile, block);
        }
        
        Entity entity = event.getHitEntity();
        if (entity != null) {
            Destructive.onProjectileHitEntity(player, projectile, entity);
        }
    }
}
