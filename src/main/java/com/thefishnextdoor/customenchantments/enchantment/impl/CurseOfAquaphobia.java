package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.ArmorEffects.ArmorCheckOptimizer;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class CurseOfAquaphobia extends CustomEnchantment {

    @Override
    public String getName() {
        return "Curse of Aquaphobia";
    }

    @Override
    public String getDescription() {
        return "Wearer takes damage from water and rain. Rare drop from enderman.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEARABLE;
    }

    @Override
    public boolean isCursed() {
        return true;
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
        return MaterialTools.isArmor(item.getType());
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        int level = EnchantTools.armorLevel(player, CustomEnchantment.CURSE_OF_AQUAPHOBIA, o);
        if (level < 1) {
            return;
        }
        if (!(player.isInWater() || (isRaining(player.getLocation().getBlock()) && !underBlock(player)))) {
            return;
        }
        player.damage(1);
    }

    private static boolean underBlock(Player player) {
        return player.getWorld().getHighestBlockYAt(player.getLocation()) > player.getLocation().getY();
    }

    private static boolean isRaining(Block block) {
        return block.getWorld().hasStorm() && canRain(block);
    }

    private static boolean canRain(Block block) {
        double temp = block.getTemperature();
        return temp < 0.95 && temp > 0.15;
    }
}