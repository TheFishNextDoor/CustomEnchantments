package com.thefishnextdoor.customenchantments;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import org.bukkit.entity.Trident;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.core.Registry;

public class NMSEnchantment extends Enchantment {

    private CustomEnchantment enchantment;

    private NMSEnchantment(CustomEnchantment enchantment) {
        super(Rarity.VERY_RARE, getNMSCategory(enchantment), new EquipmentSlot[1]);
        this.enchantment = enchantment;
    }

    public static void unfreezeEnchantmentRegistry() {
        setFieldValue(BuiltInRegistries.ENCHANTMENT, "l", false);
        setFieldValue(BuiltInRegistries.ENCHANTMENT, "m", new IdentityHashMap<>());
    }

    public static void freezeEnchantmentRegistry() {
        BuiltInRegistries.ENCHANTMENT.freeze();
    }

    public static void registerEnchantment(CustomEnchantment enchantment) {
        NMSEnchantment nmsEnchantment = new NMSEnchantment(enchantment);
        Registry.register(BuiltInRegistries.ENCHANTMENT, enchantment.getKey().getKey(), nmsEnchantment);
    }

    private static boolean setFieldValue(Object of, String fieldName, Object value) {
        try {
            boolean isStatic = of instanceof Class;
            Class<?> clazz = isStatic ? (Class<?>) of : of.getClass();

            Field field = getField(clazz, fieldName);
            if (field == null) return false;

            field.setAccessible(true);
            field.set(isStatic ? null : of, value);
            return true;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static Field getField(Class<?> of, String fieldName) {
        try {
            return of.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e) {
            Class<?> superClass = of.getSuperclass();
            return superClass == null ? null : getField(superClass, fieldName);
        }
    }

    private static EnchantmentCategory getNMSCategory(CustomEnchantment enchantment) {
        switch(enchantment.getItemTarget()) {
            case ARMOR:
                return EnchantmentCategory.ARMOR;
            case ARMOR_FEET:
                return EnchantmentCategory.ARMOR_FEET;
            case ARMOR_LEGS:
                return EnchantmentCategory.ARMOR_LEGS;
            case ARMOR_TORSO:
                return EnchantmentCategory.ARMOR_CHEST;
            case ARMOR_HEAD:
                return EnchantmentCategory.ARMOR_HEAD;
            case BOW:
                return EnchantmentCategory.BOW;
            case BREAKABLE:
                return EnchantmentCategory.BREAKABLE;
            case CROSSBOW:
                return EnchantmentCategory.CROSSBOW;
            case FISHING_ROD:
                return EnchantmentCategory.FISHING_ROD;
            case TOOL:
                return EnchantmentCategory.DIGGER;
            case TRIDENT:
                return EnchantmentCategory.TRIDENT;
            case VANISHABLE:
                return EnchantmentCategory.VANISHABLE;
            case WEAPON:
                return EnchantmentCategory.WEAPON;
            case WEARABLE:
                return EnchantmentCategory.WEARABLE;
            default:
                return EnchantmentCategory.BREAKABLE;
        }
    }
}
