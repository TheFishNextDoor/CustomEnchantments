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
        double m = 1.0 + (Util.Enchant.lootingLevel(player) * 0.1);
        if (type == EntityType.ZOMBIE && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SUSTENANCE, 1));
        if (type == EntityType.WITHER_SKELETON && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DEATHWISH, 1));
        if (type == EntityType.HUSK && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.STARVING, 1));
        if (type == EntityType.SILVERFISH && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WORM, 1));
        if (type == EntityType.GHAST && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SLOW_FALLING, 1));
        if (type == EntityType.GUARDIAN && Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        if (type == EntityType.BLAZE) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FIRE_RESISTANCE, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FLAMING, 1));
        }
        if (type == EntityType.DROWNED) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.WATER_BREATHING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.ANCHOR, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.AQUA_ASPECT, 1));
        }
        if (type == EntityType.SLIME) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.LEAPING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.BOUNCE, 1));
        }
        if (type == EntityType.MAGMA_CUBE) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.RADIANCE, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_RADIANCE, 1));
        }
        if (type == EntityType.CAVE_SPIDER) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_WEAKNESS, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.POISON, 1));
        }
        if (type == EntityType.SPIDER) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DISORIENTING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.NIGHT_VISION, 1));
        }
        if (type == EntityType.ENDERMAN) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_AQUAPHOBIA, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.TELEKINESIS, 1));
        }
        if (type == EntityType.PILLAGER) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HERO_OF_THE_VILLAGE, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.REPLANTING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.TILLING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.REFLECTION, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SPURS, 1));
        }
        if (type == EntityType.PIGLIN) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.BLOODTIPPED, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HASTE, 1));
        }
        if (type == EntityType.ZOMBIFIED_PIGLIN) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.HEALING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.STRENGTH, 1));
        }
        if (type == EntityType.SHULKER) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_LEVITATING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.LEVITATING, 1));
        }
        if (type == EntityType.WITCH) {
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DEBILITATING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.INVISIBILITY, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.SWIFTNESS, 1));
        }
        if (type == EntityType.CREEPER) {
            Creeper creeper = (Creeper) livingEntity;
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.FLING, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.DESTRUCTIVE, 1));
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.BOOSTERS, 1));
            if (creeper.isPowered() && Util.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.EXCAVATING, 1));
        }
        if (type == EntityType.SKELETON) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Util.chance(3.0)) plugin.addEnchant(drop, plugin.RANGE, 1, false, false);
                if (Util.chance(1.0)) plugin.addEnchant(drop, plugin.PRECISION, 1, false, false);
            }
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.VOLLEY, 1));
        }
        if (type == EntityType.STRAY) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Util.chance(3.0)) plugin.addEnchant(drop, plugin.CRIPPLING, 1, false, false);
                if (Util.chance(1.0)) plugin.addEnchant(drop, plugin.PRECISION, 1, false, false);
            }
            if (Util.chance(0.1 * m)) drops.add(plugin.enchantedBook(plugin.GLASS, 1));
        }
        if (type == EntityType.RAVAGER && Util.chance(6.0 * m)) drops.add(plugin.enchantedBook(plugin.CRUSH, 1));
        if (type == EntityType.WARDEN) {
            if (Util.chance(5.0 * m)) drops.add(plugin.enchantedBook(plugin.OBSCURE, 1));
            if (Util.chance(4.0 * m)) drops.add(plugin.enchantedBook(plugin.ENLIGHTENMENT, 1));
            if (Util.chance(3.0 * m)) drops.add(plugin.enchantedBook(plugin.HEAVY, 1));
            if (Util.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_SLOWNESS, 1));
        }
        if (type == EntityType.WITHER) {
            if (Util.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.WITHER, 1));
            if (Util.chance(8.0 * m)) drops.add(plugin.enchantedBook(plugin.UNBREAKABLE, 1));
            if (Util.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.LIFE_STEAL, 1));
            if (Util.chance(1.0 * m)) drops.add(plugin.enchantedBook(plugin.PROJECTILE_RESISTANCE, 1));
        }
        if (type == EntityType.ENDER_DRAGON) {
            if (Util.chance(10.0 * m)) drops.add(plugin.enchantedBook(plugin.DRAGON_SCALES, 1));
            if (Util.chance(8.0 * m)) drops.add(plugin.enchantedBook(plugin.MOMENTUM, 1));
            if (Util.chance(2.5 * m)) drops.add(plugin.enchantedBook(plugin.INCREASED_HEALTH, 1));
        }
        if (type == EntityType.ELDER_GUARDIAN ) {
            if (Util.chance(70.0 * m)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Util.chance(30.0 * m)) drops.add(plugin.enchantedBook(plugin.CURSE_OF_MINING_FATIGUE, 1));
        }
    }
}
