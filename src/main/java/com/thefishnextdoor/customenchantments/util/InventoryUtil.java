package com.thefishnextdoor.customenchantments.util;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtil {

    public static ItemStack getMeleeItemInUse(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHand = inventory.getItemInMainHand();
        ItemStack offHand = inventory.getItemInOffHand();
        if (mainHand.getType() != Material.AIR) return mainHand;
        if (offHand.getType() != Material.AIR) return offHand;
        return mainHand;
    }

    public static ItemStack getShieldInUse(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHand = inventory.getItemInMainHand();
        ItemStack offHand = inventory.getItemInOffHand();
        if (mainHand.getType() == Material.SHIELD) return mainHand;
        if (offHand.getType() == Material.SHIELD) return offHand;
        return null;
    }

    public static ItemStack getHoeInUse(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHand = inventory.getItemInMainHand();
        ItemStack offHand = inventory.getItemInOffHand();
        if (MaterialUtil.isHoe(mainHand.getType())) return mainHand;
        if (MaterialUtil.isHoe(offHand.getType())) return offHand;
        return null;
    }

    public static ItemStack getRangedItemInUse(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHand = inventory.getItemInMainHand();
        ItemStack offHand = inventory.getItemInOffHand();
        if (MaterialUtil.isRanged(mainHand.getType())) return mainHand;
        if (MaterialUtil.isRanged(offHand.getType())) return offHand;
        return null;
    }

    public static boolean take(Player player, Material material, int amount) {
        if (!has(player, material, amount)) return false;
        int remaining = amount;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                if (item.getAmount() > remaining) {
                    item.setAmount(item.getAmount() - remaining);
                    return true;
                }
                else {
                    remaining -= item.getAmount();
                    item.setAmount(0);
                }
            }
        }
        return true;
    }

    public static boolean has(Player player, Material material, int amount) {
        return count(player, material) >= amount;
    }

    public static int count(Player player, Material material) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) count += item.getAmount();
        }
        return count;
    }

    public static void give(Player player, ItemStack item) {
        HashMap<Integer, ItemStack> excessItems = player.getInventory().addItem(item);
        if (excessItems.isEmpty()) return;
        Iterator<ItemStack> iter = excessItems.values().iterator();
        while (iter.hasNext()) {
            player.getWorld().dropItem(player.getLocation(), iter.next());
        }
    }    
}
