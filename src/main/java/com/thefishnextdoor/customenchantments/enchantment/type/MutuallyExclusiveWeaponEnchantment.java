package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import com.thefishnextdoor.customenchantments.CustomEnchantment;

public abstract class MutuallyExclusiveWeaponEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveWeaponEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveWeaponEnchantment() {
        if (!mutuallyExclusiveWeaponEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveWeaponEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveWeaponEnchantment(other);
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    public static boolean isMutuallyExclusiveWeaponEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveWeaponEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveWeaponEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}