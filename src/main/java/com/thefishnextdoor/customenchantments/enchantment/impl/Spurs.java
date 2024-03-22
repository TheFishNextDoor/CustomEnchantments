package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Spurs extends CustomEnchantment {

    @Override
    public String getName() {
        return "Spurs";
    }

    @Override
    public String getDescription() {
        return "Mounted mobs receive speed and jump boost. Rare drop from pillager.";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
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
        return MaterialTools.isBoots(item.getType());
    }

    public static void onTimer(Player player, ItemStack boots) {
        if (player.getVehicle() == null || !(player.getVehicle() instanceof LivingEntity)) {
            return;
        }

        int level = EnchantTools.level(boots, CustomEnchantment.SPURS);
        if (level < 1) {
            return;
        }
        
        LivingEntity mount = (LivingEntity) player.getVehicle();
        mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, ArmorEffects.PERIOD * 2, level - 1));
        mount.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, ArmorEffects.PERIOD * 2, level - 1));
    }
}