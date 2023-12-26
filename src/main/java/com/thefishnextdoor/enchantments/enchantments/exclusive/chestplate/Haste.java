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

public class Haste extends MutuallyExclusiveChestplateEnchantment {

    public Haste(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Haste";
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
        int level = EnchantUtil.level(chestplate, CustomEnchantment.HASTE);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Timer.PERIOD * 2, level-1));
    }
}