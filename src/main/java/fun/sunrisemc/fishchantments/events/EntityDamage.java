package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.DeathWish;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.ProjectileResistance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.Heavy;

public class EntityDamage implements Listener {
    private final Plugin plugin;

    public EntityDamage(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        double damage = event.getFinalDamage();
        boolean ranged = event.getCause() == DamageCause.PROJECTILE;
        boolean melee = event.getCause() == DamageCause.ENTITY_ATTACK;
        ProjectileResistance.onPlayerTakeDamage(plugin, player, ranged, event);
        if (event.isCancelled()) return;
        Heavy.onPlayerTakeDamage(plugin, player, melee);
        DeathWish.onPlayerTakeDamage(plugin, player, damage, event);
    }
}
