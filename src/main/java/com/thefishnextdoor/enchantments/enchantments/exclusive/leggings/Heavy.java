package com.thefishnextdoor.enchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Plugin;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.WorldUtil;

public class Heavy extends Enchantment {

    public Heavy(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_LEGS;
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
        if (EnchantUtil.same(other, CustomEnchantment.SWIFTNESS)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DOLPHINS_GRACE)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isLeggings(item.getType());
    }

    public static void onPlayerTakeDamage(Plugin plugin, Player player) {
        if (EnchantUtil.has(player.getInventory().getLeggings(), CustomEnchantment.HEAVY)) WorldUtil.cancelKnockback(plugin, player);
    }
}