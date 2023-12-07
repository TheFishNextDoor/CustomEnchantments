package fun.sunrisemc.fishchantments.enchantment_definitions.specialties;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.CustomEnchantment;
import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Unbreakable;
import fun.sunrisemc.fishchantments.util.EnchantUtil;
import fun.sunrisemc.fishchantments.util.InventoryUtil;

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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, double damage, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.LIFE_STEAL, ranged);
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

    public static class Venom extends Enchantment {

        public static final String NAME = "Venom";

        public Venom(NamespacedKey key) {
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
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.POISON, ranged);
            if (level < 1) return;
            int strength = level/2;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Withering extends Enchantment {

        public static final String NAME = "Withering";

        public Withering(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.WITHER, ranged);
            if (level < 1) return;
            int strength = level/3;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Obscure extends Enchantment {

        public static final String NAME = "Obscure";

        public Obscure(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.OBSCURE, ranged);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0));
        }
    }

    public static class Disorienting extends Enchantment {

        public static final String NAME = "Disorienting";

        public Disorienting(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.DISORIENTING, ranged);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0));
        }
    }

    public static class Debilitating extends Enchantment {

        public static final String NAME = "Debilitating";

        public Debilitating(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.DEBILITATING, ranged);
            if (level < 1) return;
            int strength = level/4;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Starving extends Enchantment {

        public static final String NAME = "Starving";

        public Starving(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.STARVING, ranged);
            if (level < 1) return;
            int strength = level/2;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40 + (level * 20)/(strength + 1), strength));
        }
    }

    public static class Crippling extends Enchantment {

        public static final String NAME = "Crippling";

        public Crippling(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.CRIPPLING, ranged);
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
            return true;
        }

        @Override
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            if (EnchantUtil.same(other, Enchantment.DURABILITY)) return true;
            if (EnchantUtil.same(other, Enchantment.MENDING)) return true;
            if (name.equals(Unbreakable.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, double damage, EntityDamageByEntityEvent event, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.GLASS, ranged);
            if (level < 1) return;
            event.setDamage(damage * 1.5);
        }

        public static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, int damage, PlayerItemDamageEvent event) {
            if (EnchantUtil.has(item, CustomEnchantment.GLASS)) event.setDamage(damage * 32);
        }
    }

    public static class Levitating extends Enchantment {

        public static final String NAME = "Levitating";

        public Levitating(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isRanged(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.LEVITATING, ranged);
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(Volley.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isRanged(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity livingEntity, boolean ranged) {
            final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.BLOODTIPPED, ranged);
            if (level < 1) return;
            livingEntity.addPotionEffects(player.getActivePotionEffects());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player) {
            if (EnchantUtil.holdingRanged(player, CustomEnchantment.BLOODTIPPED)) player.damage(1);
        }
    }

    public static class Volley extends Enchantment {

        public static final String NAME = "Volley";

        public Volley(NamespacedKey key) {
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
            if (name.equals(Venom.NAME)) return true;
            if (name.equals(Withering.NAME)) return true;
            if (name.equals(Obscure.NAME)) return true;
            if (name.equals(Disorienting.NAME)) return true;
            if (name.equals(Debilitating.NAME)) return true;
            if (name.equals(Starving.NAME)) return true;
            if (name.equals(Crippling.NAME)) return true;
            if (name.equals(Glass.NAME)) return true;
            if (name.equals(Levitating.NAME)) return true;
            if (name.equals(BloodTipped.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            Material type = item.getType();
            return type == Material.BOW || type == Material.CROSSBOW;
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (!(projectile instanceof AbstractArrow)) return;
            int level = EnchantUtil.rangedLevel(player, CustomEnchantment.VOLLEY);
            if (level < 1) return;
            if (level > 9) level = 9;
            World world = projectile.getWorld();
            Location location = projectile.getLocation();
            EntityType type = projectile.getType();
            Vector velocity = projectile.getVelocity();
            for (int i = 1; i <= level; i++) {
                AbstractArrow arrow = (AbstractArrow) world.spawnEntity(location, type);
                arrow.setShooter(player);
                arrow.setVelocity(velocity.clone().multiply(1.0 - (i * 0.1)));
                arrow.setPickupStatus(PickupStatus.DISALLOWED);
            }
        }
    }
}