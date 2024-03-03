package com.thefishnextdoor.customenchantments.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.thefishnextdoor.customenchantments.PermChecker;
import com.thefishnextdoor.customenchantments.enchantment.Enlightenment;
import com.thefishnextdoor.customenchantments.enchantment.Excavating;
import com.thefishnextdoor.customenchantments.enchantment.Replanting;
import com.thefishnextdoor.customenchantments.enchantment.Telekinesis;
import com.thefishnextdoor.customenchantments.enchantment.TreeFeller;

public class BlockBreak implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!PermChecker.isReal(event)) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Replanting.onBlockBreak(player, block, event);
        Excavating.onBlockBreak(player, block, event);
        TreeFeller.onBlockBreak(player, block, event);
        Enlightenment.modifyXp(player, event);
        Telekinesis.transferXp(player, event);
    }
}
