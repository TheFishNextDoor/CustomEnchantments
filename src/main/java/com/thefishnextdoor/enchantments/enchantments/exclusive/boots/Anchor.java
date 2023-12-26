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
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Anchor extends MutuallyExclusiveBootsEnchantment {

    public Anchor(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Anchor";
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
    public boolean conflictsWith(Enchantment other) {
        return other instanceof MutuallyExclusiveBootsEnchantment;
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Timer.PERIOD * 2, 2));
    }
}