package fun.sunrisemc.fishchantments.events;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;

public class Grindstone implements Listener {
    private final Plugin plugin;

    public Grindstone(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onGrindstone(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getInventory() instanceof GrindstoneInventory)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        GrindstoneInventory grindstone = (GrindstoneInventory) event.getInventory();
        if (event.getSlotType() != SlotType.RESULT) return;
        ItemStack result = grindstone.getItem(event.getRawSlot());
        ItemStack zero = grindstone.getItem(0);
        ItemStack one = grindstone.getItem(1);
        if (!(plugin.hasFishchantment(zero) || plugin.hasFishchantment(one))) return;
        ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
        fishchantments.addAll(plugin.getFishchantments(one));
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            plugin.removeEnchant(result, enchantment);
        }
    }

}
