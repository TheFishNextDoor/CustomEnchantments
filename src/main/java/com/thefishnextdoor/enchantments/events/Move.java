package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Anchor;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Boosters;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Momentum;

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
