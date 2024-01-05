package com.thefishnextdoor.customenchantments.tools;

import org.bukkit.Material;

public class MaterialTools {

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
        return isMeleeWeapon(material) || isRangedWeapon(material);
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
        switch (material) {
            case BOW:
            case CROSSBOW:
            case TRIDENT:
            case FISHING_ROD:
            case SNOWBALL:
            case EGG:
            case ENDER_PEARL:
            case EXPERIENCE_BOTTLE:
            case SPLASH_POTION:
            case LINGERING_POTION:
                return true;
            default:
                return false;
        }
    }

    public static boolean isRangedWeapon(Material material) {
        return firesArrows(material) || material == Material.TRIDENT;
    }

    public static boolean firesArrows(Material material) {
        return material == Material.BOW || material == Material.CROSSBOW;
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

    public static boolean isLog(Material type) {
        switch (type) {
            case OAK_LOG:
            case BIRCH_LOG:
            case SPRUCE_LOG:
            case JUNGLE_LOG:
            case ACACIA_LOG:
            case DARK_OAK_LOG:
            case MANGROVE_LOG:
            case CHERRY_LOG:
            case CRIMSON_STEM:
            case WARPED_STEM:
                return true;
            default:
                return false;
        }
    }

    public static boolean isLeaves(Material type) {
        switch (type) {
            case OAK_LEAVES:
            case BIRCH_LEAVES:
            case SPRUCE_LEAVES:
            case JUNGLE_LEAVES:
            case ACACIA_LEAVES:
            case DARK_OAK_LEAVES:
            case MANGROVE_LEAVES:
            case CHERRY_LEAVES:
            case CRIMSON_FUNGUS:
            case WARPED_FUNGUS:
                return true;
            default:
                return false;
        }
    }
}