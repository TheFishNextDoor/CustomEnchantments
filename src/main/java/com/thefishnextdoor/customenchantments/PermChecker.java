package com.thefishnextdoor.customenchantments;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class PermChecker {
    private static BlockBreakEvent checking = null;

    public static boolean canBreak(Player player, Block block) {
        boolean cancelled;
        checking = new BlockBreakEvent(block, player);
        Bukkit.getServer().getPluginManager().callEvent(checking);
        cancelled = checking.isCancelled();
        checking = null;
        return !cancelled;
    }

    public static boolean isReal(BlockBreakEvent event) {
        return event != checking;
    }
}