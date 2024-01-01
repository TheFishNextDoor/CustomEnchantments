package com.thefishnextdoor.customenchantments.events;

import java.util.List;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import com.thefishnextdoor.customenchantments.enchantments.Smelting;
import com.thefishnextdoor.customenchantments.enchantments.Telekinesis;

public class BlockDropItems implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockDropItems(BlockDropItemEvent event) {
        Player player = event.getPlayer();
        List<Item> drops = event.getItems();
        Smelting.modifyDrops(player, drops);
        Telekinesis.transferDrops(player, drops);
    }
}
