package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects.ArmorCheckOptimizer;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class CurseOfRadiance extends CustomEnchantment {

    @Override
    public String getName() {
        return "Curse of Radiance";
    }

    @Override
    public String getDescription() {
        return "Wearer will begin to glow. Rare drop from magma cube.";
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
        return true;
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
        return MaterialTools.isArmor(item.getType());
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        if (EnchantTools.wearing(player, CustomEnchantment.CURSE_OF_RADIANCE, o)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, ArmorEffects.PERIOD * 2, 0));
        }
    }
}