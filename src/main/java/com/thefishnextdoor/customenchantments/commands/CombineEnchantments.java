package com.thefishnextdoor.customenchantments.commands;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.Commands;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;

import net.md_5.bungee.api.ChatColor;

public class CombineEnchantments implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack zero = player.getInventory().getItemInMainHand();
        ItemStack one = player.getInventory().getItemInOffHand();

        if (!EnchantTools.canMergeInAnvil(zero, one)) {
            sender.sendMessage(ChatColor.RED + "These items cannot be merged.");
            return true;
        }

        ItemStack result = zero.clone();
        for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(one).entrySet()) {
            EnchantTools.addEnchant(result, entry.getKey(), entry.getValue(), false, true);
        }

        if (EnchantTools.sameEnchantments(zero, result)) {
            sender.sendMessage(ChatColor.RED + "These items cannot be merged.");
            return true;
        }

        boolean confirm = args.length >= 1 && args[0].equalsIgnoreCase("confirm");
        if (!confirm) {
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Resulting Item");
            if (result.hasItemMeta() && result.getItemMeta().hasDisplayName()) {
                sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE + result.getItemMeta().getDisplayName());
            }
            sender.sendMessage(ChatColor.AQUA + "Type: " + ChatColor.WHITE + Commands.titleCase(result.getType().toString()));
            sender.sendMessage(ChatColor.AQUA + "Enchantments:");
            for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(result).entrySet()) {
                String name = Commands.titleCase(EnchantTools.name(entry.getKey()));
                sender.sendMessage(ChatColor.WHITE + "  " + name + " " + entry.getValue());
            }
            sender.sendMessage(ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/combineenchantments confirm" + ChatColor.YELLOW + " to confirm.");
            return true;
        }

        player.getInventory().setItemInMainHand(result);
        player.getInventory().setItemInOffHand(null);
        sender.sendMessage(ChatColor.AQUA + "Enchantments merged.");
        return true;
    }
    
}
