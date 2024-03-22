package com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

public class Teleport extends ArrowTransformEnchantment {

    @Override
    public String getName() {
        return "Teleport";
    }

    @Override
    public String getDescription() {
        return "Teleport to the location your projectile hits. Rare drop from enderman.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
    
    @Override
    public boolean isCursed() {
        return false;
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.TELEPORT)) {
            EntityTools.convert(projectile, EntityType.ENDER_PEARL);
        }
    }
}