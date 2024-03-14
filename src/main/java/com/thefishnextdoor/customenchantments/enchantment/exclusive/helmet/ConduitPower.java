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
        if (!player.isInWater()) {
            return;
        }

        int level = EnchantTools.level(helmet, CustomEnchantment.CONDUIT_POWER);
        if (level < 1) {
            return;
        }
        
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, ArmorEffects.PERIOD * 2, level-1));
    }
}