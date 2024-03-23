package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Gills extends MutuallyExclusiveHelmetEnchantment {

    @Override
    public String getName() {
        return "Gills";
    }

    @Override
    public String getDescription() {
        return "Wearer can breath underwater. Rare drop from drowned.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack helmet) {
        if (!player.isInWater()) {
            return;
        }
        if (!EnchantTools.has(helmet, CustomEnchantment.GILLS)) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, ArmorEffects.PERIOD * 2, 0));
    }
}