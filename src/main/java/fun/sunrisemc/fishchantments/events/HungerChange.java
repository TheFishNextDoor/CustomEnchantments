package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Helmet.Sustenance;

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
