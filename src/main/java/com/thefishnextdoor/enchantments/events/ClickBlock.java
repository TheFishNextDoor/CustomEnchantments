package com.thefishnextdoor.enchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.thefishnextdoor.enchantments.PlayerTracker;
import com.thefishnextdoor.enchantments.definitions.Generic.Replanting;

public class ClickBlock implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK) leftClick(event);
        else if (action == Action.RIGHT_CLICK_BLOCK) rightClick(event);
    }

    private void leftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        PlayerTracker.get(player).setMiningFace(event.getBlockFace());
    }

    private void rightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        Replanting.onRightClick(player, clickedBlock);
    }
}
