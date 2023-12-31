package com.thefishnextdoor.customenchantments;

import java.util.List;

import org.bukkit.GameRule;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.NumberUtil;

public class Loot {

    public static void addDrops(Player player, LivingEntity livingEntity, List<ItemStack> drops) {
        if (!Settings.MOBS_DROP_BOOKS) return;
        if (!livingEntity.getWorld().getGameRuleValue(GameRule.DO_MOB_LOOT)) return;
        EntityType type = livingEntity.getType();
        double lootingMultiplier = 1.0 + (EnchantUtil.lootingLevel(player) * 0.1);
        double c = 0.1 * lootingMultiplier;
        switch (type) {
            case ZOMBIE:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SUSTENANCE, 1));
                break;
            case WITHER_SKELETON:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DEATH_WISH, 1));
                break;
            case HUSK:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.STARVING, 1));
                break;
            case SILVERFISH:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.WORM, 1));
                break;
            case GHAST:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SLOW_FALLING, 1));
                break;
            case GUARDIAN:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DOLPHINS_GRACE, 1));
                break;
            case BLAZE:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FIRE_RESISTANCE, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FLAMING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SMELTING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FIRE_BLAST, 1));
                break;
            case DROWNED:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.GILLS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.ANCHOR, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.AQUA_ASPECT, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HARPOON, 1));
                break;
            case SLIME:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LEAPING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BOUNCE, 1));
                break;
            case MAGMA_CUBE:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.RADIANCE, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_RADIANCE, 1));
                break;
            case CAVE_SPIDER:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_WEAKNESS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.VENOM, 1));
                break;
            case SPIDER:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DISORIENTING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.NIGHT_VISION, 1));
                break;
            case ENDERMAN:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_AQUAPHOBIA, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TELEKINESIS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TELEPORT, 1));
                break;
            case PILLAGER:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HERO_OF_THE_VILLAGE, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.REPLANTING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TILLING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.REFLECTION, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SPURS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.TREE_FELLER, 1));
                break;
            case PIGLIN:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BLOOD_TIPPED, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HASTE, 1));
                break;
            case ZOMBIFIED_PIGLIN:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HEALING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.STRENGTH, 1));
                break;
            case SHULKER:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_LEVITATING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LEVITATING, 1));
                break;
            case WITCH:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DEBILITATING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.INVISIBILITY, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SWIFTNESS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_IRON_GRIP, 1));
                break;
            case CREEPER:
                Creeper creeper = (Creeper) livingEntity;
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.FLING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DESTRUCTIVE, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.BOOSTERS, 1));
                if (creeper.isPowered() && NumberUtil.chance(10.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.EXCAVATING, 1));
                break;
            case SKELETON:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.VOLLEY, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.RANGE, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.PRECISION, 1));
                break;
            case STRAY:
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.GLASS, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CRIPPLING, 1));
                if (NumberUtil.chance(c)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.SEEKING, 1));
                break;
            case RAVAGER:
                if (NumberUtil.chance(6.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CRUSH, 1));
                break;
            case WARDEN:
                if (NumberUtil.chance(5.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.OBSCURE, 1));
                if (NumberUtil.chance(4.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.ENLIGHTENMENT, 1));
                if (NumberUtil.chance(3.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.HEAVY, 1));
                if (NumberUtil.chance(2.5 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_SLOWNESS, 1));
                break;
            case WITHER:
                if (NumberUtil.chance(10.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.WITHERING, 1));
                if (NumberUtil.chance(8.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.UNBREAKABLE, 1));
                if (NumberUtil.chance(2.5 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.LIFE_STEAL, 1));
                if (NumberUtil.chance(1.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.PROJECTILE_RESISTANCE, 1));
                break;
            case ENDER_DRAGON:
                if (NumberUtil.chance(10.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.DRAGON_SCALES, 1));
                if (NumberUtil.chance(8.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.MOMENTUM, 1));
                if (NumberUtil.chance(2.5 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.INCREASED_HEALTH, 1));
                break;
            case ELDER_GUARDIAN:
                if (NumberUtil.chance(70.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CONDUIT_POWER, 1));
                if (NumberUtil.chance(30.0 * lootingMultiplier)) drops.add(EnchantUtil.enchantedBook(CustomEnchantment.CURSE_OF_MINING_FATIGUE, 1));
                break;
            default:
                break;
        }
    }
}
