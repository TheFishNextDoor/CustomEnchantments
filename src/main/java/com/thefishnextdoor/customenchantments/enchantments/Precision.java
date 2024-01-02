package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.MaterialUtil;

public class Precision extends CustomEnchantment {

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
        if (item == null) return false;
        return MaterialUtil.isRangedWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Fired projectiles have 100% accuracy. Rare drop from skeleton.";
    }

    public static void modifyVelocity(Player player, Projectile projectile) {
        if (player == null || projectile == null) return;
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.PRECISION)) return;
        Vector direction = player.getEyeLocation().getDirection();
        Vector velocity = projectile.getVelocity();
        velocity.setX(direction.getX() * velocity.length());
        velocity.setY(direction.getY() * velocity.length());
        velocity.setZ(direction.getZ() * velocity.length());
        projectile.setVelocity(velocity);
    }
}