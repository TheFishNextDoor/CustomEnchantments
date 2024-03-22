package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Flaming extends MutuallyExclusiveChestplateEnchantment {

    @Override
    public String getName() {
        return "Flaming";
    }

    @Override
    public String getDescription() {
        return "Attackers will be lit on fire. Rare drop from blaze.";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onEntityAttackPlayer(Player player, LivingEntity entity) {
        int level = EnchantTools.level(player.getInventory().getChestplate(), CustomEnchantment.FLAMING);
        if (level < 1) {
            return;
        }
        entity.setFireTicks(level * 20);
    }
}