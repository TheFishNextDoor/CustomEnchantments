package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Strength extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Strength";
    }

    @Override
    public String getDescription() {
        return "Wearer has increased melee damage. Rare drop from zombified piglin.";
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
        int level = EnchantTools.level(chestplate, CustomEnchantment.STRENGTH);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, ArmorEffects.PERIOD * 2, level-1));
    }
}