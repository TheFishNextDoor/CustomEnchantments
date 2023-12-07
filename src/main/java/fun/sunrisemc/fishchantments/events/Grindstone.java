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

import fun.sunrisemc.fishchantments.util.EnchantUtil;

public class Grindstone implements Listener {
    
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
        EnchantUtil.verify(zero); EnchantUtil.verify(one); // Fix broken fishchantments
        if (!(EnchantUtil.hasCustomEnchantments(zero) || EnchantUtil.hasCustomEnchantments(one))) return;
        ArrayList<Enchantment> fishchantments = EnchantUtil.customEnchantments(zero);
        fishchantments.addAll(EnchantUtil.customEnchantments(one));
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            EnchantUtil.removeEnchant(result, enchantment);
        }
    }

}
