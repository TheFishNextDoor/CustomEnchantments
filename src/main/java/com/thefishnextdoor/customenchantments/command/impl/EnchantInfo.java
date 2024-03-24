package com.thefishnextdoor.customenchantments.command.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.StringTools;

import net.md_5.bungee.api.ChatColor;

public class EnchantInfo implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return EnchantTools.namesOfAllEnchantments();
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

        String enchantName = args[0];
        Enchantment enchantment = EnchantTools.getEnchantFromName(enchantName);
        if (enchantment == null) {
            sender.sendMessage(ChatColor.RED + "Enchantment not found.");
            return true;
        }

        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + StringTools.titleCase(enchantment.getKey().getKey()));
        sender.sendMessage(ChatColor.AQUA + "Max Level: " + ChatColor.WHITE + enchantment.getMaxLevel());

        ArrayList<String> items = getItems(enchantment);
        if (!items.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Can Enchant: " + ChatColor.WHITE + String.join(", ", items));
        }

        ArrayList<String> conflicts = getConflicts(enchantment);
        if (!conflicts.isEmpty()) {
            sender.sendMessage(ChatColor.AQUA + "Conflicts With: " + ChatColor.WHITE + String.join(", ", conflicts));
        }

        CustomEnchantment customEnchantment = CustomEnchantment.unWrap(enchantment);
        if (customEnchantment != null) {
            String description = customEnchantment.getDescription();
            if (description != null) {
                sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + description);
            }
        }

        return true;
    }

    private ArrayList<String> getItems(Enchantment enchantment) {
        ArrayList<String> items = new ArrayList<>();
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_SWORD))) {
            items.add("Sword");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.BOW))) {
            items.add("Bow");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.CROSSBOW))) {
            items.add("Crossbow");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.TRIDENT))) {
            items.add("Trident");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_PICKAXE))) {
            items.add("Pickaxe");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_SHOVEL))) {
            items.add("Shovel");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_AXE))) {
            items.add("Axe");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_HOE))) {
            items.add("Hoe");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.FISHING_ROD))) {
            items.add("Fishing Rod");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.SHEARS))) {
            items.add("Shears");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_HELMET))) {
            items.add("Helmet");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_CHESTPLATE))) {
            items.add("Chestplate");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.ELYTRA))) {
            items.add("Elytra");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_LEGGINGS))) {
            items.add("Leggings");
        }
        if (enchantment.canEnchantItem(new ItemStack(Material.DIAMOND_BOOTS))) {
            items.add("Boots");
        }
        return items;
    } 
    
    private ArrayList<String> getConflicts(Enchantment enchantment) {
        ArrayList<String> conflicts = new ArrayList<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment other = allEnchantments.next();
            if (EnchantTools.same(enchantment, other)) {
                continue;
            }
            
            if (enchantment.conflictsWith(other) || other.conflictsWith(enchantment)) {
                conflicts.add(StringTools.titleCase(other.getKey().getKey()));
            }
        }
        return conflicts;
    }
}
