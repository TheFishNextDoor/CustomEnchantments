package com.thefishnextdoor.customenchantments.command.impl;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.thefishnextdoor.customenchantments.Plugin;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.StringTools;

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
        PlayerInventory inventory = player.getInventory();
        ItemStack currentItem = inventory.getItemInMainHand();
        int nextSlot = (inventory.getHeldItemSlot() + 1) % 9;
        ItemStack nextItem = inventory.getItem(nextSlot);

        if (!EnchantTools.canMergeInAnvil(currentItem, nextItem)) {
            sender.sendMessage(ChatColor.RED + "These items cannot be merged.");
            return true;
        }

        ItemStack result = currentItem.clone();
        for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(nextItem).entrySet()) {
            EnchantTools.addEnchant(result, entry.getKey(), entry.getValue(), false, true);
        }

        if (EnchantTools.sameEnchantments(currentItem, result)) {
            sender.sendMessage(ChatColor.RED + "These items cannot be merged.");
            return true;
        }

        boolean creative = player.getGameMode() == GameMode.CREATIVE;
        int cost = creative ? 0 : Plugin.getSettings().COMBINE_ENCHANTMENTS_COST_LEVELS;
        if (player.getLevel() < cost) {
            sender.sendMessage(ChatColor.RED + "You need " + cost + " levels to use this command.");
            return true;
        }

        boolean confirm = args.length >= 1 && args[0].equalsIgnoreCase("confirm");
        if (!confirm) {
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Resulting Item");
            if (result.hasItemMeta() && result.getItemMeta().hasDisplayName()) {
                sender.sendMessage(ChatColor.AQUA + "Name: " + ChatColor.WHITE + result.getItemMeta().getDisplayName());
            }
            sender.sendMessage(ChatColor.AQUA + "Type: " + ChatColor.WHITE + StringTools.titleCase(result.getType().toString()));
            sender.sendMessage(ChatColor.AQUA + "Enchantments:");
            for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(result).entrySet()) {
                String name = StringTools.titleCase(entry.getKey().getKey().getKey());
                sender.sendMessage(ChatColor.WHITE + "  " + name + " " + entry.getValue());
            }
            sender.sendMessage(ChatColor.YELLOW + "Type " + ChatColor.GOLD + "/combineenchantments confirm" + ChatColor.YELLOW + " to confirm.");
            return true;
        }

        inventory.setItemInMainHand(result);
        inventory.setItem(nextSlot, null);
        if (!creative) {
            player.setLevel(player.getLevel() - cost);
        }
        sender.sendMessage(ChatColor.AQUA + "Enchantments merged.");
        return true;
    }
    
}
