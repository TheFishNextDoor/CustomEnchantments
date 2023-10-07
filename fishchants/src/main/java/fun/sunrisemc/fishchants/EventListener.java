package fun.sunrisemc.fishchants;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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

public class EventListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Enchants.GrassSeeds.onArrowHitBlock(player, projectile, player.getInventory().getItemInMainHand(), event.getHitBlock());
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
        Enchants.LifeSteal.onPlayerAttackEntity(player, entity, mainHand, event.getDamage());
        Enchants.Poision.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Wither.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Helium.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Glow.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Blindness.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Confusion.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Weakness.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Hunger.onPlayerAttackEntity(player, entity, mainHand);
        Enchants.Slowness.onPlayerAttackEntity(player, entity, mainHand);
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        Enchants.Unbreakable.onItemTakeDamage(player, item);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        Enchants.Range.onPlayerShootProjectile(player, projectile, mainHand);
        Enchants.Accurate.onPlayerShootProjectile(player, projectile, mainHand);
    }
}
