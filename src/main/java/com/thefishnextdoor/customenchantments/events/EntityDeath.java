package com.thefishnextdoor.customenchantments.events;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.Loot;
import com.thefishnextdoor.customenchantments.Settings;
import com.thefishnextdoor.customenchantments.enchantments.Enlightenment;
import com.thefishnextdoor.customenchantments.enchantments.Telekinesis;

public class EntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!Settings.MOBS_DROP_BOOKS) return;
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (player == null) return;
        List<ItemStack> drops = event.getDrops();
        Loot.addDrops(player, entity, drops);
        Telekinesis.transferDrops(player, drops);
        Enlightenment.modifyXp(player, event);
        Telekinesis.transferXp(player, event);
    }
}
