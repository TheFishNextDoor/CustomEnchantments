package com.thefishnextdoor.customenchantments.enchantment.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class SlowFalling extends MutuallyExclusiveBootsEnchantment {

    public SlowFalling(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Slow Falling";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Wearer falls gracefully like a feather. Rare drop from ghast.";
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantTools.level(boots, CustomEnchantment.SLOW_FALLING);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, ArmorEffects.PERIOD * 2, level-1));
    }
}