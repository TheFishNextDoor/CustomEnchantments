package com.thefishnextdoor.enchantments.enchantments.exclusive.elytra;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Boosters extends Enchantment {

    public Boosters(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Boosters";
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
        if (EnchantUtil.same(other, CustomEnchantment.MOMENTUM)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return item.getType() == Material.ELYTRA;
    }

    public static void whenGliding(Player player) {
        if (player.isInWater()) return;
        Vector velocity = player.getVelocity();
        int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.BOOSTERS);
        if (level < 1) return;
        if (level > 10) level = 10;
        if (velocity.length() > 1.1) return;
        Vector increase = player.getLocation().getDirection().clone().normalize().multiply(level * 0.01);
        player.setVelocity(velocity.add(increase));
    }
}