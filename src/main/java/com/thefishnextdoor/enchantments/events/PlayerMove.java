package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Anchor;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Boosters;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Momentum;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        if (player.isGliding()) {
            Momentum.whenGliding(player);
            Boosters.whenGliding(player);
        }
        if (player.isInWater() && !((Entity) player).isOnGround()) {
            Anchor.whenSwimming(player);
        }
    }
}
