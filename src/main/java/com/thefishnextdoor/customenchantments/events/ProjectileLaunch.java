package com.thefishnextdoor.customenchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.customenchantments.enchantments.Precision;
import com.thefishnextdoor.customenchantments.enchantments.Range;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Destructive;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.FireBlast;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Harpoon;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Levitating;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Teleport;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Volley;

public class ProjectileLaunch implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) {
            return;
        }
        
        Player player = (Player) shooter;
        Range.modifyVelocity(player, projectile);
        Precision.modifyVelocity(player, projectile);
        Volley.onPlayerFireProjectile(player, projectile);
        BloodTipped.onPlayerFireProjectile(player);
        Seeking.onPlayerFireProjectile(player, projectile);
        SalmonSlinger.convertProjectile(player, projectile);
        FireBlast.convertProjectile(player, projectile);
        Teleport.convertProjectile(player, projectile);
        Levitating.convertProjectile(player, projectile);
        Destructive.convertProjectile(player, projectile);
        Harpoon.convertProjectile(player, projectile);
    }
}
