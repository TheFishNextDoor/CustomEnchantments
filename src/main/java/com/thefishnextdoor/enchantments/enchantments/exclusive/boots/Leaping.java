package com.thefishnextdoor.enchantments.enchantments.exclusive.boots;

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

public class Leaping extends Enchantment {

    public Leaping(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Leaping";
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
        return EnchantmentTarget.ARMOR_FEET;
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
        if (EnchantUtil.same(other, CustomEnchantment.CRUSH)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.SLOW_FALLING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.ANCHOR)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.BOUNCE)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isBoots(item.getType());
    }

    public static void onTimer(Player player, ItemStack boots) {
        int level = EnchantUtil.level(boots, CustomEnchantment.LEAPING);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
    }
}