package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;

public class Enlightenment extends CustomEnchantment {

    public Enlightenment(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Enlightenment";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
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
        if (item == null) {
            return false;
        }
        return MaterialTools.isWeapon(item.getType()) || MaterialTools.isTool(item.getType());
    }

    @Override
    public String getDescription() {
        return "Increased Xp drops from blocks and mobs. Rare drop from warden.";
    }

    public static void modifyXp(Player player, EntityDeathEvent event) {
        int level = EnchantTools.meleeLevel(player, CustomEnchantment.ENLIGHTENMENT);
        if (level < 1) {
            return;
        }
        event.setDroppedExp(xp(level, event.getDroppedExp()));
    }

    public static void modifyXp(Player player, BlockBreakEvent event) {
        int level = EnchantTools.meleeLevel(player, CustomEnchantment.ENLIGHTENMENT);
        if (level < 1) {
            return;
        }
        event.setExpToDrop(xp(level, event.getExpToDrop()));
    }

    private static int xp(int level, int xp) {
        return xp + (int) Math.round(xp * (level * 0.1));
    }
}