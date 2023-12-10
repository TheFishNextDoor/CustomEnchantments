package com.thefishnextdoor.enchantments;

import java.util.List;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.NumberUtil;

public class Loot {

    public static void addLoot(Plugin plugin, Player player, LivingEntity livingEntity, List<ItemStack> drops) {
        if (!Settings.MOBS_DROP_BOOKS) return;
        if (!livingEntity.getWorld().getGameRuleValue(GameRule.DO_MOB_LOOT)) return;
        EntityType type = livingEntity.getType();
        double m = 1.0 + (EnchantUtil.lootingLevel(player) * 0.1);
        if (type == EntityType.ZOMBIE && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SUSTENANCE, 1));
        if (type == EntityType.WITHER_SKELETON && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DEATHWISH, 1));
        if (type == EntityType.HUSK && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.STARVING, 1));
        if (type == EntityType.SILVERFISH && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.WORM, 1));
        if (type == EntityType.GHAST && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SLOW_FALLING, 1));
        if (type == EntityType.GUARDIAN && NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DOLPHINS_GRACE, 1));
        if (type == EntityType.BLAZE) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FIRE_RESISTANCE, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FLAMING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SMELTING, 1));
        }
        if (type == EntityType.DROWNED) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.GILLS, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.ANCHOR, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.AQUA_ASPECT, 1));
        }
        if (type == EntityType.SLIME) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LEAPING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BOUNCE, 1));
        }
        if (type == EntityType.MAGMA_CUBE) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.RADIANCE, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_RADIANCE, 1));
        }
        if (type == EntityType.CAVE_SPIDER) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_WEAKNESS, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.VENOM, 1));
        }
        if (type == EntityType.SPIDER) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DISORIENTING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.NIGHT_VISION, 1));
        }
        if (type == EntityType.ENDERMAN) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_AQUAPHOBIA, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TELEKINESIS, 1));
        }
        if (type == EntityType.PILLAGER) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HERO_OF_THE_VILLAGE, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.REPLANTING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TILLING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.REFLECTION, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SPURS, 1));
        }
        if (type == EntityType.PIGLIN) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BLOOD_TIPPED, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HASTE, 1));
        }
        if (type == EntityType.ZOMBIFIED_PIGLIN) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HEALING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.STRENGTH, 1));
        }
        if (type == EntityType.SHULKER) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_LEVITATING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LEVITATING, 1));
        }
        if (type == EntityType.WITCH) {
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DEBILITATING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.INVISIBILITY, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SWIFTNESS, 1));
        }
        if (type == EntityType.CREEPER) {
            Creeper creeper = (Creeper) livingEntity;
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FLING, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DESTRUCTIVE, 1));
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BOOSTERS, 1));
            if (creeper.isPowered() && NumberUtil.chance(10.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.EXCAVATING, 1));
        }
        if (type == EntityType.SKELETON) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (NumberUtil.chance(3.0)) EnchantUtil.addEnchant(drop, CustomEnchantment.RANGE, 1, false, false);
                if (NumberUtil.chance(1.0)) EnchantUtil.addEnchant(drop, CustomEnchantment.PRECISION, 1, false, false);
            }
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.VOLLEY, 1));
        }
        if (type == EntityType.STRAY) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (NumberUtil.chance(3.0)) EnchantUtil.addEnchant(drop, CustomEnchantment.CRIPPLING, 1, false, false);
                if (NumberUtil.chance(1.0)) EnchantUtil.addEnchant(drop, CustomEnchantment.PRECISION, 1, false, false);
            }
            if (NumberUtil.chance(0.1 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.GLASS, 1));
        }
        if (type == EntityType.RAVAGER && NumberUtil.chance(6.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CRUSH, 1));
        if (type == EntityType.WARDEN) {
            if (NumberUtil.chance(5.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.OBSCURE, 1));
            if (NumberUtil.chance(4.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.ENLIGHTENMENT, 1));
            if (NumberUtil.chance(3.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HEAVY, 1));
            if (NumberUtil.chance(2.5 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_SLOWNESS, 1));
        }
        if (type == EntityType.WITHER) {
            if (NumberUtil.chance(10.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.WITHERING, 1));
            if (NumberUtil.chance(8.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.UNBREAKABLE, 1));
            if (NumberUtil.chance(2.5 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LIFE_STEAL, 1));
            if (NumberUtil.chance(1.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.PROJECTILE_RESISTANCE, 1));
        }
        if (type == EntityType.ENDER_DRAGON) {
            if (NumberUtil.chance(10.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DRAGON_SCALES, 1));
            if (NumberUtil.chance(8.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.MOMENTUM, 1));
            if (NumberUtil.chance(2.5 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.INCREASED_HEALTH, 1));
        }
        if (type == EntityType.ELDER_GUARDIAN ) {
            if (NumberUtil.chance(70.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CONDUIT_POWER, 1));
            if (NumberUtil.chance(30.0 * m)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_MINING_FATIGUE, 1));
        }
    }
}
