package com.thefishnextdoor.enchantments.events;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class InventoryClick implements Listener {
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        Inventory inventory = event.getInventory();
        if (inventory instanceof GrindstoneInventory) {
            onGrindstone(event, (GrindstoneInventory) event.getInventory());
        }
    }

    private void onGrindstone(InventoryClickEvent event, GrindstoneInventory grindstone) {
        if (event.getSlotType() != SlotType.RESULT) return;
        ItemStack result = grindstone.getItem(event.getRawSlot());
        ItemStack zero = grindstone.getItem(0);
        ItemStack one = grindstone.getItem(1);
        EnchantUtil.verify(zero); EnchantUtil.verify(one);
        if (!(CustomEnchantment.hasCustomEnchantments(zero) || CustomEnchantment.hasCustomEnchantments(one))) return;
        ArrayList<Enchantment> fishchantments = CustomEnchantment.customEnchantments(zero);
        fishchantments.addAll(CustomEnchantment.customEnchantments(one));
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            EnchantUtil.removeEnchant(result, enchantment);
        }
    }
}
