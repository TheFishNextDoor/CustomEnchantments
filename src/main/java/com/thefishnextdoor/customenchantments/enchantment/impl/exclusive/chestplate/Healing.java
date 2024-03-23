package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.enchantment.ArmorEffects;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Healing extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Healing";
    }

    @Override
    public String getDescription() {
        return "Wearer has faster health regeneration. Rare drop from zombified piglin.";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantTools.level(chestplate, CustomEnchantment.HEALING);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, ArmorEffects.PERIOD * 2, level-1));
    }
}