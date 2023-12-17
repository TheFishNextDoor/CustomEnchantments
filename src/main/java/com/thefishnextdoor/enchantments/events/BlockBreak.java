package com.thefishnextdoor.enchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.thefishnextdoor.enchantments.PermChecker;
import com.thefishnextdoor.enchantments.enchantments.Enlightenment;
import com.thefishnextdoor.enchantments.enchantments.Excavating;
import com.thefishnextdoor.enchantments.enchantments.Replanting;
import com.thefishnextdoor.enchantments.enchantments.Telekinesis;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!PermChecker.isReal(event)) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Replanting.onBlockBreak(player, block, event);
        Excavating.onBlockBreak(player, block, event);
        Enlightenment.modifyXp(player, event);
        Telekinesis.transferXp(player, event);
    }
}
