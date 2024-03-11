package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Salmon;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.EntityTag;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

public class SalmonSlinger extends ArrowTransformEnchantment {

    public SalmonSlinger(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Salmon Slinger";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantTools.same(other, Enchantment.ARROW_INFINITE)) {
            return true;
        }
        return CustomEnchantment.isMutuallyExclusiveWeaponEnchantment(other);
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into salmon. This enchantment is not dropped by any mob.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.SALMON_SLINGER)) {
            Salmon salmon = (Salmon) EntityTools.convert(projectile, EntityType.SALMON);
            salmon.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 0));
            EntityTag.NO_DROPS.applyTo(salmon);
        }
    }
}