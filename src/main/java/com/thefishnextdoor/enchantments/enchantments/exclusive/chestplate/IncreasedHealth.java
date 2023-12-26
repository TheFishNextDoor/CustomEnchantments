package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class IncreasedHealth extends MutuallyExclusiveChestplateEnchantment {

    public IncreasedHealth(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Increased Health";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantUtil.level(chestplate, CustomEnchantment.INCREASED_HEALTH);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Timer.PERIOD * 2, level-1));
    }
}