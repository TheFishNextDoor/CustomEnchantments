package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Glass extends MutuallyExclusiveWeaponEnchantment {

    @Override
    public String getName() {
        return "Glass";
    }

    @Override
    public String getDescription() {
        return "Increased damage, decreased durability. Rare drop from skeleton.";
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
        if (EnchantTools.same(other, Enchantment.DURABILITY)) {
            return true;
        }
        if (EnchantTools.same(other, Enchantment.MENDING)) {
            return true;
        }
        if (EnchantTools.same(other, CustomEnchantment.UNBREAKABLE)){
            return true;
        }
        return isMutuallyExclusiveWeaponEnchantment(other);
    }

    public static void modifyDamage(Player player, EntityDamageByEntityEvent event, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.GLASS, ranged);
        if (level < 1) {
            return;
        }
        event.setDamage(event.getDamage() * 1.5);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isWeapon(item.getType());
    }

    public static void modifyDamage(Player player, ItemStack item, PlayerItemDamageEvent event) {
        if (EnchantTools.has(item, CustomEnchantment.GLASS)) {
            event.setDamage(event.getDamage() * 32);
        }
    }
}