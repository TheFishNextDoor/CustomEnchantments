package fun.sunrisemc.fishchantments.Enchantments.Specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class Helmet {

    public static class Food extends Enchantment {

        public static final String NAME = "Sustenance";

        public Food(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(WaterBreathing.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHelmet(item.getType());
        }

        public static void onHungerLoss(Plugin plugin, Player player, ItemStack item, FoodLevelChangeEvent event) {
            if (!Utl.Ench.hasEnchant(item, plugin.FOOD)) return;
            event.setFoodLevel(20);
        }
    }

    public static class Worm extends Enchantment {

        public static final String NAME = "Worm";

        public Worm(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Food.NAME)) return true;
            if (name.equals(WaterBreathing.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHelmet(item.getType());
        }

        public static void onSuffocate(Plugin plugin, Player player, ItemStack item, EntityDamageEvent event) {
            if (!Utl.Ench.hasEnchant(item, plugin.WORM)) return;
            event.setCancelled(true);
        }
    }

    public static class WaterBreathing extends Enchantment {

        public static final String NAME = "Gills";

        public WaterBreathing(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Food.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHelmet(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            if (!Utl.Ench.hasEnchant(helmet, plugin.WATER_BREATHING)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 120, 0));
        }
    }

    public static class NightVision extends Enchantment {

        public static final String NAME = "Night Vision";

        public NightVision(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 1;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Food.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(WaterBreathing.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHelmet(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            if (!Utl.Ench.hasEnchant(helmet, plugin.NIGHT_VISION)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900, 0));
        }
    }

    public static class ConduitPower extends Enchantment {

        public static final String NAME = "Conduit Power";

        public ConduitPower(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 2;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Food.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(WaterBreathing.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHelmet(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            int level = Utl.Ench.getEnchantLevel(helmet, plugin.CONDUIT_POWER);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 120, level-1));
        }
    }
}