package com.thefishnextdoor.customenchantments.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.impl.Unbreakable;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Glass;

public class PlayerItemDamage implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (cancel(player, item)) {
            event.setCancelled(true);
            return;
        }
        Glass.modifyDamage(player, item, event);
    }

    private static boolean cancel(Player player, ItemStack item) {
        return !Unbreakable.canTakeDamage(player, item);
    }
}
