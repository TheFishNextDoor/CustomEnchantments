package fun.sunrisemc.fishchantments;

import java.util.List;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Loot {

    public static void addLoot(Plugin plugin, Player player, LivingEntity livingEntity, List<ItemStack> drops) {
        EntityType type = livingEntity.getType();
        if (!livingEntity.getWorld().getGameRuleValue(GameRule.DO_MOB_LOOT)) return;
        double m = 1.0 + (Utl.Nchnt.lootingLevel(player) * 0.1);
        if (type == EntityType.MAGMA_CUBE && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.GLOWING, 1));
        if (type == EntityType.SLIME && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.JUMP, 1));
        if (type == EntityType.ZOMBIE && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FOOD, 1));
        if (type == EntityType.WITHER_SKELETON && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DEATHWISH, 1));
        if (type == EntityType.DROWNED && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WATER_BREATHING, 1));
        if (type == EntityType.HUSK && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HUNGER, 1));
        if (type == EntityType.SILVERFISH && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WORM, 1));
        if (type == EntityType.BLAZE && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FIRE_RESISTANCE, 1));;
        if (type == EntityType.GHAST && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SLOW_FALL, 1));
        if (type == EntityType.GUARDIAN && Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        if (type == EntityType.CAVE_SPIDER) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WEAKNESS_CURSE, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.POISON, 1));
        }
        if (type == EntityType.SPIDER) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.CONFUSION, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.NIGHT_VISION, 1));
        }
        if (type == EntityType.ENDERMAN) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.AQUAPHOBIACURSE, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.TELEKINESIS, 1));
        }
        if (type == EntityType.PILLAGER) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HERO_OF_THE_VILLAGE, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.REPLANTING, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.TILLING, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.REFLECTION, 1));
        }
        if (type == EntityType.PIGLIN) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.BLOODTIPPED, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HASTE, 1));
        }
        if (type == EntityType.ZOMBIFIED_PIGLIN) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.REGENERATION, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.STRENGTH, 1));
        }
        if (type == EntityType.SHULKER) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.LEVITATIONCURSE, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.LEVITATION, 1));
        }
        if (type == EntityType.WITCH) {
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WEAKNESS, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.INVISIBILITY, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SPEED, 1));
        }
        if (type == EntityType.CREEPER) {
            Creeper creeper = (Creeper) livingEntity;
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FLING, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DESTRUCTIVE, 1));
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.BOOSTERS, 1));
            if (creeper.isPowered() && Utl.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.EXCAVATING, 1));
        }
        if (type == EntityType.SKELETON) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.RANGE, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.VOLLEY, 1));
        }
        if (type == EntityType.STRAY) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.SLOWNESS, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
            if (Utl.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.GLASS, 1));
        }
        if (type == EntityType.RAVAGER && Utl.chance(6.0 * m)) drops.add(plugin.enchantedBook(plugin.CRUSH, 1));
        if (type == EntityType.WARDEN) {
            if (Utl.chance(5.0 * m)) drops.add(plugin.enchantedBook(plugin.BLINDNESS, 1));
            if (Utl.chance(4.0 * m)) drops.add(plugin.enchantedBook(plugin.ENLIGHTENMENT, 1));
            if (Utl.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.SLOWNESS_CURSE, 1));
        }
        if (type == EntityType.WITHER) {
            if (Utl.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.WITHER, 1));
            if (Utl.chance(8.0 * m)) drops.add(plugin.enchantedBook(plugin.UNBREAKABLE, 1));
            if (Utl.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.LIFE_STEAL, 1));
        }
        if (type == EntityType.ENDER_DRAGON) {
            if (Utl.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.RESISTANCE, 1));
            if (Utl.chance(8.0 * m)) drops.add(plugin.enchantedBook(plugin.MOMENTUM, 1));
            if (Utl.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.HEALTH_BOOST, 1));
        }
        if (type == EntityType.ELDER_GUARDIAN ) {
            if (Utl.chance(70.0 * m)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Utl.chance(30.0 * m)) drops.add(plugin.enchantedBook(plugin.MINING_FATIGUE_CURSE, 1));
        }
    }
    
}
