package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.enchantments.definitions.Generic.Precision;
import com.thefishnextdoor.enchantments.definitions.Generic.Range;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.BloodTipped;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Volley;

public class FireProjectile implements Listener {

    @EventHandler
    public void onFireProjectile(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Range.onPlayerFireProjectile(player, projectile);
        Precision.onPlayerFireProjectile(player, projectile);
        Volley.onPlayerFireProjectile(player, projectile);
        BloodTipped.onPlayerFireProjectile(player);
    }
}
