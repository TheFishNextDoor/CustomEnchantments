package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

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

public class ConduitPower extends Enchantment {

    public ConduitPower(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Conduit Power";
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
        return EnchantmentTarget.ARMOR_HEAD;
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
        if (EnchantUtil.same(other, CustomEnchantment.SUSTENANCE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.WORM)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.GILLS)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.NIGHT_VISION)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isHelmet(item.getType());
    }

    public static void onTimer(Player player, ItemStack helmet) {
        int level = EnchantUtil.level(helmet, CustomEnchantment.CONDUIT_POWER);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Timer.PERIOD * 2, level-1));
    }
}