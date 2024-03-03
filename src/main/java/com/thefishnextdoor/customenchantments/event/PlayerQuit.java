package com.thefishnextdoor.customenchantments.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.thefishnextdoor.customenchantments.PlayerTracker;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PlayerTracker.remove(event.getPlayer());
    }
}
