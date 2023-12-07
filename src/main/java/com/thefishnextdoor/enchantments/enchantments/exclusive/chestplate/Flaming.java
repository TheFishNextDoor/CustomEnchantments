package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Flaming extends Enchantment {

    public static final String NAME = "Flaming";

    public Flaming(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        if (name.equals(IncreasedHealth.NAME)) return true;
        if (name.equals(HeroOfTheVillage.NAME)) return true;
        if (name.equals(DeathWish.NAME)) return true;
        if (name.equals(ProjectileResistance.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isChestplate(item.getType());
    }

    public static void onEntityAttackPlayer(Player player, LivingEntity entity) {
        int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.FLAMING);
        if (level < 1) return;
        entity.setFireTicks(level * 20);
    }
}