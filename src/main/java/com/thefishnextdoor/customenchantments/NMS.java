package com.thefishnextdoor.customenchantments;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NMS {

    public static class NMSEnchantment extends Enchantment {

        private CustomEnchantment enchantment;

        private NMSEnchantment(CustomEnchantment enchantment) {
            super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, null);

        }

        @Override
        public int getMinLevel() {
            return this.enchantment.getStartLevel();
        }
      
        @Override
        public int getMaxLevel() {
            return this.enchantment.getMaxLevel();
        }

        @Override
        public boolean canEnchant(ItemStack nmsItem) {
            return this.enchantment.canEnchantItem(CraftItemStack.asBukkitCopy(nmsItem));
        }

        @Override
        public boolean isTreasureOnly() {
            return this.enchantment.isTreasure();
        }
      
        @SuppressWarnings("deprecation")
        @Override
        public boolean isCurse() {
            return this.enchantment.isCursed();
        }

        @Override
        public boolean isDiscoverable() {
            return !this.enchantment.isTreasure();
        }
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
}
