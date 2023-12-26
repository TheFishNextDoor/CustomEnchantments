package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Withering extends MutuallyExclusiveWeaponEnchantment {

    public Withering(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Withering";
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.WITHERING, ranged);
        if (level < 1) return;
        int strength = level/3;
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength));
    }
}