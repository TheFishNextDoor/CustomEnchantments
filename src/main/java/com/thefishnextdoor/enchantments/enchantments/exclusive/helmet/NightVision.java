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

    public static final String NAME = "Night Vision";

    public NightVision(NamespacedKey key) {
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
    @SuppressWarnings("deprecation")
    public boolean conflictsWith(Enchantment other) {
        String name = other.getName();
        if (name.equals(Sustenance.NAME)) return true;
        if (name.equals(Worm.NAME)) return true;
        if (name.equals(Gills.NAME)) return true;
        if (name.equals(ConduitPower.NAME)) return true;
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