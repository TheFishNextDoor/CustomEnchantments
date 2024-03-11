package com.thefishnextdoor.customenchantments;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.command.CombineEnchantments;
import com.thefishnextdoor.customenchantments.command.Disenchant;
import com.thefishnextdoor.customenchantments.command.EnchantInfo;
import com.thefishnextdoor.customenchantments.command.EnchantedBook;
import com.thefishnextdoor.customenchantments.command.FCE;
import com.thefishnextdoor.customenchantments.command.Fenchant;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Commands {
    
    public static final String RELOAD_PERMISSION = "fce.admin.reload";

    public final PluginCommand FCE_COMMAND;
    public final PluginCommand FENCHANT_COMMAND;
    public final PluginCommand ENCHANT_INFO_COMMAND;
    public final PluginCommand DISENCHANT_COMMAND;
    public final PluginCommand ENCHANTED_BOOK_COMMAND;
    public final PluginCommand COMBINE_ENCHANTMENTS_COMMAND;

    public Commands(Plugin plugin) {
        FCE_COMMAND = plugin.getCommand("fce");
        FCE_COMMAND.setExecutor(new FCE());

        FENCHANT_COMMAND = plugin.getCommand("fenchant");
        FENCHANT_COMMAND.setExecutor(new Fenchant());

        ENCHANT_INFO_COMMAND = plugin.getCommand("enchantinfo");
        ENCHANT_INFO_COMMAND.setExecutor(new EnchantInfo());

        DISENCHANT_COMMAND = plugin.getCommand("disenchant");
        DISENCHANT_COMMAND.setExecutor(new Disenchant());

        ENCHANTED_BOOK_COMMAND = plugin.getCommand("enchantedbook");
        ENCHANTED_BOOK_COMMAND.setExecutor(new EnchantedBook());

        COMBINE_ENCHANTMENTS_COMMAND = plugin.getCommand("combineenchantments");
        COMBINE_ENCHANTMENTS_COMMAND.setExecutor(new CombineEnchantments());
    }

    public static ArrayList<String> recommendedEnchantmentNames(ItemStack item) {
        if (item == null) {
            return allEnchantmentNames();
        }

        if (item.getType() == Material.BOOK) {
            return allEnchantmentNames();
        }
        
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            if (EnchantTools.has(item, enchantment) || enchantment.canEnchantItem(item)) {
                names.add(EnchantTools.name(enchantment));
            }
        }
        return names.isEmpty() ? allEnchantmentNames() : names;
    }

    public static ArrayList<String> equippedEnchantmentNames(ItemStack item) {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            if (EnchantTools.has(item, enchantment)) {
                names.add(EnchantTools.name(enchantment));
            }
        }
        return names;
    }

    public static ArrayList<String> allEnchantmentNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Enchantment enchantment : Enchantment.values()) {
            names.add(EnchantTools.name(enchantment));
        }
        return names;
    }
}
