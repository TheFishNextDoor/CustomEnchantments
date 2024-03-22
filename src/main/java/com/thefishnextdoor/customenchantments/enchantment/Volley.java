package com.thefishnextdoor.customenchantments.enchantment;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.EntityTag;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Volley extends CustomEnchantment {

    @Override
    public String getName() {
        return "Volley";
    }

    @Override
    public String getDescription() {
        return "Fire multiple arrows at once. Rare drop from skeleton.";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }
    
    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.firesArrows(item.getType());
    }

    public static void summonProjectiles(Player player, Projectile projectile) {
        if (!(projectile instanceof AbstractArrow)) {
            return;
        }

        int level = EnchantTools.rangedLevel(player, CustomEnchantment.VOLLEY);
        level = Math.min(level, 9);
        if (level < 1) {
            return;
        }

        if (EntityTag.FROM_VOLLEY.isOn(projectile)) {
            return;
        }
        
        Location location = projectile.getLocation();
        EntityType type = projectile.getType();
        Vector velocity = projectile.getVelocity();
        for (int i = 1; i <= level; i++) {
            Volley.fireArrow(player, location, type, velocity.clone().multiply(1.0 - (i * 0.1)));
        }
    }

    private static void fireArrow(Player player, Location location, EntityType type, Vector velocity) {
        AbstractArrow arrow = (AbstractArrow) location.getWorld().spawnEntity(location, type);
        arrow.setShooter(player);
        arrow.setVelocity(velocity);
        arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
        EntityTag.FROM_VOLLEY.applyTo(arrow);
        Bukkit.getServer().getPluginManager().callEvent(new ProjectileLaunchEvent(arrow));
    }
}