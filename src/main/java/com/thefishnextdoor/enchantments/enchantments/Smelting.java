package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.CraftingUtil;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

import java.util.Collection;
import java.util.List;

import org.bukkit.NamespacedKey;

public class Smelting extends Enchantment {

    public Smelting(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isTool(item.getType());
    }

    public static void onBlockDropItems(Player player, List<Item> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.SMELTING)) return;
        for (Item drop : drops) {
            CraftingUtil.smelt(drop);
        }
    }

    public static void onBlockDropItems(Player player, Collection<ItemStack> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.SMELTING)) return;
        for (ItemStack drop : drops) {
            CraftingUtil.smelt(drop);
        }
    }
}
