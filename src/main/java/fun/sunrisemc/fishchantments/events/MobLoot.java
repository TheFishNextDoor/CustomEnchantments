package fun.sunrisemc.fishchantments.events;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;
import fun.sunrisemc.fishchantments.enchantments.Generic.Enlightenment;
import fun.sunrisemc.fishchantments.enchantments.Generic.Telekinesis;

public class MobLoot implements Listener {
    private static final boolean DROP_BOOKS = true;
    private final Plugin plugin;

    public MobLoot(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onMobLoot(EntityDeathEvent event) {
        if (!DROP_BOOKS) return;
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (player == null) return;
        EntityType type = entity.getType();
        List<ItemStack> drops = event.getDrops();
        if (drops.size() == 0) return;
        if (type == EntityType.ENDERMAN && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.TELEKINESIS, 1));
        if (type == EntityType.CAVE_SPIDER && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.POISON, 1));
        if (type == EntityType.SPIDER && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.NIGHT_VISION, 1));
        if (type == EntityType.MAGMA_CUBE && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.GLOWING, 1));
        if (type == EntityType.ZOMBIE && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.FOOD, 1));
        if (type == EntityType.DROWNED && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.WATER_BREATHING, 1));
        if (type == EntityType.HUSK && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.HUNGER, 1));
        if (type == EntityType.SHULKER && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.LEVITATION, 1));
        if (type == EntityType.SILVERFISH && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.WORM, 1));
        if (type == EntityType.BLAZE && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.FIRE_RESISTANCE, 1));
        if (type == EntityType.PILLAGER && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.HERO_OF_THE_VILLAGE, 1));
        if (type == EntityType.GHAST && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.SLOW_FALL, 1));
        if (type == EntityType.PIGLIN && Utl.chance(0.2)) drops.add(plugin.enchantedBook(plugin.HASTE, 1));
        if (type == EntityType.PIGLIN_BRUTE && Utl.chance(3.0)) drops.add(plugin.enchantedBook(plugin.STRENGTH, 1));
        if (type == EntityType.ILLUSIONER && Utl.chance(3.0)) drops.add(plugin.enchantedBook(plugin.BLINDNESS, 1));
        if (type == EntityType.EVOKER && Utl.chance(3.0)) drops.add(plugin.enchantedBook(plugin.SPEED, 1));
        if (type == EntityType.RAVAGER && Utl.chance(8.0)) drops.add(plugin.enchantedBook(plugin.CRUSH, 1));
        if (type == EntityType.CREEPER) {
            Creeper creeper = (Creeper) entity;
            if (Utl.chance(0.05)) drops.add(plugin.enchantedBook(plugin.EXCAVATING, 1));
            if (Utl.chance(0.05)) drops.add(plugin.enchantedBook(plugin.DESTRUCTIVE, 1));
            if (creeper.isPowered() && Utl.chance(15.0)) drops.add(plugin.enchantedBook(plugin.FLING, 1));
        }
        if (type == EntityType.WITCH) {
            if (Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.WEAKNESS, 1));
            if (Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.INVISIBILITY, 1));
        }
        if (type == EntityType.WITHER) {
            if (Utl.chance(10.0)) drops.add(plugin.enchantedBook(plugin.WITHER, 1));
            if (Utl.chance(2.5)) drops.add(plugin.enchantedBook(plugin.LIFE_STEAL, 1));
        }
        if (type == EntityType.ENDER_DRAGON) {
            if (Utl.chance(10.0)) drops.add(plugin.enchantedBook(plugin.RESISTANCE, 1));
            if (Utl.chance(2.5)) drops.add(plugin.enchantedBook(plugin.HEALTH_BOOST, 1));
        }
        if (type == EntityType.ELDER_GUARDIAN ) {
            if (Utl.chance(60.0)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Utl.chance(40.0)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        }
        if (type == EntityType.GUARDIAN ) {
            if (Utl.chance(0.12)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Utl.chance(0.08)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        }
        if (type == EntityType.SKELETON) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.RANGE, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
        }
        if (type == EntityType.STRAY) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.SLOWNESS, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
        }
        Enlightenment.onMobXp(plugin, player, event);
        Telekinesis.onMobXp(plugin, player, event);
        Telekinesis.onMobLoot(plugin, player, drops);
    }
}
