package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTools;

public class FireBlast extends ArrowTransformEnchantment {

    @Override
    public String getName() {
        return "Fire Blast";
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into a blaze fire ball. Rare drop from blaze.";
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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        Material type = item.getType();
        return type == Material.CROSSBOW;
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.FIRE_BLAST)) {
            EntityTools.convert(projectile, EntityType.SMALL_FIREBALL);
        }
    }
}