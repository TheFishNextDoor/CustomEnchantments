package com.thefishnextdoor.customenchantments.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtil {

    public static ItemStack getMeleeItemInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (mainHand.getType() != Material.AIR) return mainHand;
        if (offHand.getType() != Material.AIR) return offHand;
        return mainHand;
    }

    public static ItemStack getShieldInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (mainHand.getType() == Material.SHIELD) return mainHand;
        if (offHand.getType() == Material.SHIELD) return offHand;
        return null;
    }

    public static ItemStack getHoeInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (MaterialUtil.isHoe(mainHand.getType())) return mainHand;
        if (MaterialUtil.isHoe(offHand.getType())) return offHand;
        return null;
    }

    public static ItemStack getRangedItemInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (MaterialUtil.isRanged(mainHand.getType())) return mainHand;
        if (MaterialUtil.isRanged(offHand.getType())) return offHand;
        return null;
    }
    
}
