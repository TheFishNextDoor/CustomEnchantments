package com.thefishnextdoor.customenchantments.command.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.InventoryTools;

import net.md_5.bungee.api.ChatColor;

public class Fenchant implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }

        Player player = (Player) sender;
        if (args.length == 1) {
            return EnchantTools.namesOfCompatibleEnchantments(InventoryTools.getMeleeItemInUse(player));
        }
        else if (args.length == 2) {
            Enchantment enchantment = EnchantTools.getEnchantmentFromName(args[0]);
            if (enchantment == null) {
                return null;
            }

            ArrayList<String> levels = new ArrayList<>();
            int maxLevel = enchantment.getMaxLevel();
            for (Integer i=0; i<=maxLevel; i++) {
                levels.add(i.toString());
            }
            return levels;
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
        Enchantment enchantment = EnchantTools.getEnchantmentFromName(args[0]);
        if (enchantment == null) {
            player.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }

        int level = 1;
        if (args.length >= 2) {
            level = EnchantTools.number(args[1]);
        }

        if (level > 0) {
            if (EnchantTools.addEnchant(player.getInventory().getItemInMainHand(), enchantment, level, true, false)) {
                player.sendMessage("Enchantment added to item in hand.");
            }
            else {
                player.sendMessage(ChatColor.RED + "Enchantment could not be added to item in hand.");
            }
        }
        else {
            if (EnchantTools.removeEnchant(player.getInventory().getItemInMainHand(), enchantment)) {
                player.sendMessage("Enchantment removed from item in hand.");
            }
            else {
                player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
            }
        }
        return true;
    }
}
