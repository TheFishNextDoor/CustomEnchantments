package com.thefishnextdoor.customenchantments.enchantments.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;

public class Sustenance extends MutuallyExclusiveHelmetEnchantment {

    public Sustenance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Sustenance";
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
        return "Hunger bar replenishes over time. Rare drop from zombie.";
    }

    public static void modifyFood(Player player, FoodLevelChangeEvent event) {
        if (!EnchantUtil.has(player.getInventory().getHelmet(), CustomEnchantment.SUSTENANCE)) return;
        int food = event.getFoodLevel() + 2;
        if (food > 20) food = 20;
        event.setFoodLevel(food);
    }
}