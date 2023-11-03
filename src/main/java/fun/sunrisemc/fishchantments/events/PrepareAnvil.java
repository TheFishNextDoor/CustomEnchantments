package fun.sunrisemc.fishchantments.events;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class PrepareAnvil implements Listener {
    private final Plugin plugin;

    public PrepareAnvil(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        ItemStack zero = event.getInventory().getItem(0);
        ItemStack one = event.getInventory().getItem(1);
        plugin.verify(zero); plugin.verify(one); // Fix broken fishchantments
        if (!(plugin.hasFishchantments(zero) || plugin.hasFishchantments(one))) return; // Don't worry about vanilla stuff
        if (result == null && zero != null && one != null) result = zero.clone(); // Make item always show up in result slot
        if (!plugin.canMerge(zero, one)) {
            event.setResult(null);
            return;
        }
        ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            int level = Utl.Nchnt.level(zero, enchantment);
            plugin.addEnchant(result, enchantment, level, true, false);
        }
        fishchantments = plugin.getFishchantments(one);
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            int level = Utl.Nchnt.level(one, enchantment);
            plugin.addEnchant(result, enchantment, level, false, true);
        }
        event.setResult(result);
    }
}
