package fun.sunrisemc.fishchantments.Enchantments.Specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class Weapon {

    public static class LifeSteal extends Enchantment {

        public static final String NAME = "Life Steal";

        public LifeSteal(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.LIFE_STEAL);
            if (level < 1) return;
            if (!ranged) damage /= 2;
            heal(player, calcAddedHealth(damage, level));
        }

        private static double calcAddedHealth(double damage, int level) {
            if (level == 1) return damage/6;
            else if (level == 2) return damage/5;
            else if (level == 3) return damage/4;
            else if (level == 4) return damage/3;
            else if (level == 5) return damage/2;
            else if (level == 6) return damage/1.75;
            else if (level == 7) return damage/1.5;
            else if (level == 8) return damage/1.25;
            else if (level == 9) return damage;
            else if (level >= 10) return damage*1.25;
            else return 0;
        }

        private static void heal(Player player, double amount) {
            final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double newHealth = player.getHealth() + amount;
            if (newHealth > maxHealth) newHealth = maxHealth;
            player.setHealth(newHealth);
        }
    }

    public static class Poison extends Enchantment {

        public static final String NAME = "Venom";

        public Poison(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.POISON);
            if (level < 1) return;
            int strength = level/2;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Wither extends Enchantment {

        public static final String NAME = "Withering";

        public Wither(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 4;
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
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.WITHER);
            if (level < 1) return;
            int strength = level/3;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Helium extends Enchantment {

        public static final String NAME = "Levitating";

        public Helium(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType()) || Utl.Mat.isBoots(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.HELIUM);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, level * 20, 0));
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Ench.getEnchantLevel(boots, plugin.HELIUM);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 120, level-1));
        }
    }

    public static class Blindness extends Enchantment {

        public static final String NAME = "Obscure";

        public Blindness(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.BLINDNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0));
        }
    }

    public static class Confusion extends Enchantment {

        public static final String NAME = "Disorienting";

        public Confusion(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.CONFUSION);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0));
        }
    }

    public static class Weakness extends Enchantment {

        public static final String NAME = "Debilitating";

        public Weakness(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.WEAKNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 + (level * 10), level/4));
        }
    }

    public static class Hunger extends Enchantment {

        public static final String NAME = "Starving";

        public Hunger(NamespacedKey key) {
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
            return EnchantmentTarget.BREAKABLE;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.HUNGER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, level * 20, level/2));
        }
    }

    public static class Slowness extends Enchantment {

        public static final String NAME = "Crippling";

        public Slowness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 4;
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
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.SLOWNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 20, level/2));
        }
    }
}