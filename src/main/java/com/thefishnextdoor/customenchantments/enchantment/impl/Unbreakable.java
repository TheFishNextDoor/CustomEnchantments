package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Unbreakable extends CustomEnchantment {

    @Override
    public String getName() {
        return "Unbreakable";
    }

    @Override
    public String getDescription() {
        return "Item will not lose durability. Rare drop from wither.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantTools.same(other, CustomEnchantment.GLASS)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isEnchantable(item.getType());
    }

    public static boolean canTakeDamage(Player player, ItemStack item) {
        return !EnchantTools.has(item, CustomEnchantment.UNBREAKABLE);
    }
}