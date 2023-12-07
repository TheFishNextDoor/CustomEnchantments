package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

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

public class IncreasedHealth extends Enchantment {

    public static final String NAME = "Increased Health";

    public IncreasedHealth(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
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
        if (name.equals(DragonScales.NAME)) return true;
        if (name.equals(Healing.NAME)) return true;
        if (name.equals(FireResistance.NAME)) return true;
        if (name.equals(Strength.NAME)) return true;
        if (name.equals(Haste.NAME)) return true;
        if (name.equals(HeroOfTheVillage.NAME)) return true;
        if (name.equals(DeathWish.NAME)) return true;
        if (name.equals(ProjectileResistance.NAME)) return true;
        if (name.equals(Flaming.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isChestplate(item.getType());
    }

    public static void onTimer(Player player, ItemStack chestplate) {
        int level = EnchantUtil.level(chestplate, CustomEnchantment.INCREASED_HEALTH);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
    }
}