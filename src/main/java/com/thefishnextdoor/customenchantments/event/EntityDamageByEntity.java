package com.thefishnextdoor.customenchantments.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.projectiles.ProjectileSource;

import com.thefishnextdoor.customenchantments.enchantment.AquaAspect;
import com.thefishnextdoor.customenchantments.enchantment.Fling;
import com.thefishnextdoor.customenchantments.enchantment.Radiance;
import com.thefishnextdoor.customenchantments.enchantment.Reflection;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.Flaming;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Crippling;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Debilitating;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Disorienting;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Glass;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Obscure;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Starving;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Venom;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Withering;
import com.thefishnextdoor.customenchantments.util.InventoryTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class EntityDamageByEntity implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getCause() != DamageCause.ENTITY_ATTACK) {
            return;
        }
        Entity reciever = event.getEntity();
        Entity damager = event.getDamager();

        // Account for fired arrows
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) {
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Entity) {
                damager = (Entity) shooter;
            }
            if (event.getFinalDamage() == 0 && reciever instanceof Player) {
                Player player = (Player) reciever;
                if (player.isBlocking()) {
                    Reflection.onDeflectProjectile(player, projectile);
                }
            }
        }

        if (!(event.getFinalDamage() > 0)) {
            return;
        }

        if (reciever instanceof Player && damager instanceof LivingEntity) {
            onEntityAttackPlayer((Player) reciever, (LivingEntity) damager);
        }

        if (damager instanceof Player && reciever instanceof LivingEntity) {
            onPlayerAttackEntity((Player) damager, (LivingEntity) reciever, event, RANGED_ATTACK);
        }
    }


    private static void onEntityAttackPlayer(Player player, LivingEntity entity) {
        Flaming.onEntityAttackPlayer(player, entity);
    }
    
    private void onPlayerAttackEntity(Player player, LivingEntity entity, EntityDamageByEntityEvent event, boolean RANGED_ATTACK) {
        if (!RANGED_ATTACK && MaterialTools.isRangedWeapon(InventoryTools.getMeleeItemInUse(player).getType())) {
            return;
        }
        DeathWish.modifyDamage(player, event);
        Glass.modifyDamage(player, event, RANGED_ATTACK);
        AquaAspect.modifyDamage(player, entity, event, RANGED_ATTACK);
        LifeSteal.onPlayerAttackEntity(player, event, RANGED_ATTACK);
        Fling.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Venom.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Withering.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Radiance.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Obscure.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Disorienting.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Debilitating.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Starving.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
        Crippling.onPlayerAttackEntity(player, entity, RANGED_ATTACK);
    }
}
