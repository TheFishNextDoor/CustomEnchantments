package com.thefishnextdoor.customenchantments.nms;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import org.bukkit.NamespacedKey;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;

import net.minecraft.core.registries.BuiltInRegistries;

public class NMS {

    public static void unfreezeEnchantmentRegistry() {
        setFieldValue(BuiltInRegistries.ENCHANTMENT, "l", false);
        setFieldValue(BuiltInRegistries.ENCHANTMENT, "m", new IdentityHashMap<>());
    }

    public static void freezeEnchantmentRegistry() {
        BuiltInRegistries.ENCHANTMENT.freeze();
    }

    public static org.bukkit.enchantments.Enchantment registerEnchantment(CustomEnchantment enchantment) {
        NMSEnchantment nmsEnchantment = new NMSEnchantment(enchantment);

        net.minecraft.core.Registry.register(BuiltInRegistries.ENCHANTMENT, enchantment.getKey().getKey(), nmsEnchantment);

        NamespacedKey key = NamespacedKey.minecraft(enchantment.getKey().getKey());
        org.bukkit.enchantments.Enchantment bukkitEnchantment = org.bukkit.Registry.ENCHANTMENT.get(key);
        if (bukkitEnchantment == null) {
            throw new IllegalStateException("Failed to register enchantment " + enchantment.getKey());
        }
        return bukkitEnchantment;
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
}
