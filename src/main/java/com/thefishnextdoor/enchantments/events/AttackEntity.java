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
import com.thefishnextdoor.enchantments.definitions.Generic.AquaAspect;
import com.thefishnextdoor.enchantments.definitions.Generic.Fling;
import com.thefishnextdoor.enchantments.definitions.Generic.Radiance;
import com.thefishnextdoor.enchantments.definitions.Generic.Reflection;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.DeathWish;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.BloodTipped;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Crippling;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Debilitating;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Disorienting;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Glass;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Levitating;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.LifeSteal;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Obscure;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Starving;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Venom;
import com.thefishnextdoor.enchantments.definitions.specialties.Weapon.Withering;
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
