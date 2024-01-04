package com.thefishnextdoor.customenchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class FireResistance extends MutuallyExclusiveChestplateEnchantment {

    public FireResistance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fire Resistance";
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
        return "Wearer is immune to fire damage. Rare drop from blaze.";
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        if (EnchantTools.has(chestplate, CustomEnchantment.FIRE_RESISTANCE)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, ArmorEffects.PERIOD * 2, 0));
        }
    }
}