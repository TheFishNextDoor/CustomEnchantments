package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class MutuallyExclusiveHelmetEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveHelmetEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveHelmetEnchantment(NamespacedKey key) {
        super(key);
        if (!mutuallyExclusiveHelmetEnchantmentLookup.contains(EnchantTools.name(this))) {
            mutuallyExclusiveHelmetEnchantmentLookup.add(EnchantTools.name(this));
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
        return mutuallyExclusiveHelmetEnchantmentLookup.contains(EnchantTools.name(enchantment));
    }
}