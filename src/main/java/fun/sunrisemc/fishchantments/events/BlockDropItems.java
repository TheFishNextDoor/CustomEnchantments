package fun.sunrisemc.fishchantments.events;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Telekinesis;

public class BlockDropItems implements Listener {
    private final Plugin plugin;

    public BlockDropItems(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockDropItems(BlockDropItemEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        List<Item> items = event.getItems();
        Telekinesis.onBlockDropItems(plugin, player, items);
    }
}
