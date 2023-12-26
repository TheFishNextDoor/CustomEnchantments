package com.thefishnextdoor.enchantments.enchantments.exclusive.elytra;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveElytraEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Momentum extends MutuallyExclusiveElytraEnchantment {

    public Momentum(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Momentum";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return item.getType() == Material.ELYTRA;
    }

    public static void whenGliding(Player player) {
        Vector velocity = player.getVelocity();
        double speed = velocity.length();
        if (speed < 0.6 || speed > 2.5) return;
        float pitch = -((float) Math.toDegrees(Math.asin(velocity.getY() / velocity.length())));
        if (pitch <= 0) return;
        int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.MOMENTUM);
        if (level < 1) return;
        if (level > 10) level = 10;
        Vector increase = velocity.clone().normalize().multiply(level * (pitch/10) * 0.002);
        player.setVelocity(velocity.add(increase));
    }
}