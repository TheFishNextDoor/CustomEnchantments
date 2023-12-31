package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.MaterialUtil;

public class Starving extends MutuallyExclusiveWeaponEnchantment {

    public Starving(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Starving";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return MaterialUtil.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Attacked entities receive hunger. Rare drop from husk.";
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.STARVING, ranged);
        if (level < 1) return;
        int strength = level/2;
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40 + (level * 20)/(strength + 1), strength));
    }
}