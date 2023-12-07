package com.thefishnextdoor.enchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Sustenance;

public class HungerChange implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null) return;
        Sustenance.onHungerLoss(player, helmet, event);
    }
}
