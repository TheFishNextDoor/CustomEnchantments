package fun.sunrisemc.fishchantments.events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Tilling;

public class Till implements Listener {
    private final Plugin plugin;

    public Till(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTill(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (clickedBlock == null || event.getItem() == null) return;
        Tilling.onTill(plugin, event.getPlayer(), clickedBlock);
    }
}
