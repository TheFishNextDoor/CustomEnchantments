package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Timer;
import com.thefishnextdoor.enchantments.Timer.ArmorCheckOptimizer;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class CurseOfLevitating extends CustomEnchantment {

    public CurseOfLevitating(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Curse of Levitating";
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

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_LEVITATING, o);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Timer.PERIOD * 2, level-1));
    }
}