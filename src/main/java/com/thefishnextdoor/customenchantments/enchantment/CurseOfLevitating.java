package com.thefishnextdoor.customenchantments.enchantment;

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
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

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

    @Override
    public String getDescription() {
        return "Wearer levitates uncontrollably. Rare drop from shulkers.";
    }

    public static void onTimer(Player player, ArmorCheckOptimizer o) {
        int level = EnchantTools.armorLevel(player, CustomEnchantment.CURSE_OF_LEVITATING, o);
        if (level < 1) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, ArmorEffects.PERIOD * 2, level-1));
    }
}