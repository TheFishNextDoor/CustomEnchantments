package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public abstract class MutuallyExclusiveWeaponEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveWeaponEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveWeaponEnchantment(NamespacedKey key) {
        super(key);
        if (!mutuallyExclusiveWeaponEnchantmentLookup.contains(EnchantTools.name(this))) {
            mutuallyExclusiveWeaponEnchantmentLookup.add(EnchantTools.name(this));
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
        return mutuallyExclusiveWeaponEnchantmentLookup.contains(EnchantTools.name(enchantment));
    }
}