package fun.sunrisemc.fishchantments.enchantment_definitions;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.CustomEnchantment;
import fun.sunrisemc.fishchantments.Settings;
import fun.sunrisemc.fishchantments.util.EnchantUtil;
import fun.sunrisemc.fishchantments.util.InventoryUtil;
import fun.sunrisemc.fishchantments.util.WorldUtil;

public class Curses {

    public static class CurseOfMiningFatigue extends Enchantment {

        public static final String NAME = "Curse of Mining Fatigue";

        public CurseOfMiningFatigue(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_MINING_FATIGUE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 120, level-1));
        }
    }

    public static class CurseOfSlowness extends Enchantment {

        public static final String NAME = "Curse of Slowness";

        public CurseOfSlowness(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_SLOWNESS);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, level-1));
        }
    }

    public static class CurseOfWeakness extends Enchantment {

        public static final String NAME = "Curse of Weakness";

        public CurseOfWeakness(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_WEAKNESS);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, level-1));
        }
    }

    public static class CurseOfLevitating extends Enchantment {

        public static final String NAME = "Curse of Levitating";

        public CurseOfLevitating(NamespacedKey key) {
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
            return EnchantmentTarget.WEARABLE;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_LEVITATING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
        }
    }

    public static class CurseOfAquaphobia extends Enchantment {

        public static final String NAME = "Curse of Aquaphobia";

        public CurseOfAquaphobia(NamespacedKey key) {
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
            return EnchantmentTarget.WEARABLE;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            int level = EnchantUtil.armorLevel(player, CustomEnchantment.CURSE_OF_AQUAPHOBIA);
            if (level < 1) return;
            boolean inWater = player.isInWater();
            boolean inRain = WorldUtil.raining(player.getLocation().getBlock()) && !WorldUtil.underBlock(player);
            if (inWater || inRain) player.damage(1);
        }
    }

    public static class CurseOfRadiance extends Enchantment {

        public static final String NAME = "Curse of Radiance";

        public CurseOfRadiance(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
        }

        @Override
        public boolean isTreasure() {
            return false;
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
            if (item == null) return false;
            return InventoryUtil.isArmor(item.getType());
        }

        public static void onTimer(Player player) {
            if (!EnchantUtil.wearing(player, CustomEnchantment.CURSE_OF_RADIANCE)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, 0));
        }
    }

}