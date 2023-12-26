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

public class Leaping extends MutuallyExclusiveBootsEnchantment {

    public Leaping(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Leaping";
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
        int level = EnchantUtil.level(boots, CustomEnchantment.LEAPING);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Timer.PERIOD * 2, level-1));
    }
}