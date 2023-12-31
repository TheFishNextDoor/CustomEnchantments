package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.ArmorEffects.ArmorCheckOptimizer;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.MaterialUtil;

public class CurseOfRadiance extends CustomEnchantment {

    public CurseOfRadiance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Curse of Radiance";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
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
        return "Wearer will begin to glow. Rare drop from magma cube.";
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        if (!EnchantUtil.wearing(player, CustomEnchantment.CURSE_OF_RADIANCE, o)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, ArmorEffects.PERIOD * 2, 0));
    }
}