package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;

public class Reflection extends CustomEnchantment {

    public Reflection(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Reflection";
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
        return item.getType() == Material.SHIELD;
    }

    @Override
    public String getDescription() {
        return "Reflect arrows at a higher velocity. Rare drop from pillager.";
    }

    public static void onDeflectProjectile(Player player, Projectile projectile) {
        if (projectile.isOnGround()) return;
        int level = EnchantUtil.shieldLevel(player, CustomEnchantment.REFLECTION);
        if (level < 1) return;
        if (level > 5) level = 5;
        projectile.setVelocity(projectile.getVelocity().multiply(3.5 + (level * 1.5)));
    }
}