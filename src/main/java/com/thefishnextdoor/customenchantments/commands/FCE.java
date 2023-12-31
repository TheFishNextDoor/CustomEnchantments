package com.thefishnextdoor.customenchantments.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.thefishnextdoor.customenchantments.Commands;
import com.thefishnextdoor.customenchantments.Plugin;

import net.md_5.bungee.api.ChatColor;

public class FCE implements CommandExecutor, TabCompleter {
    private final Plugin plugin;
    public FCE(Plugin plugin) {
        this.plugin = plugin;
    }

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

        boolean reloadPermission = sender.hasPermission(Commands.RELOAD_PERMISSION);
        boolean fenchantPermission = sender.hasPermission(Commands.FENCHANT_PERMISSION);
        boolean enchantinfoPermission = sender.hasPermission(Commands.ENCHANTINFO_PERMISSION);
        boolean disenchantPermission = sender.hasPermission(Commands.DISENCHANT_PERMISSION);

        if (subcommand.equals("help")) {
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Fish's Custom Enchantments");
            sender.sendMessage(ChatColor.AQUA + "/fce help " + ChatColor.WHITE + "Show this message");
            if (reloadPermission) {
                sender.sendMessage(ChatColor.AQUA + "/fce reload " + ChatColor.WHITE + "Reload the plugin");
            }
            if (fenchantPermission) {
                sender.sendMessage(ChatColor.AQUA + "/fenchant <enchantment> [level] " + ChatColor.WHITE + "Enchant the item in your hand");
            }
            if (enchantinfoPermission) {
                sender.sendMessage(ChatColor.AQUA + "/enchantinfo <enchantment> " + ChatColor.WHITE + "Show information about an enchantment");
            }
            if (disenchantPermission) {
                sender.sendMessage(ChatColor.AQUA + "/disenchant <enchantment> " + ChatColor.WHITE + "Move an enchantment from an item to a book");
            }
            return true;
        }

        if (subcommand.equals("reload") && reloadPermission) {
            plugin.reload();
            sender.sendMessage(ChatColor.AQUA + "Plugin reloaded");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Unknown subcommand");
        return true;
    }
}
