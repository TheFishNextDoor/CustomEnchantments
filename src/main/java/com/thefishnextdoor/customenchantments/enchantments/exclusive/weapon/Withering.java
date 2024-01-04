package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;

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

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Attacked entities receive wither. Rare drop from the wither.";
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.WITHERING, ranged);
        if (level < 1) {
            return;
        }
        int strength = level/3;
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength));
    }
}