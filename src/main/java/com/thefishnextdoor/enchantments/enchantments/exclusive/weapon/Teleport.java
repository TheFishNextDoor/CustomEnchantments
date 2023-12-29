package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class Teleport extends ArrowTransformEnchantment {

    public Teleport(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Teleport";
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
        return "Teleport to the location your projectile hits. Rare drop from enderman.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.TELEPORT)) return;
        EntityUtil.convert(projectile, EntityType.ENDER_PEARL);
    }
}