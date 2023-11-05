package fun.sunrisemc.fishchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;
import fun.sunrisemc.fishchantments.enchantments.Generic.Excavating;
import fun.sunrisemc.fishchantments.enchantments.Generic.Replanting;

public class BreakBlock implements Listener {
    private final Plugin plugin;

    public BreakBlock(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!Utl.PrmChkr.isReal(event)) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        Replanting.onBlockBreak(plugin, player, block, event);
        Excavating.onBlockBreak(plugin, player, block, event);
    }
}
