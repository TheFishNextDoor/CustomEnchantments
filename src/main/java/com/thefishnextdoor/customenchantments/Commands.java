package com.thefishnextdoor.customenchantments;

import org.bukkit.command.PluginCommand;

import com.thefishnextdoor.customenchantments.command.CombineEnchantments;
import com.thefishnextdoor.customenchantments.command.Disenchant;
import com.thefishnextdoor.customenchantments.command.EnchantInfo;
import com.thefishnextdoor.customenchantments.command.EnchantedBook;
import com.thefishnextdoor.customenchantments.command.FCE;
import com.thefishnextdoor.customenchantments.command.Fenchant;

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
}
