package com.thefishnextdoor.enchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

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
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantUtil.level(boots, CustomEnchantment.SLOW_FALLING);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Timer.PERIOD * 2, level-1));
    }
}