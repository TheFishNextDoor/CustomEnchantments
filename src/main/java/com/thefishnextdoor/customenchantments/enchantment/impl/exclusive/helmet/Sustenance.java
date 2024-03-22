package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Sustenance extends MutuallyExclusiveHelmetEnchantment {

    @Override
    public String getName() {
        return "Sustenance";
    }
    
    @Override
    public String getDescription() {
        return "Hunger bar replenishes over time. Rare drop from zombie.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void modifyFood(Player player, FoodLevelChangeEvent event) {
        if (EnchantTools.has(player.getInventory().getHelmet(), CustomEnchantment.SUSTENANCE)) {
            int food = event.getFoodLevel() + 2;
            food = Math.min(food, 20);
            event.setFoodLevel(food);
        }
    }
}