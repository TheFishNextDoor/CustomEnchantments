package com.thefishnextdoor.customenchantments.enchantment.type;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public abstract class ArrowTransformEnchantment extends MutuallyExclusiveWeaponEnchantment {

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantTools.same(other, Enchantment.PIERCING)) {
            return true;
        }
        return isMutuallyExclusiveWeaponEnchantment(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.firesArrows(item.getType());
    }
}