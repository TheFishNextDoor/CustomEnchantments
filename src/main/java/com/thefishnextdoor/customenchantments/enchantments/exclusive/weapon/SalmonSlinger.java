package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.Tag;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.EntityTools;

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
            Entity entity = EntityTools.convert(projectile, EntityType.SALMON);
            entity.setPersistent(false);
            entity.setFallDistance(100);
            Tag.NO_DROPS.applyTo(entity);
        }
    }
}