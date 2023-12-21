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
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Swiftness extends Enchantment {

    public Swiftness(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Swiftness";
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
        return CustomEnchantment.isMutuallyExclusiveLeggings(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isLeggings(item.getType());
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantUtil.level(boots, CustomEnchantment.SWIFTNESS);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Timer.PERIOD * 2, level-1));
    }
}