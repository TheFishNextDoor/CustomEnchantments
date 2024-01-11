package com.thefishnextdoor.customenchantments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class EndOfTick {

    public static void remove(final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
            public void run() {
                entity.remove();
            }
        }, 0);
    }

    public static void setVelocity(final Entity entity, final Vector velocity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
            public void run() {
                entity.setVelocity(entity.getVelocity().add(velocity));
            }
        }, 0);
    }
}
