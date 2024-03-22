package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class MutuallyExclusiveHelmetEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveHelmetEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveHelmetEnchantment() {
        if (!mutuallyExclusiveHelmetEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveHelmetEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveHelmetEnchantment(other);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isHelmet(item.getType());
    }

    public static boolean isMutuallyExclusiveHelmetEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveHelmetEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveHelmetEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}