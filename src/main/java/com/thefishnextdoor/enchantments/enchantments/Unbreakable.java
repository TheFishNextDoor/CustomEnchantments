package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.MaterialUtil;

public class Unbreakable extends CustomEnchantment {

    public Unbreakable(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Unbreakable";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantUtil.same(other, CustomEnchantment.GLASS));
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return MaterialUtil.isEnchantable(item.getType());
    }

    @Override
    public String getDescription() {
        return "Item will not lose durability. Rare drop from wither.";
    }

    public static boolean canTakeDamage(Player player, ItemStack item) {
        return !EnchantUtil.has(item, CustomEnchantment.UNBREAKABLE);
    }
}