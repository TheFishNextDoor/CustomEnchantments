package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.MaterialUtil;

public class Range extends CustomEnchantment {

    public Range(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Range";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        return MaterialUtil.isRanged(item.getType());
    }

    @Override
    public String getDescription() {
        return "Fired projectiles have a higher velocity. Rare drop from skeleton.";
    }

    public static void modifyVelocity(Player player, Projectile projectile) {
        if (player == null || projectile == null) return;
        final int level = EnchantUtil.rangedLevel(player, CustomEnchantment.RANGE);
        if (level < 1) return;
        double levelValue = level; 
        projectile.setVelocity(projectile.getVelocity().multiply(1.0 + (levelValue/5.0)));
    }
}