package com.thefishnextdoor.customenchantments.event;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.customenchantments.enchantment.Precision;
import com.thefishnextdoor.customenchantments.enchantment.Range;
import com.thefishnextdoor.customenchantments.enchantment.Volley;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Destructive;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.FireBlast;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Harpoon;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Levitating;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Teleport;

public class ProjectileLaunch implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) {
            return;
        }
        
        Player player = (Player) shooter;
        Volley.summonProjectiles(player, projectile);
        Range.modifyVelocity(player, projectile);
        Precision.modifyVelocity(player, projectile);
        BloodTipped.onPlayerFireProjectile(player, projectile);
        Seeking.onPlayerFireProjectile(player, projectile);
        SalmonSlinger.convertProjectile(player, projectile);
        FireBlast.convertProjectile(player, projectile);
        Teleport.convertProjectile(player, projectile);
        Levitating.convertProjectile(player, projectile);
        Destructive.convertProjectile(player, projectile);
        Harpoon.convertProjectile(player, projectile);
    }
}
