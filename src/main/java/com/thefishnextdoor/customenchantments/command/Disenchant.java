package com.thefishnextdoor.customenchantments.command;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.Plugin;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.InventoryTools;

import net.md_5.bungee.api.ChatColor;

public class Disenchant implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return null;
        }

        if (!(sender instanceof Player)) {
            return null;
        }

        return EnchantTools.namesOfEnchantmentsOnItem(InventoryTools.getMeleeItemInUse((Player) sender));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Enchantment enchantment = EnchantTools.getEnchantmentFromName(args[0]);
        if (enchantment == null) {
            player.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }

        boolean creative = player.getGameMode() == GameMode.CREATIVE;
        if (!creative && !InventoryTools.has(player, Material.BOOK, 1)) {
            player.sendMessage(ChatColor.RED + "You must have a book in your inventory to use this command.");
            return true;
        }

        int cost = creative ? 0 : Plugin.getSettings().DISENCHANT_COST_LEVELS;
        if (player.getLevel() < cost) {
            player.sendMessage(ChatColor.RED + "You need " + cost + " levels to use this command.");
            return true;
        }

        ItemStack item = InventoryTools.getMeleeItemInUse(player);
        int level = EnchantTools.level(item, enchantment);
        boolean confirm = args.length >= 2 && args[1].equalsIgnoreCase("confirm");
        if (level > enchantment.getMaxLevel() && !confirm && !creative) {
            player.sendMessage(ChatColor.YELLOW + "Removing this enchantment will reduce its level from " + level + " to "
                    + enchantment.getMaxLevel() + ". Use " + ChatColor.GOLD + "/disenchant " + args[0] + " confirm" 
                    + ChatColor.YELLOW + " to confirm.");
            return true;
        }
        
        level = Math.min(level, enchantment.getMaxLevel());
        if (!EnchantTools.removeEnchant(item, enchantment)) {
            player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
            return true;
        }

        if (!creative) {
            InventoryTools.take(player, Material.BOOK, 1);
            InventoryTools.give(player, EnchantTools.enchantedBook(enchantment, level));
            player.setLevel(player.getLevel() - cost);
        }
        player.sendMessage(ChatColor.AQUA + "Enchantment removed from item in hand.");
        return true;
    }
}
