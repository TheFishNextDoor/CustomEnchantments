package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class SalmonSlinger extends MutuallyExclusiveWeaponEnchantment {

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
        if (EnchantUtil.same(other, Enchantment.ARROW_INFINITE)) return true;
        return CustomEnchantment.isMutuallyExclusiveWeaponEnchantment(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into salmon. This enchantment is not dropped by any mob.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.SALMON_SLINGER)) return;
        Entity entity = EntityUtil.convert(projectile, EntityType.SALMON);
        entity.setFallDistance(100);
    }
}