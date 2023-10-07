package fun.sunrisemc.fishchantments;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glow;
import fun.sunrisemc.fishchantments.EnchantDefinitions.GrassSeeds;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;

public class EventListener implements Listener {
    private final Plugin plugin;

    EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        GrassSeeds.onArrowHitBlock(plugin, player, projectile, player.getInventory().getItemInMainHand(), event.getHitBlock());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity damager = event.getDamager();
        if (damager instanceof Projectile) { // Include ranged player attacks
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player) damager = (Player) shooter;
        }
        if (!(damager instanceof Player && event.getEntity() instanceof LivingEntity)) return;
        Player player = (Player) damager;
        LivingEntity entity = (LivingEntity) event.getEntity();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        LifeSteal.onPlayerAttackEntity(plugin, player, entity, mainHand, event.getDamage());
        Poison.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Wither.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Helium.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Glow.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Blindness.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Confusion.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Weakness.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Hunger.onPlayerAttackEntity(plugin, player, entity, mainHand);
        Slowness.onPlayerAttackEntity(plugin, player, entity, mainHand);
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        Unbreakable.onItemTakeDamage(plugin, player, item, event);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Range.onPlayerShootProjectile(plugin, player, projectile, mainHand);
        Accurate.onPlayerShootProjectile(plugin, player, projectile, mainHand);
    }
}
