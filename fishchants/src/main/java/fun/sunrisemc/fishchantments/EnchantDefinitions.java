package fun.sunrisemc.fishchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EnchantDefinitions {

    public static class GrassSeeds extends Enchantment {

        public GrassSeeds(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Grass Seeds";
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
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (player == null || projectile == null || bow == null || block == null) return;
            if (!Plugin.hasEnchant(bow, plugin.GRASS_SEEDS)) return;
            if (block.getType() != Material.DIRT) return;
            projectile.remove();
            block.setType(Material.GRASS_BLOCK);
        }
    }

    public static class Unbreakable extends Enchantment {

        public Unbreakable(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Unbreakable";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (player == null || item == null) return;
            if (!Plugin.hasEnchant(item, plugin.UNBREAKABLE)) return;
            event.setCancelled(true);
        }
    }

    public static class LifeSteal extends Enchantment {

        public LifeSteal(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Life Steal";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage) {
            if (player == null || entity == null || weapon == null || damage == 0) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.LIFE_STEAL);
            if (level < 1) return;
            heal(player, calcAddedHealth(damage, level));
        }

        static double calcAddedHealth(double damage, int level) {
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

    public static class Range extends Enchantment {

        public Range(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Range";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (player == null || projectile == null || item == null) return;
            final int level = Plugin.getEnchantLevel(item, plugin.RANGE);
            if (level < 1) return;
            projectile.setVelocity(projectile.getVelocity().multiply(1 + (level/5)));
        }
    }

    public static class Accurate extends Enchantment {

        public Accurate(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Accurate";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (player == null || projectile == null || item == null) return;
            if (!Plugin.hasEnchant(item, plugin.ACCURATE)) return;
            Vector direction = player.getEyeLocation().getDirection();
            Vector velocity = projectile.getVelocity();
            velocity.setX(direction.getX() * velocity.length());
            velocity.setY(direction.getY() * velocity.length());
            velocity.setZ(direction.getZ() * velocity.length());
            projectile.setVelocity(velocity);
        }
    }

    public static class Poison extends Enchantment {

        public Poison(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Poison";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.POISON);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 + (level * 10), level/3), false);
        }
    }

    public static class Wither extends Enchantment {

        public Wither(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Wither";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.WITHER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 + (level * 20), level/5), false);
        }
    }

    public static class Helium extends Enchantment {

        public Helium(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Helium";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.HELIUM);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, level - 1), false);
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 + (level * 10), 0), false);
        }
    }

    public static class Glow extends Enchantment {

        public Glow(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Glow";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.GLOW);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0), false);
        }
    }

    public static class Blindness extends Enchantment {

        public Blindness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Blindness";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.BLINDNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0), false);
        }
    }

    public static class Confusion extends Enchantment {

        public Confusion(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Confusion";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.CONFUSION);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0), false);
        }
    }

    public static class Weakness extends Enchantment {

        public Weakness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Weakness";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.WEAKNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 + (level * 10), level/4), false);
        }
    }

    public static class Hunger extends Enchantment {

        public Hunger(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Hunger";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.HUNGER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, level * 20, level/2), false);
        }
    }

    public static class Slowness extends Enchantment {

        public Slowness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Slowness";
        }

        @Override
        public int getMaxLevel() {
            return 10;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = Plugin.getEnchantLevel(weapon, plugin.SLOWNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 20, level/2), false);
        }
    }
}