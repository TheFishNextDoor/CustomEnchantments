package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.entity.AbstractArrow.PickupStatus;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

public class Harpoon extends ArrowTransformEnchantment {

    @Override
    public String getName() {
        return "Harpoon";
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into tridents. Rare drop from drowned.";
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
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.HARPOON)) {
            Trident trident = (Trident) EntityTools.convert(projectile, EntityType.TRIDENT);
            trident.setPickupStatus(PickupStatus.DISALLOWED);
        }
    }
}
