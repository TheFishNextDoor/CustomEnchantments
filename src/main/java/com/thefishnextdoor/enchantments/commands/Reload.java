package com.thefishnextdoor.enchantments.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import com.thefishnextdoor.enchantments.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Reload implements CommandExecutor, TabCompleter{
    private final Plugin plugin;

    public Reload(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return false;
        if (!command.getName().equalsIgnoreCase("reloadenchantments")) return false;
        plugin.reload();
        sender.sendMessage(ChatColor.AQUA + "Reloaded Fish's Custom Enchantments.");
        return true;
    }
}
