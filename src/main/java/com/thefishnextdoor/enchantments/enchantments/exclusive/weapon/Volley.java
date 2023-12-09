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
        if (EnchantUtil.same(other, CustomEnchantment.LIFE_STEAL)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.VENOM)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.WITHERING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.OBSCURE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DISORIENTING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DEBILITATING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.STARVING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.CRIPPLING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.GLASS)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.LEVITATING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.BLOOD_TIPPED)) return true;
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