package com.thefishnextdoor.customenchantments.command;

import org.bukkit.command.PluginCommand;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.command.impl.CombineEnchantments;
import com.thefishnextdoor.customenchantments.command.impl.Disenchant;
import com.thefishnextdoor.customenchantments.command.impl.EnchantInfo;
import com.thefishnextdoor.customenchantments.command.impl.EnchantedBook;
import com.thefishnextdoor.customenchantments.command.impl.FCE;
import com.thefishnextdoor.customenchantments.command.impl.Fenchant;

public class Commands {
    
    public static final String RELOAD_PERMISSION = "fce.admin.reload";

    public final PluginCommand FCE_COMMAND;
    public final PluginCommand FENCHANT_COMMAND;
    public final PluginCommand ENCHANT_INFO_COMMAND;
    public final PluginCommand DISENCHANT_COMMAND;
    public final PluginCommand ENCHANTED_BOOK_COMMAND;
    public final PluginCommand COMBINE_ENCHANTMENTS_COMMAND;

    public Commands(CustomEnchantments plugin) {
        FCE_COMMAND = plugin.getCommand("fce");
        FCE fce = new FCE();
        FCE_COMMAND.setExecutor(fce);
        FCE_COMMAND.setTabCompleter(fce);

        FENCHANT_COMMAND = plugin.getCommand("fenchant");
        Fenchant fenchant = new Fenchant();
        FENCHANT_COMMAND.setExecutor(fenchant);
        FENCHANT_COMMAND.setTabCompleter(fenchant);

        ENCHANT_INFO_COMMAND = plugin.getCommand("enchantinfo");
        EnchantInfo enchantInfo = new EnchantInfo();
        ENCHANT_INFO_COMMAND.setExecutor(enchantInfo);
        ENCHANT_INFO_COMMAND.setTabCompleter(enchantInfo);

        DISENCHANT_COMMAND = plugin.getCommand("disenchant");
        Disenchant disenchant = new Disenchant();
        DISENCHANT_COMMAND.setExecutor(disenchant);
        DISENCHANT_COMMAND.setTabCompleter(disenchant);

        ENCHANTED_BOOK_COMMAND = plugin.getCommand("enchantedbook");
        EnchantedBook enchantedBook = new EnchantedBook();
        ENCHANTED_BOOK_COMMAND.setExecutor(enchantedBook);
        ENCHANTED_BOOK_COMMAND.setTabCompleter(enchantedBook);

        COMBINE_ENCHANTMENTS_COMMAND = plugin.getCommand("combineenchantments");
        CombineEnchantments combineEnchantments = new CombineEnchantments();
        COMBINE_ENCHANTMENTS_COMMAND.setExecutor(combineEnchantments);
        COMBINE_ENCHANTMENTS_COMMAND.setTabCompleter(combineEnchantments);
    }
}
