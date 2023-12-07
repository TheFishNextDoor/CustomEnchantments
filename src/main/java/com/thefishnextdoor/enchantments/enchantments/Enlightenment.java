package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Enlightenment extends Enchantment {

    public static final String NAME = "Enlightenment";

    public Enlightenment(NamespacedKey key) {
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
        return EnchantmentTarget.BREAKABLE;
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
        return InventoryUtil.isWeapon(item.getType()) || InventoryUtil.isTool(item.getType());
    }

    public static void onMobXp(Player player, EntityDeathEvent event) {
        int level = EnchantUtil.handLevel(player, CustomEnchantment.ENLIGHTENMENT);
        if (level < 1) return;
        event.setDroppedExp(xp(level, event.getDroppedExp()));
    }

    public static void onBlockXp(Player player, BlockBreakEvent event) {
        int level = EnchantUtil.handLevel(player, CustomEnchantment.ENLIGHTENMENT);
        if (level < 1) return;
        event.setExpToDrop(xp(level, event.getExpToDrop()));
    }

    private static int xp(int level, int xp) {
        return xp + (int) Math.round(xp * (level * 0.1));
    }
}