package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Helmet.Worm;

public class Suffocation implements Listener {
    
    @EventHandler
    public void onSuffocation(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null) return;
        Worm.onSuffocate(player, helmet, event);
    }
}
