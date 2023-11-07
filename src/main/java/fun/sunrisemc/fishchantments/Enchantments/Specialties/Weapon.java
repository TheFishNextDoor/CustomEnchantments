package fun.sunrisemc.fishchantments.enchantments.specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;
import fun.sunrisemc.fishchantments.enchantments.Generic.Unbreakable;

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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, double damage, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.LIFE_STEAL, ranged);
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.POISON, ranged);
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.WITHER, ranged);
            if (level < 1) return;
            int strength = level/3;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength));
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.BLINDNESS, ranged);
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.CONFUSION, ranged);
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.WEAKNESS, ranged);
            if (level < 1) return;
            int strength = level/4;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40 + (level * 20)/(strength + 1), strength));
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.HUNGER, ranged);
            if (level < 1) return;
            int strength = level/2;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40 + (level * 20)/(strength + 1), strength));
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.SLOWNESS, ranged);
            if (level < 1) return;
            int strength = level/3;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Glass extends Enchantment {

        public static final String NAME = "Glass";

        public Glass(NamespacedKey key) {
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
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Unbreakable.NAME)) return true;
            if (Utl.Nchnt.same(other, Enchantment.DURABILITY)) return true;
            if (Utl.Nchnt.same(other, Enchantment.MENDING)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, EntityDamageByEntityEvent event, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.GLASS, ranged);
            if (level < 1) return;
            event.setDamage(event.getDamage() * 1.5);
        }

        public static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (Utl.Nchnt.has(item, plugin.GLASS)) event.setDamage(event.getDamage() * 16);
        }
    }

    public static class Levitation extends Enchantment {

        public static final String NAME = "Levitating";

        public Levitation(NamespacedKey key) {
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
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isRanged(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.LEVITATION, ranged);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, level * 40, 0));
        }
    }
    
    public static class BloodTipped extends Enchantment {

        public static final String NAME = "Blood Tipped";

        public BloodTipped(NamespacedKey key) {
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
            return false;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Levitation.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isRanged(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity livingEntity, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.BLOODTIPPED, ranged);
            if (level < 1) return;
            livingEntity.addPotionEffects(player.getActivePotionEffects());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player) {
            if (Utl.Nchnt.holdingRanged(player, plugin.BLOODTIPPED)) player.damage(1);
        }
    }
}