package com.thefishnextdoor.customenchantments.enchantment.type;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public abstract class MutuallyExclusiveElytraEnchantment extends CustomEnchantment {

    private static HashSet<String> mutuallyExclusiveElytraEnchantmentLookup = new HashSet<>();

    public MutuallyExclusiveElytraEnchantment(NamespacedKey key) {
        super(key);
        if (!mutuallyExclusiveElytraEnchantmentLookup.contains(EnchantTools.name(this))) {
            mutuallyExclusiveElytraEnchantmentLookup.add(EnchantTools.name(this));
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
        return mutuallyExclusiveElytraEnchantmentLookup.contains(EnchantTools.name(enchantment));
    }
}