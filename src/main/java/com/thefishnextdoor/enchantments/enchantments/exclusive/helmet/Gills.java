package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Gills extends MutuallyExclusiveHelmetEnchantment {

    public Gills(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Gills";
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

    public static void onTimer(Player player, ItemStack helmet) {
        if (!EnchantUtil.has(helmet, CustomEnchantment.GILLS)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Timer.PERIOD * 2, 0));
    }
}