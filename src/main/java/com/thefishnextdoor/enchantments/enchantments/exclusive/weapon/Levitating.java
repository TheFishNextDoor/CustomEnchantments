package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;

public class Levitating extends MutuallyExclusiveWeaponEnchantment {

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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        Material type = item.getType();
        return type == Material.BOW || type == Material.CROSSBOW;
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRanged(player, CustomEnchantment.LEVITATING)) return;
        EntityUtil.convert(projectile, EntityType.SHULKER_BULLET);

    }
}