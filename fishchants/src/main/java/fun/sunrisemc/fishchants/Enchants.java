package fun.sunrisemc.fishchants;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

class Enchants {

    static class GrassSeeds {
        final static String NAME = ChatColor.GRAY + "Grass Seeds";

        static void onArrowHitBlock(Player player, Projectile projectile, ItemStack bow, Block block) {
            if (player == null || projectile == null || bow == null || block == null) return;
            if (!EnchantManager.hasEnchant(bow, NAME)) return;
            if (block.getType() != Material.DIRT) return;
            projectile.remove();
            block.setType(Material.GRASS_BLOCK);
        }
    }

    static class LifeSteal {
        final static String NAME = ChatColor.GRAY + "Life Steal";

        static void onPlayerAttackEntity(Player player, Entity entity, ItemStack weapon, double damage) {
            if (player == null || entity == null || weapon == null || damage == 0) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            heal(player, calcAddedHealth(damage, level));
        }

        static double calcAddedHealth(double damage, int level) {
            if (level == 1) return damage/6;
            else if (level == 2) return damage/5;
            else if (level == 3) return damage/4;
            else if (level == 4) return damage/3;
            else if (level == 5) return damage/2;
            else if (level == 6) return damage/1.75;
            else if (level == 7) return damage/1.5;
            else if (level == 8) return damage/1.25;
            else if (level == 9) return damage;
            else if (level >= 10) return damage*1.25;
            else return 0;
        }

        private static void heal(Player player, double amount) {
            final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double newHealth = player.getHealth() + amount;
            if (newHealth > maxHealth) newHealth = maxHealth;
            player.setHealth(newHealth);
        }
    }

    static class Poision {
        final static String NAME = ChatColor.GRAY + "Poison";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 + level * 10, level/3), false);
        }
    }

    static class Wither {
        final static String NAME = ChatColor.GRAY + "Wither";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 + level * 20, level/5), false);
        }
    }

    static class Helium {
        final static String NAME = ChatColor.GRAY + "Helium";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, level - 1), false);
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20 + (level * 10), 0), false);
        }
    }

    static class Glow {
        final static String NAME = ChatColor.GRAY + "Glow";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0), false);
        }
    }

    static class BadOmen {
        final static String NAME = ChatColor.GRAY + "Bad Omen";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, level * 2400, level/2), false);
        }
    }

    static class Blindness {
        final static String NAME = ChatColor.GRAY + "Blindness";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0), false);
        }
    }

    static class Confusion {
        final static String NAME = ChatColor.GRAY + "Confusion";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0), false);
        }
    }

    static class Weakness {
        final static String NAME = ChatColor.GRAY + "Weakness";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, level * 10, level/3), false);
        }
    }

    static class Hunger {
        final static String NAME = ChatColor.GRAY + "Hunger";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, level * 20, level/2), false);
        }
    }

    static class Slowness {
        final static String NAME = ChatColor.GRAY + "Slowness";

        static void onPlayerAttackEntity(Player attacker, LivingEntity reciever, ItemStack weapon) {
            if (attacker == null || reciever == null || weapon == null) return;
            final int level = EnchantManager.getEnchantLevel(weapon, NAME);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 20, level/2), false);
        }
    }
}