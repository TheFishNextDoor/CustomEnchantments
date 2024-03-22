package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class CurseOfIronGrip extends CustomEnchantment {

    @Override
    public String getName() {
        return "Curse of Iron Grip";
    }

    @Override
    public String getDescription() {
        return "Prevents the holder from dropping the item. Rare drop from witch.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        Material type = item.getType();
        return MaterialTools.isWeapon(type) || MaterialTools.isTool(type);
    }

    public static void modifyCancelStatus(Player player, ItemStack item, PlayerDropItemEvent event) {
        if (EnchantTools.has(item, CustomEnchantment.CURSE_OF_IRON_GRIP)) {
            event.setCancelled(true);
        }
    }
}
