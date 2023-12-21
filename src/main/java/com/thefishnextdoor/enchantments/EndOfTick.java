package com.thefishnextdoor.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class EndOfTick {
    public static Plugin plugin;

    public static void init(Plugin plugin) {
        EndOfTick.plugin = plugin;
    }

    public static void remove(final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                entity.remove();
            }
        }, 0);
    }

    public static void setVelocity(final Entity entity, final Vector velocity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                entity.setVelocity(entity.getVelocity().add(velocity));
            }
        }, 0);
    }
}
