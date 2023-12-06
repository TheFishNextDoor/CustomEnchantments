package fun.sunrisemc.fishchantments.enchantments.specialties;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Settings;
import fun.sunrisemc.fishchantments.Util;

public class Boot {

    public static class Crush extends Enchantment {

        public static final String NAME = "Crush";

        public Crush(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 8;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_FEET;
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
            if (name.equals(Leaping.NAME)) return true;
            if (name.equals(SlowFalling.NAME)) return true;
            if (name.equals(Anchor.NAME)) return true;
            if (name.equals(Bounce.NAME)) return true;
            if (Util.Enchant.same(other, Enchantment.PROTECTION_FALL)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isBoots(item.getType());
        }

        public static void onFall(Plugin plugin, Player player, ItemStack boots, EntityDamageEvent event) {
            int level = Util.Enchant.level(boots, plugin.CRUSH);
            if (level < 1) return;
            double damage = calcDamage(event.getDamage(), level);
            for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
                if (entity instanceof LivingEntity) ((LivingEntity) entity).damage(damage, player);
            }
        }

        private static double calcDamage(double damage, int level) {
            if (level == 1) return damage/3;
            else if (level == 2) return damage/2;
            else if (level == 3) return damage;
            else if (level == 4) return damage * 1.5;
            else if (level == 5) return damage * 1.75;
            else if (level == 6) return damage * 2;
            else if (level == 7) return damage * 2.25;
            else if (level == 8) return damage * 2.5;
            else if (level == 9) return damage * 2.75;
            else if (level >= 10) return damage * 3;
            else return 0;
        }
    }

    public static class Leaping extends Enchantment {

        public static final String NAME = "Leaping";

        public Leaping(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_FEET;
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
            if (name.equals(Crush.NAME)) return true;
            if (name.equals(SlowFalling.NAME)) return true;
            if (name.equals(Anchor.NAME)) return true;
            if (name.equals(Bounce.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isBoots(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Util.Enchant.level(boots, plugin.LEAPING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Settings.QUICK_ARMOR_EFFECTS_PERIOD * 2, level-1));
        }
    }

    public static class SlowFalling extends Enchantment {

        public static final String NAME = "Slow Falling";

        public SlowFalling(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_FEET;
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
            if (name.equals(Crush.NAME)) return true;
            if (name.equals(Leaping.NAME)) return true;
            if (name.equals(Anchor.NAME)) return true;
            if (name.equals(Bounce.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isBoots(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Util.Enchant.level(boots, plugin.SLOW_FALLING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Settings.QUICK_ARMOR_EFFECTS_PERIOD * 2, level-1));
        }
    }

    public static class Anchor extends Enchantment {

        public static final String NAME = "Anchor";

        public Anchor(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_FEET;
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
            if (name.equals(Crush.NAME)) return true;
            if (name.equals(Leaping.NAME)) return true;
            if (name.equals(SlowFalling.NAME)) return true;
            if (name.equals(Bounce.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isBoots(item.getType());
        }

        public static void whenSwimming(Plugin plugin, Player player) {
            if (!Util.Enchant.has(player.getInventory().getBoots(), plugin.ANCHOR)) return;
            Vector velocity = player.getVelocity();
            double y = velocity.getY();
            if (y <= 0) velocity.setY(y - (0.06));
            else velocity.setY(y - (0.03));
            player.setVelocity(velocity);
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            if (!player.isInWater()) return;
            if (!Util.Enchant.has(player.getInventory().getBoots(), plugin.ANCHOR)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Settings.QUICK_ARMOR_EFFECTS_PERIOD * 2, 2));
        }
    }

    public static class Bounce extends Enchantment {

        public static final String NAME = "Bounce";

        public Bounce(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_FEET;
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
            if (name.equals(Crush.NAME)) return true;
            if (name.equals(Leaping.NAME)) return true;
            if (name.equals(SlowFalling.NAME)) return true;
            if (name.equals(Anchor.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isBoots(item.getType());
        }

        public static void onFall(Plugin plugin, Player player, ItemStack boots, EntityDamageEvent event) {
            int level = Util.Enchant.level(boots, plugin.BOUNCE);
            if (level < 1) return;
            double v = Math.log(event.getFinalDamage()) * (level + 2) / 10;
            if (v > 10) v = 10;
            player.setVelocity(player.getVelocity().setY(v));
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
}