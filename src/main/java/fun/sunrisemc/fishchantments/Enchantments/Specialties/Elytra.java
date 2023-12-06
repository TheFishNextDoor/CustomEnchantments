package fun.sunrisemc.fishchantments.enchantments.specialties;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.util.EnchantUtil;

public class Elytra {

    public static class Boosters extends Enchantment {
    
        public static final String NAME = "Boosters";
    
        public Boosters(NamespacedKey key) {
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
            if (name.equals(Elytra.Momentum.NAME)) return true;
            return false;
        }
    
        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return item.getType() == Material.ELYTRA;
        }
    
        public static void whenGliding(Plugin plugin, Player player) {
            if (player.isInWater()) return;
            Vector velocity = player.getVelocity();
            int level = EnchantUtil.level(player.getInventory().getChestplate(), plugin.BOOSTERS);
            if (level < 1) return;
            if (level > 10) level = 10;
            if (velocity.length() > 1.1) return;
            Vector increase = player.getLocation().getDirection().clone().normalize().multiply(level * 0.01);
            player.setVelocity(velocity.add(increase));
        }
    }

    public static class Momentum extends Enchantment {
    
        public static final String NAME = "Momentum";
    
        public Momentum(NamespacedKey key) {
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
            if (name.equals(Boosters.NAME)) return true;
            return false;
        }
    
        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return item.getType() == Material.ELYTRA;
        }
    
        public static void whenGliding(Plugin plugin, Player player) {
            Vector velocity = player.getVelocity();
            double speed = velocity.length();
            if (speed < 0.6 || speed > 2.5) return;
            float pitch = -((float) Math.toDegrees(Math.asin(velocity.getY() / velocity.length())));
            if (pitch <= 0) return;
            int level = EnchantUtil.level(player.getInventory().getChestplate(), plugin.MOMENTUM);
            if (level < 1) return;
            if (level > 10) level = 10;
            Vector increase = velocity.clone().normalize().multiply(level * (pitch/10) * 0.002);
            player.setVelocity(velocity.add(increase));
        }
    }
    
}
