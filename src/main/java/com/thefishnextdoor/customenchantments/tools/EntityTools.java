package com.thefishnextdoor.customenchantments.tools;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.EndOfTick;

public class EntityTools {

    public static Entity convert(Entity entity, EntityType type) {
        if (entity.getType() == type) {
            return entity;
        }

        Entity newEntity = entity.getWorld().spawnEntity(entity.getLocation(), type);
        newEntity.setFireTicks(entity.getFireTicks());
        if (newEntity instanceof Fireball) {
            ((Fireball) newEntity).setDirection(entity.getVelocity());
        }
        else {
            newEntity.setVelocity(entity.getVelocity());
        }

        if (entity instanceof Projectile && newEntity instanceof Projectile) {
            ((Projectile) newEntity).setShooter(((Projectile) entity).getShooter());
        }
        
        EndOfTick.remove(entity);
        return newEntity;
    }

    public static void cancelKnockback(Entity entity) {
        EndOfTick.setVelocity(entity, new Vector(0, 0, 0));
    }
}
