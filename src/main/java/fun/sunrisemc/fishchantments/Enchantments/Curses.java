package fun.sunrisemc.fishchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class Curses {

    public static class MiningFatigueCurse extends Enchantment {

        public static final String NAME = "Curse of Mining Fatigue";

        public MiningFatigueCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.MINING_FATIGUE_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.MINING_FATIGUE_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.MINING_FATIGUE_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.MINING_FATIGUE_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 120, level-1));
        }
    }

    public static class SlownessCurse extends Enchantment {

        public static final String NAME = "Curse of Slowness";

        public SlownessCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.SLOWNESS_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.SLOWNESS_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.SLOWNESS_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.SLOWNESS_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, level-1));
        }
    }

    public static class WeaknessCurse extends Enchantment {

        public static final String NAME = "Curse of Weakness";

        public WeaknessCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR;
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
        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.WEAKNESS_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.WEAKNESS_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.WEAKNESS_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.WEAKNESS_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, level-1));
        }
    }
}