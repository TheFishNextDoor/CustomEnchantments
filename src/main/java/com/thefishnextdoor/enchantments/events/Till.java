package com.thefishnextdoor.enchantments.events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.thefishnextdoor.enchantments.definitions.Generic.Tilling;

public class Till implements Listener {

    @EventHandler
    public void onTill(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (clickedBlock == null || event.getItem() == null) return;
        Tilling.onTill(event.getPlayer(), clickedBlock);
    }
}
