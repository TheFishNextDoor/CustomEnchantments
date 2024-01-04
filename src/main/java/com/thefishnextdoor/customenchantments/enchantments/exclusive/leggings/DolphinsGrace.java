package com.thefishnextdoor.customenchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

public class DolphinsGrace extends MutuallyExclusiveLeggingsEnchantment {

    public DolphinsGrace(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Dolphins Grace";
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
        return "Wearer can move quickly in water. Rare drop from guardian.";
    }

    public static void onTimer(Player player, ItemStack leggings) {
        if (!player.isInWater()) {
            return;
        }
        if (!EnchantTools.has(leggings, CustomEnchantment.DOLPHINS_GRACE)) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, ArmorEffects.PERIOD * 2, 0));
    }
}