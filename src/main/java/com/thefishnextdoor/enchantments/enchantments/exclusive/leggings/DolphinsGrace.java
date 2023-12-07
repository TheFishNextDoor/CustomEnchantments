package com.thefishnextdoor.enchantments.enchantments.exclusive.leggings;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class DolphinsGrace extends Enchantment {

    public static final String NAME = "Dolphins Grace";

    public DolphinsGrace(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
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
    @SuppressWarnings("deprecation")
    public boolean conflictsWith(Enchantment other) {
        String name = other.getName();
        if (name.equals(Swiftness.NAME)) return true;
        if (name.equals(Heavy.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isLeggings(item.getType());
    }

    public static void onTimer(Player player, ItemStack leggings) {
        if (!EnchantUtil.has(leggings, CustomEnchantment.DOLPHINS_GRACE)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, 0));
    }
}