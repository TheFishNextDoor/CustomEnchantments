package com.thefishnextdoor.customenchantments.events;

import java.util.ArrayList;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

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
            ArrayList<Enchantment> fishchantments = CustomEnchantment.customEnchantments(zero);
            for (int i = 0; i < fishchantments.size(); i++) {
                Enchantment enchantment = fishchantments.get(i);
                int level = EnchantTools.level(zero, enchantment);
                EnchantTools.addEnchant(result, enchantment, level, true, false);
            }
        }
        
        ArrayList<Enchantment> enchantments = EnchantTools.enchantments(one);
        for (int i = 0; i < enchantments.size(); i++) {
            Enchantment enchantment = enchantments.get(i);
            int level = EnchantTools.level(one, enchantment);
            EnchantTools.addEnchant(result, enchantment, level, false, true);
        }
        if (EnchantTools.sameEnchantments(zero, result) && cloned) {
            event.setResult(null);
            return;
        }
        event.setResult(result);
    }
}
