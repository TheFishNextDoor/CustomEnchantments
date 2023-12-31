package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.ArmorEffects;
import com.thefishnextdoor.enchantments.ArmorEffects.ArmorCheckOptimizer;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.MaterialUtil;

public class Invisibility extends CustomEnchantment {

    public Invisibility(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Invisivility";
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
        return false;
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
        return "Wearer receives invisibility. Rare drop from witch.";
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        if (!EnchantUtil.wearing(player, CustomEnchantment.INVISIBILITY, o)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, ArmorEffects.PERIOD * 2, 0));
    }
}