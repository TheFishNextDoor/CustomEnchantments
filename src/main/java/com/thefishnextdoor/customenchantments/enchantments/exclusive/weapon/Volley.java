package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;

public class Volley extends MutuallyExclusiveWeaponEnchantment {

    public Volley(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Volley";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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

    @Override
    public String getDescription() {
        return "Fire multiple arrows at once. Rare drop from skeleton.";
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!(projectile instanceof AbstractArrow)) {
            return;
        }

        int level = EnchantTools.rangedLevel(player, CustomEnchantment.VOLLEY);
        level = Math.min(level, 9);
        if (level < 1) {
            return;
        }
        
        World world = projectile.getWorld();
        Location location = projectile.getLocation();
        EntityType type = projectile.getType();
        Vector velocity = projectile.getVelocity();
        for (int i = 1; i <= level; i++) {
            AbstractArrow arrow = (AbstractArrow) world.spawnEntity(location, type);
            arrow.setShooter(player);
            arrow.setVelocity(velocity.clone().multiply(1.0 - (i * 0.1)));
            arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
        }
    }
}