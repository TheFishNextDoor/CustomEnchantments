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
        EnchantDefinitions.GrassSeeds.onArrowHitBlock(player, projectile, player.getInventory().getItemInMainHand(), event.getHitBlock());
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
        EnchantDefinitions.LifeSteal.onPlayerAttackEntity(player, entity, mainHand, event.getDamage());
        EnchantDefinitions.Poision.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Wither.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Helium.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Glow.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Blindness.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Confusion.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Weakness.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Hunger.onPlayerAttackEntity(player, entity, mainHand);
        EnchantDefinitions.Slowness.onPlayerAttackEntity(player, entity, mainHand);
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        EnchantDefinitions.Unbreakable.onItemTakeDamage(player, item);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        EnchantDefinitions.Range.onPlayerShootProjectile(player, projectile, mainHand);
        EnchantDefinitions.Accurate.onPlayerShootProjectile(player, projectile, mainHand);
    }
}
