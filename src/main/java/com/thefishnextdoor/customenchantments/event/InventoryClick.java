package com.thefishnextdoor.customenchantments.event;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class InventoryClick implements Listener {
    
    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory instanceof GrindstoneInventory) {
            onGrindstone(event, (GrindstoneInventory) event.getInventory());
        }
    }

    private void onGrindstone(InventoryClickEvent event, GrindstoneInventory grindstone) {
        if (event.getSlotType() != SlotType.RESULT) {
            return;
        }

        ItemStack result = grindstone.getItem(event.getRawSlot());
        ItemStack zero = grindstone.getItem(0);
        ItemStack one = grindstone.getItem(1);
        EnchantTools.verify(zero); EnchantTools.verify(one);
        if (!(CustomEnchantment.hasCustomEnchantments(zero) || CustomEnchantment.hasCustomEnchantments(one))) {
            return;
        }

        for (Enchantment enchantment : CustomEnchantment.customEnchantments(zero).keySet()) {
            EnchantTools.removeEnchant(result, enchantment);
        }

        for (Enchantment enchantment : CustomEnchantment.customEnchantments(one).keySet()) {
            EnchantTools.removeEnchant(result, enchantment);
        }


    }
}
