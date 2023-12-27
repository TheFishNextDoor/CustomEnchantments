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

public class Strength extends MutuallyExclusiveChestplateEnchantment {

    public Strength(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Strength";
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
        return "Wearer has increased melee damage. Rare drop from zombified piglin.";
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantUtil.level(chestplate, CustomEnchantment.STRENGTH);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Timer.PERIOD * 2, level-1));
    }
}