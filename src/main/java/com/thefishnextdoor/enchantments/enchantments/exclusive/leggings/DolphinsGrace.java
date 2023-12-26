package com.thefishnextdoor.enchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveLeggingsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class DolphinsGrace extends MutuallyExclusiveLeggingsEnchantment {

    public DolphinsGrace(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Dolphins Grace";
    }

    @Override
    public int getMaxLevel() {
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
        return other instanceof MutuallyExclusiveLeggingsEnchantment;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isLeggings(item.getType());
    }

    public static void onTimer(Player player, ItemStack leggings) {
        if (!EnchantUtil.has(leggings, CustomEnchantment.DOLPHINS_GRACE)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Timer.PERIOD * 2, 0));
    }
}