package fun.sunrisemc.fishchantments.enchantment_definitions.specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.CustomEnchantment;
import fun.sunrisemc.fishchantments.Settings;
import fun.sunrisemc.fishchantments.util.EnchantUtil;
import fun.sunrisemc.fishchantments.util.InventoryUtil;

public class Chestplate {

    public static class DragonScales extends Enchantment {

        public static final String NAME = "Dragon Scales";

        public DragonScales(NamespacedKey key) {
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
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.DRAGON_SCALES);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
        }
    }

    public static class Healing extends Enchantment {

        public static final String NAME = "Healing";

        public Healing(NamespacedKey key) {
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.HEALING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            if (!(EnchantUtil.has(chestplate, CustomEnchantment.FIRE_RESISTANCE))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, 0));
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.STRENGTH);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.HASTE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
        }
    }

    public static class IncreasedHealth extends Enchantment {

        public static final String NAME = "Increased Health";

        public IncreasedHealth(NamespacedKey key) {
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.INCREASED_HEALTH);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }

        public static void onTimer(Player player, ItemStack chestplate) {
            int level = EnchantUtil.level(chestplate, CustomEnchantment.HERO_OF_THE_VILLAGE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Settings.ARMOR_EFFECTS_PERIOD_TICKS * 2, level-1));
        }
    }

    public static class DeathWish extends Enchantment {
    
        public static final String NAME = "Death Wish";
    
        public DeathWish(NamespacedKey key) {
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
            return true;
        }
    
        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }
    
        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }
    
        public static void onPlayerAttackEntity(Player player, double damage, EntityDamageByEntityEvent event) {
            if (EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATHWISH)) event.setDamage(damage * 1.75);
        }
    
        public static void onPlayerTakeDamage(Player player, double damage, EntityDamageEvent event) {
            if (EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.DEATHWISH)) event.setDamage(damage * 1.5);
        }
    }

    public static class ProjectileResistance extends Enchantment {
    
        public static final String NAME = "Projectile Resistance";
    
        public ProjectileResistance(NamespacedKey key) {
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(Flaming.NAME)) return true;
            return false;
        }
    
        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }
    
        public static void onPlayerTakeDamage(Player player, boolean ranged, EntityDamageEvent event) {
            if (ranged && EnchantUtil.has(player.getInventory().getChestplate(), CustomEnchantment.PROJECTILE_RESISTANCE)) event.setCancelled(true);
        }
    }

    public static class Flaming extends Enchantment {
    
        public static final String NAME = "Flaming";
    
        public Flaming(NamespacedKey key) {
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
            if (name.equals(DragonScales.NAME)) return true;
            if (name.equals(Healing.NAME)) return true;
            if (name.equals(FireResistance.NAME)) return true;
            if (name.equals(Strength.NAME)) return true;
            if (name.equals(Haste.NAME)) return true;
            if (name.equals(IncreasedHealth.NAME)) return true;
            if (name.equals(HeroOfTheVillage.NAME)) return true;
            if (name.equals(DeathWish.NAME)) return true;
            if (name.equals(ProjectileResistance.NAME)) return true;
            return false;
        }
    
        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }
    
        public static void onEntityAttackPlayer(Player player, LivingEntity entity) {
            int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.FLAMING);
            if (level < 1) return;
            entity.setFireTicks(level * 20);
        }
    }
}