package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Boot.Anchor;
import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Elytra.Boosters;
import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Elytra.Momentum;

public class Move implements Listener {

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;
        if (player.isGliding()) {
            Momentum.whenGliding(player);
            Boosters.whenGliding(player);
        }
        if (player.isInWater() && !player.isOnGround()) {
            Anchor.whenSwimming(player);
        }
    }
}
