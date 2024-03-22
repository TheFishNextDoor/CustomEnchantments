package com.thefishnextdoor.customenchantments.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Range extends CustomEnchantment {

    @Override
    public String getName() {
        return "Range";
    }

    @Override
    public String getDescription() {
        return "Fired projectiles have a higher velocity. Rare drop from skeleton.";
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
        if (item == null) {
            return false;
        }
        return MaterialTools.isRangedWeapon(item.getType());
    }

    public static void modifyVelocity(Player player, Projectile projectile) {
        if (player == null || projectile == null) {
            return;
        }

        int level = EnchantTools.rangedLevel(player, CustomEnchantment.RANGE);
        if (level < 1) {
            return;
        }
        
        double levelValue = level; 
        projectile.setVelocity(projectile.getVelocity().multiply(1.0 + (levelValue/5.0)));
    }
}