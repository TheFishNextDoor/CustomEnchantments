package com.thefishnextdoor.customenchantments.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.thefishnextdoor.customenchantments.enchantments.CurseOfIronGrip;

public class DropItem implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onDropItem(PlayerDropItemEvent event) {
        CurseOfIronGrip.modifyCancelStatus(event.getPlayer(), event.getItemDrop().getItemStack(), event);
    }
}
