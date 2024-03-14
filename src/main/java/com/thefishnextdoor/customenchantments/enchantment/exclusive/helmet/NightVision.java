package com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

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

    @Override
    public String getDescription() {
        return "Wearer has improved vision in the dark. Rare drop from spider.";
    }

    public static void onTimer(Player player, ItemStack helmet) {
        if (EnchantTools.has(helmet, CustomEnchantment.NIGHT_VISION)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900 + ArmorEffects.PERIOD, 0));
        }
    }
}