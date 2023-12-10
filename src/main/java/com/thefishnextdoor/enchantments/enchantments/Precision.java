package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Precision extends Enchantment {

    public Precision(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Precision";
    }

    @Override
    public int getMaxLevel() {
        return 1;
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
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isRanged(item.getType());
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (player == null || projectile == null) return;
        if (!EnchantUtil.holding(player, CustomEnchantment.PRECISION)) return;
        Vector direction = player.getEyeLocation().getDirection();
        Vector velocity = projectile.getVelocity();
        velocity.setX(direction.getX() * velocity.length());
        velocity.setY(direction.getY() * velocity.length());
        velocity.setZ(direction.getZ() * velocity.length());
        projectile.setVelocity(velocity);
    }
}