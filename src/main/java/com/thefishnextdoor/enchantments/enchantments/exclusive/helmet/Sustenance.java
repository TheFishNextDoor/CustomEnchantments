package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

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
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void modifyFood(Player player, FoodLevelChangeEvent event) {
        if (!EnchantUtil.has(player.getInventory().getHelmet(), CustomEnchantment.SUSTENANCE)) return;
        int food = event.getFoodLevel() + 2;
        if (food > 20) food = 20;
        event.setFoodLevel(food);
    }
}