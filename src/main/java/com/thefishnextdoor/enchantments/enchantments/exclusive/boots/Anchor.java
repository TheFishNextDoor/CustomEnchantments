package com.thefishnextdoor.enchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Anchor extends Enchantment {

    public static final String NAME = "Anchor";

    public Anchor(NamespacedKey key) {
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
    @SuppressWarnings("deprecation")
    public boolean conflictsWith(Enchantment other) {
        String name = other.getName();
        if (name.equals(Crush.NAME)) return true;
        if (name.equals(Leaping.NAME)) return true;
        if (name.equals(SlowFalling.NAME)) return true;
        if (name.equals(Bounce.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isBoots(item.getType());
    }

    public static void whenSwimming(Player player) {
        if (!EnchantUtil.has(player.getInventory().getBoots(), CustomEnchantment.ANCHOR)) return;
        Vector velocity = player.getVelocity();
        double y = velocity.getY();
        if (y <= 0) velocity.setY(y - (0.06));
        else velocity.setY(y - (0.03));
        player.setVelocity(velocity);
    }

    public static void onTimer(Player player, ItemStack boots) {
        if (!player.isInWater()) return;
        if (!EnchantUtil.has(player.getInventory().getBoots(), CustomEnchantment.ANCHOR)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, 2));
    }
}