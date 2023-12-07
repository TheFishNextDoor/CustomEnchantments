package com.thefishnextdoor.enchantments.events;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.Loot;
import com.thefishnextdoor.enchantments.Plugin;
import com.thefishnextdoor.enchantments.definitions.Generic.Enlightenment;
import com.thefishnextdoor.enchantments.definitions.Generic.Telekinesis;

public class EntityDeath implements Listener {
    private static final boolean DROP_BOOKS = true;
    private final Plugin plugin;

    public EntityDeath(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!DROP_BOOKS) return;
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        if (player == null) return;
        List<ItemStack> drops = event.getDrops();
        Loot.addLoot(plugin, player, entity, drops); // Must run before telekinesis
        Enlightenment.onMobXp(player, event); // Must run before telekinesis
        Telekinesis.onMobXp(player, event);
        Telekinesis.onMobLoot(player, drops);
    }
}
