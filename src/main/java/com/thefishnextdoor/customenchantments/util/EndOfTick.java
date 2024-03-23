package com.thefishnextdoor.customenchantments.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantments;

public class EndOfTick {

    public static void remove(final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CustomEnchantments.getInstance(), new Runnable() {
            public void run() {
                entity.remove();
            }
        }, 0);
    }

    public static void setVelocity(final Entity entity, final Vector velocity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(CustomEnchantments.getInstance(), new Runnable() {
            public void run() {
                entity.setVelocity(entity.getVelocity().add(velocity));
            }
        }, 0);
    }
}
