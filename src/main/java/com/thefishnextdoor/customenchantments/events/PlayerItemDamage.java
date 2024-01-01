package com.thefishnextdoor.customenchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantments.Unbreakable;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Glass;

public class PlayerItemDamage implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (cancel(player, event, item)) return;
        Glass.modifyDamage(player, item, event);
    }

    private static boolean cancel(Player player, PlayerItemDamageEvent event, ItemStack item) {
        if (!Unbreakable.canTakeDamage(player, item)) {
            event.setCancelled(true);
            return true;
        }
        return false;
    }
}
