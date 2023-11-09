package fun.sunrisemc.fishchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Destructive;
import fun.sunrisemc.fishchantments.enchantments.Generic.Tilling;

public class ProjectileHit implements Listener {
    Plugin plugin;

    public ProjectileHit(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile == null) return;
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Block block = event.getHitBlock();
        if (block == null) return;
        Destructive.onProjectileHitBlock(plugin, player, projectile, block);
        Tilling.onProjectileHitBlock(plugin, player, projectile, block);
    }
}
