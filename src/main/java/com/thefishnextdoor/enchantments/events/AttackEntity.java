package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.enchantments.Plugin;
import com.thefishnextdoor.enchantments.enchantments.AquaAspect;
import com.thefishnextdoor.enchantments.enchantments.Fling;
import com.thefishnextdoor.enchantments.enchantments.Radiance;
import com.thefishnextdoor.enchantments.enchantments.Reflection;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Crippling;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Debilitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Disorienting;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Glass;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Levitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Obscure;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Starving;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Venom;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Withering;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class AttackEntity implements Listener {
    private final Plugin plugin;

    public AttackEntity(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity)) return;
        LivingEntity reciever = (LivingEntity) event.getEntity(); 
        double damage = event.getFinalDamage();
        Entity damager = event.getDamager();
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) {
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Entity) damager = (Entity) shooter;
            if (reciever instanceof Player) {
                Player player = (Player) reciever;
                if (player.isBlocking() && damage == 0) Reflection.onDeflectProjectile(player, projectile);
            }
        }
        if (!(damage > 0)) return;
        if (!(damager instanceof Player)) return;
        Player player = (Player) damager;
        if (!RANGED_ATTACK && InventoryUtil.isRanged(InventoryUtil.getItemInUse(player).getType())) return;

        // Event modifiers
        DeathWish.onPlayerAttackEntity(player, damage, event);
        Glass.onPlayerAttackEntity(player, damage, event, RANGED_ATTACK);
        AquaAspect.onPlayerAttackEntity(plugin, player, reciever, event, RANGED_ATTACK);
        LifeSteal.onPlayerAttackEntity(player, damage, RANGED_ATTACK); // Last

        Fling.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Venom.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Withering.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        BloodTipped.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Levitating.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Radiance.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Obscure.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Disorienting.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Debilitating.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Starving.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
        Crippling.onPlayerAttackEntity(player, reciever, RANGED_ATTACK);
    }   
}
