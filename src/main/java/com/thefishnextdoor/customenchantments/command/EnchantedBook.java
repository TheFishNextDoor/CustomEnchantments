package com.thefishnextdoor.customenchantments.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.thefishnextdoor.customenchantments.Commands;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.InventoryTools;

import net.md_5.bungee.api.ChatColor;

public class EnchantedBook implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return Commands.allEnchantmentNames();
        }
        else {
            return null;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        
        }

        Player player = (Player) sender;
        Enchantment enchantment = EnchantTools.getEnchantment(args[0]);
        if (enchantment == null) {
            player.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }

        int level = 1;
        if (args.length >= 2) {
            level = EnchantTools.number(args[1]);
        }

        if (level < 1) {
            player.sendMessage(ChatColor.RED + "Invalid level.");
            return true;
        }

        InventoryTools.give(player, EnchantTools.enchantedBook(enchantment, level));
        return true;
    }
}
