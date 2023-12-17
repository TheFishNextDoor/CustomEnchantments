package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class ProjectileResistance extends Enchantment {

    public ProjectileResistance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Projectile Resistance";
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
        return EnchantmentTarget.ARMOR_TORSO;
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
        if (EnchantUtil.same(other, CustomEnchantment.DRAGON_SCALES)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.HEALING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.FIRE_RESISTANCE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.STRENGTH)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.HASTE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.INCREASED_HEALTH)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.HERO_OF_THE_VILLAGE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DEATHWISH)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.FLAMING)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isChestplate(item.getType());
    }

    public static boolean resist(Player player, boolean ranged) {
        return ranged && EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.PROJECTILE_RESISTANCE);
    }
}