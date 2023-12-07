package fun.sunrisemc.fishchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Enlightenment;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Excavating;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Replanting;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Telekinesis;
import fun.sunrisemc.fishchantments.PermChecker;

public class BreakBlock implements Listener {
    private final Plugin plugin;

    public BreakBlock(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        if (!PermChecker.isReal(event)) return;
        Enlightenment.onBlockXp(plugin, player, event);
        Telekinesis.onBlockXp(plugin, player, event);
        Replanting.onBlockBreak(plugin, player, block, event);
        Excavating.onBlockBreak(plugin, player, block, event);
    }
}
