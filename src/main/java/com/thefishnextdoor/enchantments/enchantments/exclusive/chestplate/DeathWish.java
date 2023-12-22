package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class DeathWish extends Enchantment {

    public DeathWish(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Death Wish";
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
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return CustomEnchantment.isMutuallyExclusiveChestplate(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isChestplate(item.getType());
    }

    public static void modifyDamage(Player player, EntityDamageByEntityEvent event) {
        if (!EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) return;
        event.setDamage(event.getDamage() * 1.75);
    }

    public static void modifyDamage(Player player, EntityDamageEvent event) {
        if (!EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATH_WISH)) return;
        event.setDamage(event.getDamage() * 1.5);
    }
}