package fun.sunrisemc.fishchantments.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fun.sunrisemc.fishchantments.PlayerTracker;

public class Quit implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        PlayerTracker.remove(event.getPlayer());
    }
}
