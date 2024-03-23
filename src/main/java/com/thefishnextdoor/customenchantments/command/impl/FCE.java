package com.thefishnextdoor.customenchantments.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.command.Commands;

import net.md_5.bungee.api.ChatColor;

public class FCE implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> subcommands = new ArrayList<String>();
            boolean reloadPermission = sender.hasPermission(Commands.RELOAD_PERMISSION);
            subcommands.add("help");
            if (reloadPermission) {
                subcommands.add("reload");
            }
            return subcommands;
        }
        return null; 
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String subcommand = "help";
        if (args.length > 0) {
            subcommand = args[0];
        }

        if (subcommand.equals("help")) {
            Commands commands = CustomEnchantments.getCommands();
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Fish's Custom Enchantments");
            sender.sendMessage(ChatColor.AQUA + "/fce help " + ChatColor.WHITE + "Show this message");
            if (sender.hasPermission(Commands.RELOAD_PERMISSION)) {
                sender.sendMessage(ChatColor.AQUA + "/fce reload " + ChatColor.WHITE + "Reload the plugin");
            }
            sendHelpMessage(commands.ENCHANT_INFO_COMMAND, sender);
            sendHelpMessage(commands.FENCHANT_COMMAND, sender);
            sendHelpMessage(commands.DISENCHANT_COMMAND, sender);
            sendHelpMessage(commands.ENCHANTED_BOOK_COMMAND, sender);
            sendHelpMessage(commands.COMBINE_ENCHANTMENTS_COMMAND, sender);
            return true;
        }

        if (subcommand.equals("reload") && sender.hasPermission(Commands.RELOAD_PERMISSION)) {
            CustomEnchantments.getInstance().reload();
            sender.sendMessage(ChatColor.AQUA + "Plugin reloaded");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Unknown subcommand");
        return true;
    }

    private static void sendHelpMessage(PluginCommand command, CommandSender sender) {
        if (sender.hasPermission(command.getPermission())) {
            sender.sendMessage(ChatColor.AQUA + command.getUsage() + ChatColor.WHITE + " " + command.getDescription());
        }
    }
}
