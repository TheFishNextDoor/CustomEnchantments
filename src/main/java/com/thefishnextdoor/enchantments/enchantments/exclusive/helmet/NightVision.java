package com.thefishnextdoor.enchantments.enchantments.exclusive.helmet;

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

public class NightVision extends Enchantment {

    public NightVision(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Night Vision";
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
        if (EnchantUtil.same(other, CustomEnchantment.CONDUIT_POWER)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isHelmet(item.getType());
    }

    public static void onTimer(Player player, ItemStack helmet) {
        if (!EnchantUtil.has(helmet, CustomEnchantment.NIGHT_VISION)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900 + Settings.ARMOR_EFFECTS_PERIOD_TICKS, 0));
    }
}