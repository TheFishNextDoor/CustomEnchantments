package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.AbstractArrow.PickupStatus;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Harpoon extends MutuallyExclusiveWeaponEnchantment {

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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Launched projectiles are transformed into tridents. Rare drop from drowned.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRanged(player, CustomEnchantment.HARPOON)) return;
        Trident trident = (Trident) EntityUtil.convert(projectile, EntityType.TRIDENT);
        trident.setPickupStatus(PickupStatus.DISALLOWED);
    }
}
