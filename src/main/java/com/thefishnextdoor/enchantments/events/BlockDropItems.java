package com.thefishnextdoor.enchantments.events;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import com.thefishnextdoor.enchantments.enchantments.Telekinesis;

public class BlockDropItems implements Listener {

    @EventHandler
    public void onBlockDropItems(BlockDropItemEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        List<Item> items = event.getItems();
        Telekinesis.onBlockDropItems(player, items);
    }
}
