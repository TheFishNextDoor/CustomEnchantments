package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class FireBlast extends ArrowTransformEnchantment {

    public FireBlast(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fire Blast";
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
        if (item == null) return false;
        Material type = item.getType();
        return type == Material.CROSSBOW;
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into a blaze fire ball. Rare drop from blaze.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.FIRE_BLAST)) return;
        EntityUtil.convert(projectile, EntityType.SMALL_FIREBALL);
    }
}