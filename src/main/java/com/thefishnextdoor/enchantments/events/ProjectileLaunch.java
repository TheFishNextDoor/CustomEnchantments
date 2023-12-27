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
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Destructive;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.FireBlast;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Harpoon;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Levitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Teleport;
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
        BloodTipped.onPlayerFireProjectile(player);
        SalmonSlinger.convertProjectile(player, projectile);
        FireBlast.convertProjectile(player, projectile);
        Teleport.convertProjectile(player, projectile);
        Levitating.convertProjectile(player, projectile);
        Destructive.convertProjectile(player, projectile);
        Harpoon.convertProjectile(player, projectile);
    }
}
