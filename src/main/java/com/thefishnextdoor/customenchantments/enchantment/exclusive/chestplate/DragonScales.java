package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class DragonScales extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Dragon Scales";
    }

    @Override
    public String getDescription() {
        return "Wearer has increased damage resistance. Rare drop from ender dragon.";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantTools.level(chestplate, CustomEnchantment.DRAGON_SCALES);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, ArmorEffects.PERIOD * 2, level-1));
    }
}