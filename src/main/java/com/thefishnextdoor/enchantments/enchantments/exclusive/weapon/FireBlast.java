package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class FireBlast extends Enchantment {

    public FireBlast(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fire Blast";
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
        return CustomEnchantment.isMutuallyExclusiveWeapon(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isRanged(item.getType());
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRanged(player, CustomEnchantment.FIRE_BLAST)) return;
        Projectile newProjectile = (Projectile) EntityUtil.convert(projectile, EntityType.SMALL_FIREBALL);
        newProjectile.setVelocity(projectile.getVelocity().normalize().multiply(0.1));
    }
}