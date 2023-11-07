package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Accurate;
import fun.sunrisemc.fishchantments.enchantments.Generic.Range;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.BloodTipped;

public class FireProjectile implements Listener {
    private final Plugin plugin;

    public FireProjectile(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFireProjectile(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Range.onPlayerFireProjectile(plugin, player, projectile);
        Accurate.onPlayerFireProjectile(plugin, player, projectile);
        BloodTipped.onPlayerFireProjectile(plugin, player);
    }
}
