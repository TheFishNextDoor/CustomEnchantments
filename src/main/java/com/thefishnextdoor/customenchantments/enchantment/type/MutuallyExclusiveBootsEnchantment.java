package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class MutuallyExclusiveBootsEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveBootsEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveBootsEnchantment() {
        if (!mutuallyExclusiveBootsEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveBootsEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveBootsEnchantment(other);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isBoots(item.getType());
    }

    public static boolean isMutuallyExclusiveBootsEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveBootsEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveBootsEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}