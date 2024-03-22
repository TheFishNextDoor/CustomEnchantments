package com.thefishnextdoor.customenchantments.event;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.Loot;
import com.thefishnextdoor.customenchantments.enchantment.impl.Enlightenment;
import com.thefishnextdoor.customenchantments.enchantment.impl.Telekinesis;
import com.thefishnextdoor.customenchantments.EntityTag;

public class EntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        List<ItemStack> drops = event.getDrops();
        if (EntityTag.NO_DROPS.isOn(entity)) {
            drops.clear();
        }

        Player player = entity.getKiller();
        if (player == null) {
            return;
        }

        Loot.addDrops(player, entity, drops);
        Telekinesis.transferDrops(player, drops);
        Enlightenment.modifyXp(player, event);
        Telekinesis.transferXp(player, event);
    }
}
