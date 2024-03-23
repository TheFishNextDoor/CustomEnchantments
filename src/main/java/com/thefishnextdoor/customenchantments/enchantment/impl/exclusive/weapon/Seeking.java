package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Seeking extends MutuallyExclusiveWeaponEnchantment {

    private static ArrayList<SeekingArrow> seekingArrows = new ArrayList<>();

    private static int taskID = -1;

    @Override
    public String getName() {
        return "Seeking";
    }

    @Override
    public String getDescription() {
        return "Arrows are attracted to the nearest entity. Rare drop from Stray.";
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
        if (item == null) {
            return false;
        }
        return MaterialTools.firesArrows(item.getType());
    }

    public static void startTask(JavaPlugin plugin) {
        if (taskID != -1) {
            return;
        }
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<SeekingArrow> iter = seekingArrows.iterator();
                while (iter.hasNext()) {
                    SeekingArrow seekingArrow = iter.next();
                    if (seekingArrow.valid()) {
                        seekingArrow.seek();
                    }
                    else {
                        iter.remove();
                    }
                }
            }
        }, 0, 1);
    }

    public static void stopTask() {
        if (taskID != -1) {
            Bukkit.getScheduler().cancelTask(taskID);
            taskID = -1;
        }
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!(projectile instanceof AbstractArrow)) {
            return;
        }
        if (!EnchantTools.holdingRangedWith(player, CustomEnchantment.SEEKING)) {
            return;
        }
        new SeekingArrow((AbstractArrow) projectile, player);
    }

    private static class SeekingArrow {

        private static final double TURN_STRENGTH = 0.12; // 0.0 to 1.0

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
            Vector originalVelocity = arrow.getVelocity();
            double speed = originalVelocity.length();
            if (speed < 0.6) {
                return;
            }

            Entity nearest = nearestEntity();
            if (nearest == null) {
                return;
            }

            Vector newDirection = direction(arrow.getLocation(), centerLocation(nearest));
            Vector newVelocity = newDirection.multiply(speed);
            Vector weightedVelocity = originalVelocity.multiply(1.0 - TURN_STRENGTH).add(newVelocity.multiply(TURN_STRENGTH));
            arrow.setVelocity(weightedVelocity);
        }

        private Entity nearestEntity() {
            Entity nearest = null;
            double nearestAngle = 0;
            int radius = CustomEnchantments.getSettings().SEEKING_RADIUS;
            List<Entity> nearby = arrow.getNearbyEntities(radius, radius, radius);
            for (Entity entity : nearby) {
                if (!(entity instanceof LivingEntity)) {
                    continue;
                }

                if (entity instanceof Player && ((Player) entity).getUniqueId().equals(playerID)) {
                    continue;
                }

                Location entityLocation = centerLocation(entity);
                Location arrowLocation = arrow.getLocation();
                Vector entityDirection = direction(arrowLocation, entityLocation);
                Vector arrowDirection = arrow.getVelocity().clone().normalize();
                double angle = entityDirection.dot(arrowDirection);
                if (angle > nearestAngle && angle > 0.866) {
                    nearest = entity;
                    nearestAngle = angle;
                }
            }
            return nearest;
        }

        private static Vector direction(Location from, Location to) {
            return to.toVector().subtract(from.toVector()).normalize();
        }

        private static Location centerLocation(Entity entity) {
            return entity.getLocation().add(0, entity.getHeight()/2, 0);
        }
    }
} 