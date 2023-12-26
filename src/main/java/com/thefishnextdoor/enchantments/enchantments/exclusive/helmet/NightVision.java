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

public class NightVision extends MutuallyExclusiveHelmetEnchantment {

    public NightVision(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Night Vision";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onTimer(Player player, ItemStack helmet) {
        if (!EnchantUtil.has(helmet, CustomEnchantment.NIGHT_VISION)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900 + Timer.PERIOD, 0));
    }
}