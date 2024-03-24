package com.thefishnextdoor.customenchantments.event;

import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.Settings;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class PrepareAnvil implements Listener {
    
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        Settings settings = CustomEnchantments.getSettings();

        AnvilInventory anvilInventory = event.getInventory();

        if (settings.OVERRIDE_ANVIL_COST_LEVELS > -1) {
            anvilInventory.setRepairCost(settings.OVERRIDE_ANVIL_COST_LEVELS);
        }
        else {
            int cost = (int) (anvilInventory.getRepairCost() * settings.ANVIL_COST_MULTIPLIER);
            if (settings.MAX_ANVIL_COST_LEVELS > -1) {
                cost = Math.min(cost, settings.MAX_ANVIL_COST_LEVELS);
            }
            anvilInventory.setRepairCost(cost);
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

        
        for (Entry<Enchantment, Integer> entry : EnchantTools.getEnchants(result).entrySet()) {
            CustomEnchantment customEnchantment = CustomEnchantment.unWrap(entry.getKey());
            if (customEnchantment != null) {
                CustomEnchantment.addLore(customEnchantment, result, entry.getValue());
            }
        }

        event.setResult(result);
    }
}
