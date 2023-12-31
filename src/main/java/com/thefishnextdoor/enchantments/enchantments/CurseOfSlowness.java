package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.ArmorEffects.ArmorCheckOptimizer;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.MaterialUtil;

public class CurseOfSlowness extends CustomEnchantment {

    public CurseOfSlowness(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Curse of Slowness";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
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
        return MaterialUtil.isArmor(item.getType());
    }

    @Override
    public String getDescription() {
        return "Wearer has decreased movement speed. Rare drop from warden.";
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_SLOWNESS, o);
        if (level < 1) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, level-1));
    }
}