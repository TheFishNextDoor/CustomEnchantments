package com.thefishnextdoor.customenchantments.enchantment.exclusive.boots;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Leaping extends MutuallyExclusiveBootsEnchantment {

    @Override
    public String getName() {
        return "Leaping";
    }

    @Override
    public String getDescription() {
        return "Wearer has increased jump height. Rare drop from slime.";
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
        int level = EnchantTools.level(boots, CustomEnchantment.LEAPING);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, ArmorEffects.PERIOD * 2, level-1));
    }
}