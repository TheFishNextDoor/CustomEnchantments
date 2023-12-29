package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class Levitating extends ArrowTransformEnchantment {

    public Levitating(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Levitating";
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
        return "Launched projectiles are transformed into a shulker bullet. Rare drop from shulker.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.LEVITATING)) return;
        EntityUtil.convert(projectile, EntityType.SHULKER_BULLET);

    }
}