package com.thefishnextdoor.customenchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

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

    @Override
    public String getDescription() {
        return "Wearer has increased jump height. Rare drop from slime.";
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantTools.level(boots, CustomEnchantment.LEAPING);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, ArmorEffects.PERIOD * 2, level-1));
    }
}