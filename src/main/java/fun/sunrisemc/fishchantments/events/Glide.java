package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Boosters;
import fun.sunrisemc.fishchantments.enchantments.Generic.Momentum;

public class Glide implements Listener {
    Plugin plugin;

    public Glide(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onGlide(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;
        if (!player.isGliding()) return;
        Momentum.onGlide(plugin, player);
        Boosters.onGlide(plugin, player);
    }
    
}
