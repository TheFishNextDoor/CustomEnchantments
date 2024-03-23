package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class DolphinsGrace extends MutuallyExclusiveLeggingsEnchantment {

    @Override
    public String getName() {
        return "Dolphins Grace";
    }

    @Override
    public String getDescription() {
        return "Wearer can move quickly in water. Rare drop from guardian.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack leggings) {
        if (!player.isInWater()) {
            return;
        }
        if (!EnchantTools.has(leggings, CustomEnchantment.DOLPHINS_GRACE)) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, ArmorEffects.PERIOD * 2, 0));
    }
}