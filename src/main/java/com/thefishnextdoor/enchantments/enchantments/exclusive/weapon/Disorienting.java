package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Disorienting extends MutuallyExclusiveWeaponEnchantment {

    public Disorienting(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Disorienting";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.DISORIENTING, ranged);
        if (level < 1) return;
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0));
    }
}