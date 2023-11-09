package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.Generic.Unbreakable;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Glass;

public class ItemDamage implements Listener {
    private final Plugin plugin;

    public ItemDamage(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        int damage = event.getDamage();
        Glass.onItemTakeDamage(plugin, player, item, damage, event);
        Unbreakable.onItemTakeDamage(plugin, player, item, event);
    }
}
