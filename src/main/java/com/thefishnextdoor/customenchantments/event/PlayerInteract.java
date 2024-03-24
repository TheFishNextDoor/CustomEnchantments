package com.thefishnextdoor.customenchantments.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.thefishnextdoor.customenchantments.PlayerTracker;
import com.thefishnextdoor.customenchantments.enchantment.impl.Replanting;
import com.thefishnextdoor.customenchantments.enchantment.impl.Tilling;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        switch (event.getAction()) {
            case LEFT_CLICK_BLOCK:
                onLeftClickBlock(event);
                break;
            case RIGHT_CLICK_BLOCK:
                onRightClickBlock(event);
                break;
            default:
                break;
        }
    }

    private static void onLeftClickBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        PlayerTracker.get(player).setMiningFace(event.getBlockFace());
    }

    private static void onRightClickBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        Tilling.onTill(player, clickedBlock);
        Replanting.onRightClick(player, clickedBlock);
    }
}
