package com.thefishnextdoor.customenchantments.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Radiance extends CustomEnchantment {

    @Override
    public String getName() {
        return "Radiance";
    }

    @Override
    public String getDescription() {
        return "Attacked entities receive glowing. Rare drop from magma cube.";
    }


    @Override
    public int getMaxLevel() {
        return 5;
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
        return MaterialTools.isWeapon(item.getType());
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.RADIANCE, ranged);
        if (level < 1) {
            return;
        }
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0));
    }
}