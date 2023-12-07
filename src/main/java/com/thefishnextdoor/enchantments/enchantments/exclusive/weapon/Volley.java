package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Volley extends Enchantment {

    public static final String NAME = "Volley";

    public Volley(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean conflictsWith(Enchantment other) {
        String name = other.getName();
        if (name.equals(LifeSteal.NAME)) return true;
        if (name.equals(Venom.NAME)) return true;
        if (name.equals(Withering.NAME)) return true;
        if (name.equals(Obscure.NAME)) return true;
        if (name.equals(Disorienting.NAME)) return true;
        if (name.equals(Debilitating.NAME)) return true;
        if (name.equals(Starving.NAME)) return true;
        if (name.equals(Crippling.NAME)) return true;
        if (name.equals(Glass.NAME)) return true;
        if (name.equals(Levitating.NAME)) return true;
        if (name.equals(BloodTipped.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        Material type = item.getType();
        return type == Material.BOW || type == Material.CROSSBOW;
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
            arrow.setPickupStatus(PickupStatus.DISALLOWED);
        }
    }
}