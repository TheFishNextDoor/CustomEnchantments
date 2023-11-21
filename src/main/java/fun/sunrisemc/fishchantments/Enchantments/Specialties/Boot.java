package fun.sunrisemc.fishchantments.enchantments.specialties;

import java.util.ArrayList;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isBoots(item.getType());
        }

        public static void onFall(Plugin plugin, Player player, ItemStack boots, double fallDamage, ArrayList<LivingEntity> fellOn) {
            final int level = Utl.Nchnt.level(boots, plugin.CRUSH);
            if (level < 1) return;
            final double damage = calcDamage(fallDamage, level);
            for (LivingEntity entity : fellOn) {
                entity.damage(damage, player);
            }
        }

        private static double calcDamage(double damage, int level) {
            if (level == 1) return damage/3;
            else if (level == 2) return damage/2;
            else if (level == 3) return damage;
            else if (level == 4) return damage * 1.5;
            else if (level == 5) return damage* 1.75;
            else if (level == 6) return damage * 2;
            else if (level == 7) return damage * 2.25;
            else if (level == 8) return damage * 2.5;
            else if (level == 9) return damage * 2.75;
            else if (level >= 10) return damage* 3;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isBoots(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Nchnt.level(boots, plugin.LEAPING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, level-1));
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isBoots(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Nchnt.level(boots, plugin.SLOW_FALLING);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, level-1));
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isBoots(item.getType());
        }

        @SuppressWarnings("deprecation")
        public static void whenInWater(Plugin plugin, Player player) {
            if (!Utl.Nchnt.has(player.getInventory().getBoots(), plugin.ANCHOR)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 2));
            if (player.isOnGround()) return;
            Vector velocity = player.getVelocity();
            double y = velocity.getY();
            if (y <= 0) velocity.setY(y - (0.06));
            else velocity.setY(y - (0.03));
            player.setVelocity(velocity);
        }
    }
}