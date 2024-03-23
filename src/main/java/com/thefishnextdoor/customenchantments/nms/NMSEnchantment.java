package com.thefishnextdoor.customenchantments.nms;

import org.bukkit.craftbukkit.v1_20_R3.enchantments.CraftEnchantment;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NMSEnchantment extends net.minecraft.world.item.enchantment.Enchantment {

    private CustomEnchantment enchantment;

    NMSEnchantment(CustomEnchantment enchantment) {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, null);
        this.enchantment = enchantment;
    }

    @Override
    public int getMinLevel() {
        return this.enchantment.getStartLevel();
    }

    @Override
    public int getMaxLevel() {
        return this.enchantment.getMaxLevel();
    }

    protected boolean checkCompatibility(net.minecraft.world.item.enchantment.Enchantment nmsEnchantment) {
        return !enchantment.conflictsWith(CraftEnchantment.minecraftToBukkit(nmsEnchantment));
    }

    @Override
    public boolean canEnchant(ItemStack nmsItem) {
        return this.enchantment.canEnchantItem(CraftItemStack.asBukkitCopy(nmsItem));
    }

    @Override
    public boolean isTreasureOnly() {
        return this.enchantment.isTreasure();
    }

    @Override
    public boolean isCurse() {
        return this.enchantment.isCursed();
    }

    @Override
    public boolean isDiscoverable() {
        return !this.enchantment.isTreasure();
    }
}