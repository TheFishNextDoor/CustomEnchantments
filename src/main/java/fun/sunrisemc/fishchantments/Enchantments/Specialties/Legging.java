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

public class Legging {

    public static class Swiftness extends Enchantment {

        public static final String NAME = "Swiftness";

        public Swiftness(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_LEGS;
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
            if (name.equals(DolphinsGrace.NAME)) return true;
            if (name.equals(Heavy.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isLeggings(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Nchnt.level(boots, plugin.SWIFTNESS);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, level-1));
        }
    }

    public static class DolphinsGrace extends Enchantment {

        public static final String NAME = "Dolphins Grace";

        public DolphinsGrace(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_LEGS;
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
            if (name.equals(Swiftness.NAME)) return true;
            if (name.equals(Heavy.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isLeggings(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack leggings) {
            if (!Utl.Nchnt.has(leggings, plugin.DOLPHINS_GRACE)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, 0));
        }
    }

    public static class Heavy extends Enchantment {

        public static final String NAME = "Heavy";

        public Heavy(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_LEGS;
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
            if (name.equals(Swiftness.NAME)) return true;
            if (name.equals(DolphinsGrace.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isLeggings(item.getType());
        }

        public static void onPlayerTakeDamage(Plugin plugin, Player player) {
            if (Utl.Nchnt.has(player.getInventory().getLeggings(), plugin.HEAVY)) Utl.Ntty.cancelKnockback(plugin, player);
        }
    }
}