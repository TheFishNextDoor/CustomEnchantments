package com.thefishnextdoor.customenchantments.event;

import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class PrepareAnvil implements Listener {
    
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        ItemStack zero = event.getInventory().getItem(0);
        ItemStack one = event.getInventory().getItem(1);
        EnchantTools.verify(zero); EnchantTools.verify(one);
        if (!(CustomEnchantment.hasCustomEnchantments(zero) || CustomEnchantment.hasCustomEnchantments(one))) {
            return;
        }

        if (!EnchantTools.canMergeInAnvil(zero, one)) {
            return;
        }

        boolean cloned = false;
        if (result == null) {
            result = zero.clone();
            cloned = true;
        }
        else {
            for (Entry<Enchantment, Integer> entry : CustomEnchantment.customEnchantments(zero).entrySet()) {
                EnchantTools.addEnchant(result, entry.getKey(), entry.getValue(), true, false);
            }
        }
        
        for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(one).entrySet()) {
            EnchantTools.addEnchant(result, entry.getKey(), entry.getValue(), false, true);
        }
        if (EnchantTools.sameEnchantments(zero, result) && cloned) {
            event.setResult(null);
            return;
        }
        event.setResult(result);
    }
}
