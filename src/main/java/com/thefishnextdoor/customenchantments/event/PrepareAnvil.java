package com.thefishnextdoor.customenchantments.event;

import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class PrepareAnvil implements Listener {
    
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        AnvilInventory anvilInventory = event.getInventory();
        Integer overrideCost = CustomEnchantments.getSettings().OVERRIDE_ANVIL_COST_LEVELS;
        if (overrideCost > -1) {
            anvilInventory.setRepairCost(overrideCost);
        }

        ItemStack result = event.getResult();
        ItemStack zero = anvilInventory.getItem(0);
        ItemStack one = anvilInventory.getItem(1);

        EnchantTools.verify(zero); EnchantTools.verify(one);
        if (!(CustomEnchantment.hasCustomEnchantments(zero) || CustomEnchantment.hasCustomEnchantments(one))) {
            return;
        }

        if (!EnchantTools.canMergeInAnvil(zero, one)) {
            return;
        }

        
        for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(result).entrySet()) {
            CustomEnchantment customEnchantment = CustomEnchantment.unWrap(entry.getKey());
            if (customEnchantment != null) {
                CustomEnchantment.addLore(customEnchantment, result, entry.getValue());
            }
        }

        event.setResult(result);
    }
}
