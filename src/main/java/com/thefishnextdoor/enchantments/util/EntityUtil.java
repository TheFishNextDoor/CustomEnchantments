package com.thefishnextdoor.enchantments.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
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
    
}
