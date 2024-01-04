package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.entity.AbstractArrow.PickupStatus;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.EntityTools;

public class Harpoon extends ArrowTransformEnchantment {

    public Harpoon(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Harpoon";
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
    public String getDescription() {
        return "Launched projectiles are transformed into tridents. Rare drop from drowned.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.HARPOON)) {
            Trident trident = (Trident) EntityTools.convert(projectile, EntityType.TRIDENT);
            trident.setPickupStatus(PickupStatus.DISALLOWED);
        }
    }
}
