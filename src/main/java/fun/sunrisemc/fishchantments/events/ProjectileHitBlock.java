package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Destructive;
import fun.sunrisemc.fishchantments.enchantments.Generic.Tilling;

public class ProjectileHitBlock implements Listener {
    Plugin plugin;

    public ProjectileHitBlock(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHitBlock(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Destructive.onProjectileHitBlock(plugin, player, projectile, event.getHitBlock());
        Tilling.onProjectileHitBlock(plugin, player, projectile, event.getHitBlock());
    }
}
