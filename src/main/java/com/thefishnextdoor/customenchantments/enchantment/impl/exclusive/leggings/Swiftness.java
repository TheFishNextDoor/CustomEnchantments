package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Swiftness extends MutuallyExclusiveLeggingsEnchantment {

    @Override
    public String getName() {
        return "Swiftness";
    }

    @Override
    public String getDescription() {
        return "Wearer has increased movement speed. Rare drop from witch.";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantTools.level(boots, CustomEnchantment.SWIFTNESS);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, ArmorEffects.PERIOD * 2, level-1));
    }
}