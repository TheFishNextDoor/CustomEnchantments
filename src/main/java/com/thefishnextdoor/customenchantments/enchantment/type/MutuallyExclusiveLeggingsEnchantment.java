package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class MutuallyExclusiveLeggingsEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveLeggingsEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveLeggingsEnchantment() {
        if (!mutuallyExclusiveLeggingsEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveLeggingsEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveLeggingsEnchantment(other);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_LEGS;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isLeggings(item.getType());
    }

    public static boolean isMutuallyExclusiveLeggingsEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveLeggingsEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveLeggingsEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}