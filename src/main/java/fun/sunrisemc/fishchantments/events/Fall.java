package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Boot.Bounce;
import fun.sunrisemc.fishchantments.enchantment_definitions.specialties.Boot.Crush;

public class Fall implements Listener {
    private final Plugin plugin;

    public Fall(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        Entity damagedEntity = event.getEntity();
        if (!(damagedEntity instanceof Player)) return;
        Player player = (Player) damagedEntity;
        ItemStack boots = player.getInventory().getBoots();
        if (boots == null) return;
        Bounce.onFall(plugin, player, boots, event);
        Crush.onFall(plugin, player, boots, event);
    }
}
