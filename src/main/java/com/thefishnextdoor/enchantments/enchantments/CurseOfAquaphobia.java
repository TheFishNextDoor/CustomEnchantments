package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer.ArmorCheckOptimizer;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class CurseOfAquaphobia extends CustomEnchantment {

    public CurseOfAquaphobia(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Curse of Aquaphobia";
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
        if (item == null) return false;
        return InventoryUtil.isArmor(item.getType());
    }

    @Override
    public String getDescription() {
        return "Wearer takes damage from water and rain. Rare drop from enderman.";
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_AQUAPHOBIA, o);
        if (level < 1) return;
        if (!(player.isInWater() || (isRaining(player.getLocation().getBlock()) && !underBlock(player)))) return; 
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