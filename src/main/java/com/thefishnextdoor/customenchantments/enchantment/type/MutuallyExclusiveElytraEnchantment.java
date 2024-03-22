package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;

public abstract class MutuallyExclusiveElytraEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveElytraEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveElytraEnchantment() {
        if (!mutuallyExclusiveElytraEnchantmentLookup.contains(this.getKey().toString())) {
            mutuallyExclusiveElytraEnchantmentLookup.add(this.getKey().toString());
        }
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return isMutuallyExclusiveElytraEnchantment(other);
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
        return item.getType() == Material.ELYTRA;
    }

    public static boolean isMutuallyExclusiveElytraEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveElytraEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return mutuallyExclusiveElytraEnchantmentLookup.contains(enchantment.getKey().toString());
    }
}