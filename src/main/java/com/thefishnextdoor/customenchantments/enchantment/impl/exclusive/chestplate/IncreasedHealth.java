package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class IncreasedHealth extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Increased Health";
    }

    @Override
    public String getDescription() {
        return "Wearer has increased max health. Rare drop from ender dragon.";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantTools.level(chestplate, CustomEnchantment.INCREASED_HEALTH);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, ArmorEffects.PERIOD * 2, level-1));
    }
}