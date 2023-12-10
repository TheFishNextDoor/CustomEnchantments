package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class CurseOfAquaphobia extends Enchantment {

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
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEARABLE;
    }

    @Override
    public boolean isTreasure() {
        return false;
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

    public static void onTimer(Player player) {
        int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_AQUAPHOBIA);
        if (level < 1) return;
        boolean inWater = player.isInWater();
        boolean inRain = isRaining(player.getLocation().getBlock()) && !underBlock(player);
        if (inWater || inRain) player.damage(1);
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