package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Spurs extends Enchantment {

    public Spurs(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Spurs";
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
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isBoots(item.getType());
    }

    public static void onTimer(Player player, ItemStack boots) {
        if (player.getVehicle() == null || !(player.getVehicle() instanceof LivingEntity)) return;
        LivingEntity mount = (LivingEntity) player.getVehicle();
        int level = EnchantUtil.level(boots, CustomEnchantment.SPURS);
        if (level < 1) return;
        mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level - 1));
        mount.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level - 1));
    }
}