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
import fun.sunrisemc.fishchantments.enchantments.Generic.Reflection;
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
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity)) return;
        LivingEntity reciever = (LivingEntity) event.getEntity(); 
        double damage = event.getFinalDamage();
        Entity damager = event.getDamager();
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) {
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Entity) damager = (Entity) shooter;
            if (reciever instanceof Player) {
                Player player = (Player) reciever;
                if (player.isBlocking() && damage == 0) Reflection.onDeflectProjectile(plugin, player, projectile);
            }
        }
        if (!(damage > 0)) return;
        if (!(damager instanceof Player)) return;
        Player player = (Player) damager;
        LifeSteal.onPlayerAttackEntity(plugin, player, damage, RANGED_ATTACK);
        Fling.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Poison.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Wither.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        BloodTipped.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Levitation.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Glowing.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Blindness.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Confusion.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Weakness.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Hunger.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Slowness.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        DeathWish.onPlayerAttackEntity(plugin, player, damage, event);
        Glass.onPlayerAttackEntity(plugin, player, damage, event, RANGED_ATTACK);
    }   
}
