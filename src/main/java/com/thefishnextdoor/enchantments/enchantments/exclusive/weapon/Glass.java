package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Glass extends MutuallyExclusiveWeaponEnchantment {

    public Glass(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Glass";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantUtil.same(other, Enchantment.DURABILITY)) return true;
        if (EnchantUtil.same(other, Enchantment.MENDING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.UNBREAKABLE)) return true;
        return CustomEnchantment.isMutuallyExclusiveWeaponEnchantment(other);
    }

    public static void modifyDamage(Player player, EntityDamageByEntityEvent event, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.GLASS, ranged);
        if (level < 1) return;
        event.setDamage(event.getDamage() * 1.5);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Increased damage, decreased durability. Rare drop from skeleton.";
    }

    public static void modifyDamage(Player player, ItemStack item, PlayerItemDamageEvent event) {
        if (!EnchantUtil.has(item, CustomEnchantment.GLASS)) return;
        event.setDamage(event.getDamage() * 32);
    }
}