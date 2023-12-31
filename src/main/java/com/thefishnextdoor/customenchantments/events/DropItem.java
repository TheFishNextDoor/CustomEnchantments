package com.thefishnextdoor.customenchantments.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.thefishnextdoor.customenchantments.enchantments.CurseOfIronGrip;

public class DropItem implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        if (event.isCancelled()) return;
        CurseOfIronGrip.modifyCancelStatus(event.getPlayer(), event.getItemDrop().getItemStack(), event);
    }
}
