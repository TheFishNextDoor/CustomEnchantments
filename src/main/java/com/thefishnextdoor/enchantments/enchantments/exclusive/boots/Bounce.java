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

    public static final String NAME = "Bounce";

    public Bounce(NamespacedKey key) {
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
        if (name.equals(Anchor.NAME)) return true;
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