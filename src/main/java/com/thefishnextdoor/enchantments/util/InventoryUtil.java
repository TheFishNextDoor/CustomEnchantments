package com.thefishnextdoor.enchantments.util;

import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUtil {

    public static void give(Player player, ItemStack item) {
        HashMap<Integer, ItemStack> excessItems = player.getInventory().addItem(item);
        if (excessItems.isEmpty()) return;
        Iterator<ItemStack> iter = excessItems.values().iterator();
        while (iter.hasNext()) {
            player.getWorld().dropItem(player.getLocation(), iter.next());
        }
    }

    public static ItemStack getItemInUse(Player player) {
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
        if (isHoe(mainHand.getType())) return mainHand;
        if (isRanged(mainHand.getType())) return null;
        if (isHoe(offHand.getType())) return offHand;
        return null;
    }

    public static ItemStack getRangedItemInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (isRanged(mainHand.getType())) return mainHand;
        if (isHoe(mainHand.getType())) return null;
        if (isRanged(offHand.getType())) return offHand;
        return null;
    }

    public static boolean isEnchantable(Material material) {
        return isTool(material) || isWeapon(material)  || isArmor(material);
    }

    public static boolean isTool(Material material) {
        return isPickaxe(material) || isShovel(material) || isAxe(material) || isHoe(material);
    }

    public static boolean isPickaxe(Material material) {
        return material.name().endsWith("_PICKAXE"); 
    }

    public static boolean isShovel(Material material) {
        return material.name().endsWith("_SHOVEL"); 
    }

    public static boolean isHoe(Material material) {
        return material.name().endsWith("_HOE"); 
    }

    public static boolean isWeapon(Material material) {
        return isMeleeWeapon(material) || isRanged(material);
    }

    public static boolean isMeleeWeapon(Material material) {
        return isSword(material) || isAxe(material) || material == Material.TRIDENT;
    }

    public static boolean isSword(Material material) {
        return material.name().endsWith("_SWORD");
    }

    public static boolean isAxe(Material material) {
        return material.name().endsWith("_AXE");
    }

    public static boolean isRanged(Material material) {
        return material == Material.BOW || material == Material.CROSSBOW || material == Material.TRIDENT || material == Material.FISHING_ROD;
    }

    public static boolean isArmor(Material material) {
        return isHelmet(material) || isChestplate(material) || isLeggings(material) || isBoots(material);
    }

    public static boolean isHelmet(Material material) {
        return material.name().endsWith("_HELMET");
    }

    public static boolean isChestplate(Material material) {
        return material.name().endsWith("_CHESTPLATE") || material == Material.ELYTRA;
    }

    public static boolean isLeggings(Material material) {
        return material.name().endsWith("_LEGGINGS");
    }

    public static boolean isBoots(Material material) {
        return material.name().endsWith("_BOOTS");
    }
}