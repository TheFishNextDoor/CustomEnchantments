package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Flaming;

public class EntityAttack implements Listener {

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        double damage = event.getFinalDamage();
        if (!(damage > 0)) return;
        Entity damager = event.getDamager();
        if (!(damager instanceof LivingEntity)) return;
        LivingEntity attacker = (LivingEntity) damager;
        Flaming.onEntityAttackPlayer(player, attacker);
    }   
}