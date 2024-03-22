package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class MutuallyExclusiveChestplateEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveChestplateEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveChestplateEnchantment() {
        if (!mutuallyExclusiveChestplateEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveChestplateEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveChestplateEnchantment(other);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isChestplate(item.getType());
    }

    public static boolean isMutuallyExclusiveChestplateEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveChestplateEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveChestplateEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}