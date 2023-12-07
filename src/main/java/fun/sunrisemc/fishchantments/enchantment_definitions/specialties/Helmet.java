package fun.sunrisemc.fishchantments.enchantment_definitions.specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.CustomEnchantment;
import fun.sunrisemc.fishchantments.Settings;
import fun.sunrisemc.fishchantments.util.EnchantUtil;
import fun.sunrisemc.fishchantments.util.InventoryUtil;

public class Helmet {

    public static class Sustenance extends Enchantment {

        public static final String NAME = "Sustenance";

        public Sustenance(NamespacedKey key) {
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
            if (name.equals(Gills.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }

        public static void onHungerLoss(Player player, ItemStack helmet, FoodLevelChangeEvent event) {
            if (!EnchantUtil.has(helmet, CustomEnchantment.SUSTENANCE)) return;
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
            if (name.equals(Sustenance.NAME)) return true;
            if (name.equals(Gills.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }

        public static void onSuffocate(Player player, ItemStack helmet, EntityDamageEvent event) {
            if (!EnchantUtil.has(helmet, CustomEnchantment.WORM)) return;
            event.setCancelled(true);
        }
    }

    public static class Gills extends Enchantment {

        public static final String NAME = "Gills";

        public Gills(NamespacedKey key) {
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
            if (name.equals(Sustenance.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }

        public static void onTimer(Player player, ItemStack helmet) {
            if (!EnchantUtil.has(helmet, CustomEnchantment.WATER_BREATHING)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, 0));
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
            if (name.equals(Sustenance.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(Gills.NAME)) return true;
            if (name.equals(ConduitPower.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }

        public static void onTimer(Player player, ItemStack helmet) {
            if (!EnchantUtil.has(helmet, CustomEnchantment.NIGHT_VISION)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900 + Settings.ARMOR_EFFECTS_PERIOD_TICKS, 0));
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
            if (name.equals(Sustenance.NAME)) return true;
            if (name.equals(Worm.NAME)) return true;
            if (name.equals(Gills.NAME)) return true;
            if (name.equals(NightVision.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }

        public static void onTimer(Player player, ItemStack helmet) {
            int level = EnchantUtil.level(helmet, CustomEnchantment.CONDUIT_POWER);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
        }
    }
}