package com.thefishnextdoor.enchantments.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.Plugin;

public class EntityUtil {

    public static void cancelKnockback(Plugin plugin, final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                entity.setVelocity(new Vector(0, 0, 0));
            }
        }, 0);
    }

    public static Entity convert(Entity entity, EntityType type) {
        if (entity.getType() == type) return entity;
        Entity newEntity = entity.getWorld().spawnEntity(entity.getLocation(), type);
        newEntity.setVelocity(entity.getVelocity());
        if (entity instanceof Projectile && newEntity instanceof Projectile) {
            ((Projectile) newEntity).setShooter(((Projectile) entity).getShooter());
        }
        if (entity instanceof AbstractArrow) {
            ((AbstractArrow) entity).setPickupStatus(PickupStatus.DISALLOWED);
        }
        entity.remove();
        return newEntity;
    }
}
