package com.thefishnextdoor.customenchantments.commands;

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

import com.thefishnextdoor.customenchantments.util.CommandUtil;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.InventoryUtil;

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

        return CommandUtil.equippedEnchantmentNames(InventoryUtil.getMeleeItemInUse((Player) sender));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        boolean creative = player.getGameMode() == GameMode.CREATIVE;

        Enchantment enchantment = CommandUtil.getEnchantment(args[0]);
        if (enchantment == null) {
            player.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }

        if (!creative && !InventoryUtil.has(player, Material.BOOK, 1)) {
            player.sendMessage(ChatColor.RED + "You must have a book in your inventory to use this command.");
            return true;
        }

        ItemStack item = InventoryUtil.getMeleeItemInUse(player);
        int level = EnchantUtil.level(item, enchantment);
        if (!EnchantUtil.removeEnchant(item, enchantment)) {
            player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
            return true;
        }

        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        }

        if (!creative) {
            InventoryUtil.take(player, Material.BOOK, 1);
            InventoryUtil.give(player, EnchantUtil.enchantedBook(enchantment, level));
        }

        player.sendMessage(ChatColor.AQUA + "Enchantment removed from item in hand.");
        return true;
    }
}
