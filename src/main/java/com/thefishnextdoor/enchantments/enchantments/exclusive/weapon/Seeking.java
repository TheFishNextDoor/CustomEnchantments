package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Seeking extends MutuallyExclusiveWeaponEnchantment {
    private static ArrayList<SeekingArrow> seekingArrows = new ArrayList<>();
    private static int taskID = -1;

    public Seeking(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Seeking";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Arrows are attracted to the nearest entity. Rare drop from Stray.";
    }

    public static void startTask(JavaPlugin plugin) {
        if (taskID != -1) return;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<SeekingArrow> iter = seekingArrows.iterator();
                while (iter.hasNext()) {
                    SeekingArrow seekingArrow = iter.next();
                    if (seekingArrow.valid()) seekingArrow.seek();
                    else iter.remove();
                }
            }
        }, 0, 1);
    }

    public static void stopTask() {
        if (taskID == -1) return;
        Bukkit.getScheduler().cancelTask(taskID);
        taskID = -1;
    }
    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!(projectile instanceof AbstractArrow)) return;
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.SEEKING)) return;
        new SeekingArrow((AbstractArrow) projectile, player);
    }

    private static class SeekingArrow {
        private static final int RADIUS = 8;
        private static final double WEIGHT = 0.12;
        private final AbstractArrow arrow;
        private final UUID playerID;
        private final long created = System.currentTimeMillis();

        private SeekingArrow(AbstractArrow arrow, Player shooter) {
            this.arrow = arrow;
            this.playerID = shooter.getUniqueId();
            seekingArrows.add(this);
        }

        public boolean valid() {
            return arrow.isValid() && !arrow.isOnGround() && System.currentTimeMillis() - created < 10000;
        }

        public void seek() {
            Entity nearest = nearestEntity();
            if (nearest == null) return;
            Vector originalVelocity = arrow.getVelocity();
            double speed = originalVelocity.length();
            if (speed < 0.6) return;
            Vector newDirection = direction(arrow.getLocation(), nearest.getLocation());
            Vector newVelocity = newDirection.multiply(speed);
            Vector weightedVelocity = originalVelocity.multiply(1 - WEIGHT).add(newVelocity.multiply(WEIGHT));
            arrow.setVelocity(weightedVelocity);
        }

        private Entity nearestEntity() {
            Entity nearest = null;
            double nearestDistance = RADIUS;
            List<Entity> nearby = arrow.getNearbyEntities(RADIUS, RADIUS, RADIUS);
            for (Entity entity : nearby) {
                if (!(entity instanceof LivingEntity)) continue;
                if (entity instanceof Player && ((Player) entity).getUniqueId().equals(playerID)) continue;
                double distance = arrow.getLocation().distance(entity.getLocation());
                if (distance >= nearestDistance) continue;
                nearest = entity;
                nearestDistance = distance;
            }
            return nearest;
        }

        private static Vector direction(Location from, Location to) {
            return to.toVector().subtract(from.toVector()).normalize();
        }
    }
} 