package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

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

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

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
        if (item == null) return false;
        return InventoryUtil.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Fire multiple arrows at once. Rare drop from skeleton.";
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!(projectile instanceof AbstractArrow)) return;
        int level = EnchantUtil.rangedLevel(player, CustomEnchantment.VOLLEY);
        if (level < 1) return;
        if (level > 9) level = 9;
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