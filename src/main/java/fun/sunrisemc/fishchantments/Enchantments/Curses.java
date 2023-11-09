package fun.sunrisemc.fishchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            int level = Utl.Nchnt.armorLevel(player, plugin.MINING_FATIGUE_CURSE);
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            int level = Utl.Nchnt.armorLevel(player, plugin.SLOWNESS_CURSE);
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            int level = Utl.Nchnt.armorLevel(player, plugin.WEAKNESS_CURSE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, level-1));
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
            return EnchantmentTarget.WEARABLE;
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, double damage, EntityDamageByEntityEvent event) {
            if (Utl.Nchnt.wearing(player, plugin.DEATHWISH)) event.setDamage(damage * 1.75);
        }

        public static void onPlayerTakeDamage(Plugin plugin, Player player, double damage, EntityDamageEvent event) {
            if (Utl.Nchnt.wearing(player, plugin.DEATHWISH)) event.setDamage(damage * 1.5);
        }
    }

    public static class LevitationCurse extends Enchantment {

        public static final String NAME = "Curse of Levitating";

        public LevitationCurse(NamespacedKey key) {
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
            return EnchantmentTarget.WEARABLE;
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            int level = Utl.Nchnt.armorLevel(player, plugin.LEVITATIONCURSE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, level-1));
        }
    }

    public static class AquaphobiaCurse extends Enchantment {

        public static final String NAME = "Curse of Aquaphobia";

        public AquaphobiaCurse(NamespacedKey key) {
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
            return EnchantmentTarget.WEARABLE;
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            int level = Utl.Nchnt.armorLevel(player, plugin.AQUAPHOBIACURSE);
            if (level < 1) return;
            boolean inWater = player.isInWater();
            boolean inRain = Utl.Wrld.raining(player.getLocation().getBlock()) && !Utl.Wrld.underBlock(player);
            if (inWater || inRain) player.damage(1);
        }
    }
}