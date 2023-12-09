package com.thefishnextdoor.enchantments.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

import net.md_5.bungee.api.ChatColor;

public class Fenchant implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;
        if (args.length == 1) return getEnchantCommandNames(InventoryUtil.getItemInUse(player));
        else if (args.length == 2) {
            ArrayList<String> levels = new ArrayList<>();
            Enchantment enchantment = getEnchantment(args[0]);
            if (enchantment == null) return null;
            int maxLevel = enchantment.getMaxLevel();
            for (Integer i=0; i<=maxLevel; i++) {
                levels.add(i.toString());
            }
            return levels;
        }
        else return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (args.length == 0) return false;
        String enchantName = args[0];
        Enchantment enchantment = getEnchantment(enchantName);
        if (enchantment == null) return false;
        int level = 1;
        if (args.length >= 2) level = EnchantUtil.number(args[1]);
        if (level > 0) {
            if (EnchantUtil.addEnchant(player.getInventory().getItemInMainHand(), enchantment, level, true, false)) player.sendMessage("Enchantment added to item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be added to item in hand.");
        }
        else {
            if (EnchantUtil.removeEnchant(player.getInventory().getItemInMainHand(), enchantment)) player.sendMessage("Enchantment removed from item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
        }
        return true;
    }

    private Enchantment getEnchantment(String name) {
        Iterator<Enchantment> enchantIter = EnchantUtil.allEnchantments().iterator();
        while (enchantIter.hasNext()) {
            Enchantment enchantment = enchantIter.next();
            if (getEnchantCommandName(enchantment).equalsIgnoreCase(name)) return enchantment; 
        }
        return null;
    }

    private ArrayList<String> getEnchantCommandNames(ItemStack item) {
        Iterator<Enchantment> enchantIter = EnchantUtil.allEnchantments().iterator();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> allNames = new ArrayList<>();
        while (enchantIter.hasNext()) {
            Enchantment enchantment = enchantIter.next();
            String name = getEnchantCommandName(enchantment);
            allNames.add(name);
            if (EnchantUtil.has(item, enchantment) || enchantment.canEnchantItem(item)) names.add(name);
        }
        return names.size() == 0 ? allNames : names;
    }

    private static String getEnchantCommandName(Enchantment enchantment) {
        return enchantment.getKey().getKey();
    }
}
