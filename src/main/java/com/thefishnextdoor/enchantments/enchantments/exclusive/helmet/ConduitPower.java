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

public class ConduitPower extends MutuallyExclusiveHelmetEnchantment {

    public ConduitPower(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Conduit Power";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "See underwater, breathe underwater, mine faster underwater. Drop from elder guardian.";
    }

    public static void onTimer(Player player, ItemStack helmet) {
        int level = EnchantUtil.level(helmet, CustomEnchantment.CONDUIT_POWER);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Timer.PERIOD * 2, level-1));
    }
}