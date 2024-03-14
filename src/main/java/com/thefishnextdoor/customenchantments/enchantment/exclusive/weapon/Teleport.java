package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

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
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.TELEPORT)) {
            EntityTools.convert(projectile, EntityType.ENDER_PEARL);
        }
    }
}