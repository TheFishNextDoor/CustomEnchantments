package com.thefishnextdoor.customenchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.thefishnextdoor.customenchantments.enchantments.exclusive.helmet.Sustenance;

public class HungerChange implements Listener {

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Sustenance.modifyFood(player, event);
    }
}
