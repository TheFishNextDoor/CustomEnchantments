package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Healing extends MutuallyExclusiveChestplateEnchantment {

    public Healing(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Healing";
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
        return "Wearer has faster health regeneration. Rare drop from zombified piglin.";
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantTools.level(chestplate, CustomEnchantment.HEALING);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, ArmorEffects.PERIOD * 2, level-1));
    }
}