package fun.sunrisemc.fishchantments.enchantments.specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class Chestplate {

    public static class Resistance extends Enchantment {

        public static final String NAME = "Dragon Scales";

        public Resistance(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Nchnt.level(helmet, plugin.RESISTANCE);
            int chestplateLevel = Utl.Nchnt.level(chestplate, plugin.RESISTANCE);
            int leggingsLevel = Utl.Nchnt.level(leggings, plugin.RESISTANCE);
            int bootsLevel = Utl.Nchnt.level(boots, plugin.RESISTANCE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, level-1));
        }
    }

    public static class Regeneration extends Enchantment {

        public static final String NAME = "Healing";

        public Regeneration(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Nchnt.level(helmet, plugin.REGENERATION);
            int chestplateLevel = Utl.Nchnt.level(chestplate, plugin.REGENERATION);
            int leggingsLevel = Utl.Nchnt.level(leggings, plugin.REGENERATION);
            int bootsLevel = Utl.Nchnt.level(boots, plugin.REGENERATION);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, level-1));
        }
    }

    public static class FireResistance extends Enchantment {

        public static final String NAME = "Fire Resistance";

        public FireResistance(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Nchnt.has(helmet, plugin.FIRE_RESISTANCE) || Utl.Nchnt.has(chestplate, plugin.FIRE_RESISTANCE) || Utl.Nchnt.has(leggings, plugin.FIRE_RESISTANCE) || Utl.Nchnt.has(boots, plugin.FIRE_RESISTANCE))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 0));
        }
    }

    public static class Strength extends Enchantment {

        public static final String NAME = "Strength";

        public Strength(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack chestplate) {
            int level = Utl.Nchnt.level(chestplate, plugin.STRENGTH);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, level-1));
        }
    }

    public static class Haste extends Enchantment {

        public static final String NAME = "Haste";

        public Haste(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack chestplate) {
            int level = Utl.Nchnt.level(chestplate, plugin.HASTE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, level-1));
        }
    }

    public static class HealthBoost extends Enchantment {

        public static final String NAME = "Increased Health";

        public HealthBoost(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 5;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Nchnt.level(helmet, plugin.HEALTH_BOOST);
            int chestplateLevel = Utl.Nchnt.level(chestplate, plugin.HEALTH_BOOST);
            int leggingsLevel = Utl.Nchnt.level(leggings, plugin.HEALTH_BOOST);
            int bootsLevel = Utl.Nchnt.level(boots, plugin.HEALTH_BOOST);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 120, level-1));
        }
    }

    public static class HeroOfTheVillage extends Enchantment {

        public static final String NAME = "Hero of the Village";

        public HeroOfTheVillage(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 3;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Resistance.NAME)) return true;
            if (name.equals(Regeneration.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HealthBoost.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isChestplate(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Nchnt.level(helmet, plugin.HERO_OF_THE_VILLAGE);
            int chestplateLevel = Utl.Nchnt.level(chestplate, plugin.HERO_OF_THE_VILLAGE);
            int leggingsLevel = Utl.Nchnt.level(leggings, plugin.HERO_OF_THE_VILLAGE);
            int bootsLevel = Utl.Nchnt.level(boots, plugin.HERO_OF_THE_VILLAGE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 120, level-1));
        }
    }
}