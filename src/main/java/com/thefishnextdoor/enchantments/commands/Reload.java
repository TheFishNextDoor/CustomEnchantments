package com.thefishnextdoor.enchantments.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.thefishnextdoor.enchantments.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Reload implements CommandExecutor {
    private final Plugin plugin;

    public Reload(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.reload();
        sender.sendMessage(ChatColor.AQUA + "Reloaded Fish's Custom Enchantments.");
        return true;
    }
}
