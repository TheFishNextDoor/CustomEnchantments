package com.thefishnextdoor.customenchantments.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.CommandUtil;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;

import net.md_5.bungee.api.ChatColor;

public class EnchantInfo implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) return null;
        return CommandUtil.allEnchantmentNames();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;
        String enchantName = args[0];
        Enchantment enchantment = CommandUtil.getEnchantment(enchantName);
        if (enchantment == null) {
            sender.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }
        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + CommandUtil.titleCase(EnchantUtil.name(enchantment)));
        sender.sendMessage(ChatColor.AQUA + "Max Level: " + ChatColor.WHITE + enchantment.getMaxLevel());
        ArrayList<String> items = getItems(enchantment);
        if (!items.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Can Enchant: " + ChatColor.WHITE + String.join(", ", items));
        }
        ArrayList<String> conflicts = getConflicts(enchantment);
        if (!conflicts.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Conflicts With: " + ChatColor.WHITE + String.join(", ", conflicts));
        }
        String description = CustomEnchantment.description(enchantment);
        if (description != null) {
            sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + description);
        }
        return true;
    }

    private ArrayList<String> getItems(Enchantment enchantment) {
        ArrayList<String> items = new ArrayList<>();
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_SWORD))) items.add("Sword");
        if (enchantment.canEnchantItem(new ItemStack(Material.BOW))) items.add("Bow");
        if (enchantment.canEnchantItem(new ItemStack(Material.CROSSBOW))) items.add("Crossbow");
        if (enchantment.canEnchantItem(new ItemStack(Material.TRIDENT))) items.add("Trident");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_PICKAXE))) items.add("Pickaxe");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_SHOVEL))) items.add("Shovel");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_AXE))) items.add("Axe");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_HOE))) items.add("Hoe");
        if (enchantment.canEnchantItem(new ItemStack(Material.FISHING_ROD))) items.add("Fishing Rod");
        if (enchantment.canEnchantItem(new ItemStack(Material.SHEARS))) items.add("Shears");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_HELMET))) items.add("Helmet");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_CHESTPLATE))) items.add("Chestplate");
        if (enchantment.canEnchantItem(new ItemStack(Material.ELYTRA))) items.add("Elytra");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_LEGGINGS))) items.add("Leggings");
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_BOOTS))) items.add("Boots");
        return items;
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
