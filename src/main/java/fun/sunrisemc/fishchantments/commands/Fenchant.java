package fun.sunrisemc.fishchantments.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Util;
import fun.sunrisemc.fishchantments.Util.Inventory;
import net.md_5.bungee.api.ChatColor;

public class Fenchant implements CommandExecutor, TabCompleter {
    private final Plugin plugin;

    public Fenchant(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return null;
        if (!(sender instanceof Player)) return null;
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("fenchant")) {
            if (args.length == 1) return getEnchantCommandNames(Inventory.getItemInUse(player));
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
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return false;
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (!command.getName().equalsIgnoreCase("fenchant")) return false;
        if (args.length == 0) return false;
        String commandName = args[0];
        Enchantment enchantment = getEnchantment(commandName);
        if (enchantment == null) return false;
        int level = 1;
        if (args.length >= 2) level = Util.Enchant.number(args[1]);
        if (level > 0) {
            if (plugin.addEnchant(player.getInventory().getItemInMainHand(), enchantment, level, true, false)) player.sendMessage("Enchantment added to item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be added to item in hand.");
        }
        else {
            if (plugin.removeEnchant(player.getInventory().getItemInMainHand(), enchantment)) player.sendMessage("Enchantment removed from item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
        }
        return true;
    }

    private Enchantment getEnchantment(String name) {
        Iterator<Enchantment> enchantIter = allEnchantments().iterator();
        while (enchantIter.hasNext()) {
            Enchantment enchantment = enchantIter.next();
            if (getEnchantCommandName(enchantment).equalsIgnoreCase(name)) return enchantment; 
        }
        return null;
    }

    private ArrayList<String> getEnchantCommandNames(ItemStack item) {
        Iterator<Enchantment> enchantIter = allEnchantments().iterator();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> allNames = new ArrayList<>();
        while (enchantIter.hasNext()) {
            Enchantment enchantment = enchantIter.next();
            String name = getEnchantCommandName(enchantment);
            allNames.add(name);
            if (Util.Enchant.has(item, enchantment) || enchantment.canEnchantItem(item)) names.add(name);
        }
        return names.size() == 0 ? allNames : names;
    }

    private ArrayList<Enchantment> allEnchantments() {
        ArrayList<Enchantment> enchants = plugin.getFishchantments();
        enchants.addAll(Arrays.asList(Enchantment.values()));
        return enchants;
    }

    private static String getEnchantCommandName(Enchantment enchantment) {
        return enchantment.getKey().getKey();
    }
}
