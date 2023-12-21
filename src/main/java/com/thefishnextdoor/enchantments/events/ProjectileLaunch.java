package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.enchantments.enchantments.Precision;
import com.thefishnextdoor.enchantments.enchantments.Range;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.FireBlast;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Volley;

public class ProjectileLaunch implements Listener {

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Range.modifyVelocity(player, projectile);
        Precision.modifyVelocity(player, projectile);
        Volley.onPlayerFireProjectile(player, projectile);
        SalmonSlinger.onPlayerFireProjectile(player, projectile);
        FireBlast.onPlayerFireProjectile(player, projectile);
        BloodTipped.onPlayerFireProjectile(player);
    }
}
