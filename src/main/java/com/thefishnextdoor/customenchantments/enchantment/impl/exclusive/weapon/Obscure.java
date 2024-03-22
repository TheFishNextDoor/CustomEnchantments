package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Obscure extends MutuallyExclusiveWeaponEnchantment {

    @Override
    public String getName() {
        return "Obscure";
    }

    @Override
    public String getDescription() {
        return "Attacked entities receive blindness. Rare drop from warden.";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isCursed() {
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
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.OBSCURE, ranged);
        if (level < 1) {
            return;
        }
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0));
    }
}