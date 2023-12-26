package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveHelmetEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

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
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isHelmet(item.getType());
    }

    public static void modifyFood(Player player, FoodLevelChangeEvent event) {
        if (!EnchantUtil.has(player.getInventory().getHelmet(), CustomEnchantment.SUSTENANCE)) return;
        int food = event.getFoodLevel() + 2;
        if (food > 20) food = 20;
        event.setFoodLevel(food);
    }
}