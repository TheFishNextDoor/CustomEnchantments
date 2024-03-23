package com.thefishnextdoor.customenchantments;

import java.util.List;

import org.bukkit.GameRule;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.EntityTag;
import com.thefishnextdoor.customenchantments.util.NumberTools;

public class Loot {

    public static void addDrops(Player player, LivingEntity livingEntity, List<ItemStack> drops) {
        if (!CustomEnchantments.getSettings().MOBS_DROP_BOOKS) {
            return;
        }

        if (!livingEntity.getWorld().getGameRuleValue(GameRule.DO_MOB_LOOT)) {
            return;
        }

        if (EntityTag.NO_DROPS.isOn(livingEntity)) {
            return;
        }

        EntityType type = livingEntity.getType();
        double baseChance = 0.1;
        double lootingMultiplier = 1.0 + (EnchantTools.lootingLevel(player) * 0.1);
        double chance = baseChance * lootingMultiplier;
        switch (type) {
            case ZOMBIE:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SUSTENANCE, 1));
                }
                break;
            case WITHER_SKELETON:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DEATH_WISH, 1));
                }
                break;
            case HUSK:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.STARVING, 1));
                }
                break;
            case SILVERFISH:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.WORM, 1));
                }
                break;
            case GHAST:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SLOW_FALLING, 1));
                }
                break;
            case GUARDIAN:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DOLPHINS_GRACE, 1));
                }
                break;
            case BLAZE:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.FIRE_RESISTANCE, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.FLAMING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SMELTING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.FIRE_BLAST, 1));
                }
                break;
            case DROWNED:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.GILLS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.ANCHOR, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.AQUA_ASPECT, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.HARPOON, 1));
                }
                break;
            case SLIME:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.LEAPING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.BOUNCE, 1));
                }
                break;
            case MAGMA_CUBE:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.RADIANCE, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_RADIANCE, 1));
                }
                break;
            case CAVE_SPIDER:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_WEAKNESS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.VENOM, 1));
                }
                break;
            case SPIDER:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DISORIENTING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.NIGHT_VISION, 1));
                }
                break;
            case ENDERMAN:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_AQUAPHOBIA, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.TELEKINESIS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.TELEPORT, 1));
                }
                break;
            case PILLAGER:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.HERO_OF_THE_VILLAGE, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.REPLANTING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.TILLING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.REFLECTION, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SPURS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.TREE_FELLER, 1));
                }
                break;
            case PIGLIN:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.BLOOD_TIPPED, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.HASTE, 1));
                }
                break;
            case ZOMBIFIED_PIGLIN:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.HEALING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.STRENGTH, 1));
                }
                break;
            case SHULKER:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_LEVITATING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.LEVITATING, 1));
                }
                break;
            case WITCH:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DEBILITATING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.INVISIBILITY, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SWIFTNESS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_IRON_GRIP, 1));
                }
                break;
            case CREEPER:
                Creeper creeper = (Creeper) livingEntity;
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.FLING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DESTRUCTIVE, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.BOOSTERS, 1));
                }
                if (creeper.isPowered() && NumberTools.chance(10.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.EXCAVATING, 1));
                }
                break;
            case SKELETON:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.VOLLEY, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.RANGE, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.PRECISION, 1));
                }
                break;
            case STRAY:
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.GLASS, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CRIPPLING, 1));
                }
                if (NumberTools.chance(chance)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.SEEKING, 1));
                }
                break;
            case RAVAGER:
                if (NumberTools.chance(6.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CRUSH, 1));
                }
                break;
            case WARDEN:
                if (NumberTools.chance(5.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.ENLIGHTENMENT, 1));
                }
                if (NumberTools.chance(4.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.HEAVY, 1));
                }
                if (NumberTools.chance(3.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_SLOWNESS, 1));
                }
                if (NumberTools.chance(2.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.OBSCURE, 1));
                }
                break;
            case WITHER:
                if (NumberTools.chance(10.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.UNBREAKABLE, 1));
                }
                if (NumberTools.chance(5.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.LIFE_STEAL, 1));
                }
                if (NumberTools.chance(4.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.WITHERING, 1));
                }
                if (NumberTools.chance(3.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.PROJECTILE_RESISTANCE, 1));
                }
                break;
            case ENDER_DRAGON:
                if (NumberTools.chance(10.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.DRAGON_SCALES, 1));
                }
                if (NumberTools.chance(8.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.MOMENTUM, 1));
                }
                if (NumberTools.chance(4.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.INCREASED_HEALTH, 1));
                }
                break;
            case ELDER_GUARDIAN:
                if (NumberTools.chance(70.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CONDUIT_POWER, 1));
                }
                if (NumberTools.chance(40.0 * lootingMultiplier)) {
                    drops.add(EnchantTools.enchantedBook(CustomEnchantment.CURSE_OF_MINING_FATIGUE, 1));
                }
                break;
            default:
                break;
        }
    }
}
