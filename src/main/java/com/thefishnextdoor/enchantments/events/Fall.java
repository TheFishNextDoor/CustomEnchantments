package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Bounce;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Crush;

public class Fall implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        Entity damagedEntity = event.getEntity();
        if (!(damagedEntity instanceof Player)) return;
        Player player = (Player) damagedEntity;
        ItemStack boots = player.getInventory().getBoots();
        if (boots == null) return;
        Bounce.onFall(player, boots, event);
        Crush.onFall(player, boots, event);
    }
}
