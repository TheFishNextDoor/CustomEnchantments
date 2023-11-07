package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Curses.DeathWish;
import fun.sunrisemc.fishchantments.enchantments.Generic.Fling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Glowing;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Blindness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.BloodTipped;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Confusion;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Glass;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Levitation;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Hunger;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.LifeSteal;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Poison;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Slowness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Weakness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Wither;

public class AttackEntity implements Listener {
    Plugin plugin;

    public AttackEntity(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity damager = event.getDamager();
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) { // Include ranged player attacks
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player) damager = (Player) shooter;
        }
        if (!(damager instanceof Player && event.getEntity() instanceof LivingEntity)) return;
        Player player = (Player) damager;
        LivingEntity entity = (LivingEntity) event.getEntity();
        final double damage = event.getDamage();
        if (plugin == null || player == null || entity == null|| damage == 0) return;
        LifeSteal.onPlayerAttackEntity(plugin, player, damage, RANGED_ATTACK);
        Fling.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Poison.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Wither.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        BloodTipped.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Levitation.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Glowing.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Blindness.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Confusion.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Weakness.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Hunger.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        Slowness.onPlayerAttackEntity(plugin, player, entity, RANGED_ATTACK);
        DeathWish.onPlayerAttackEntity(plugin, player, event);
        Glass.onPlayerAttackEntity(plugin, player, event, RANGED_ATTACK);
    }   
}
