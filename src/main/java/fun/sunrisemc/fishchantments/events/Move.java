package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Boosters;
import fun.sunrisemc.fishchantments.enchantments.Generic.Momentum;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Anchor;

public class Move implements Listener {
    Plugin plugin;

    public Move(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;
        if (player.isGliding()) {
            Momentum.whenGliding(plugin, player);
            Boosters.whenGliding(plugin, player);
        }
        if (player.isInWater() && !player.isOnGround()) {
            Anchor.whenSwimming(plugin, player);
        }
    }
}
