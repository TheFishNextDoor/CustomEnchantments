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

public class BreakBlock implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        if (!PermChecker.isReal(event)) return;
        Enlightenment.onBlockXp(player, event);
        Telekinesis.onBlockXp(player, event);
        Replanting.onBlockBreak(player, block, event);
        Excavating.onBlockBreak(player, block, event);
    }
}
