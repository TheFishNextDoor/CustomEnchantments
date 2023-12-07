package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.enchantments.Unbreakable;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Glass;

public class ItemDamage implements Listener {

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        int damage = event.getDamage();
        Glass.onItemTakeDamage(player, item, damage, event);
        Unbreakable.onItemTakeDamage(player, item, event);
    }
}
