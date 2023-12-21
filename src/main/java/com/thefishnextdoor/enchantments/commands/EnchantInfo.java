package com.thefishnextdoor.enchantments.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.CommandUtil;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

import net.md_5.bungee.api.ChatColor;

public class EnchantInfo implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;
        if (args.length == 1) return CommandUtil.recommendedEnchants(InventoryUtil.getItemInUse(player));
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;
        String enchantName = args[0];
        Enchantment enchantment = EnchantUtil.getEnchantment(enchantName);
        if (enchantment == null) return false;
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + CommandUtil.titleCase(EnchantUtil.name(enchantment)));
        sender.sendMessage(ChatColor.AQUA + "Max Level: " + ChatColor.WHITE + enchantment.getMaxLevel());
        String description = CustomEnchantment.desciption(enchantment);
        ArrayList<String> conflicts = getConflicts(enchantment);
        if (!conflicts.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Conflicts With: " + ChatColor.WHITE + String.join(", ", conflicts));
        }
        if (description != null) {
            sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + description);
        }
        return true;
    }
    
    private ArrayList<String> getConflicts(Enchantment enchantment) {
        ArrayList<String> conflicts = new ArrayList<>();
        for (Enchantment other : Enchantment.values()) {
            if (EnchantUtil.same(enchantment, other)) continue;
            if (enchantment.conflictsWith(other) || other.conflictsWith(enchantment)) {
                conflicts.add(CommandUtil.titleCase(EnchantUtil.name(other)));
            }
        }
        return conflicts;
    }
}
