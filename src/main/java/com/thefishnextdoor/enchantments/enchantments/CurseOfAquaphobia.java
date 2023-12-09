package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.WorldUtil;

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
        boolean inRain = WorldUtil.isRaining(player.getLocation().getBlock()) && !WorldUtil.underBlock(player);
        if (inWater || inRain) player.damage(1);
    }
}