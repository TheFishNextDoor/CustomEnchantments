package com.thefishnextdoor.enchantments.enchantments.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Bounce extends Enchantment {

    public Bounce(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Bounce";
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
        if (EnchantUtil.same(other, CustomEnchantment.LEAPING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.SLOW_FALLING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.ANCHOR)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isBoots(item.getType());
    }

    public static void onFall(Player player, ItemStack boots, EntityDamageEvent event) {
        int level = EnchantUtil.level(boots, CustomEnchantment.BOUNCE);
        if (level < 1) return;
        double v = Math.log(event.getFinalDamage()) * (level + 2) / 10;
        if (v > 10) v = 10;
        player.setVelocity(player.getVelocity().setY(v));
        event.setDamage(0);
        event.setCancelled(true);
    }
}