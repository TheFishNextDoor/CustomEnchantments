package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private final Plugin plugin;

    CommandHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return null;
        if (!(sender instanceof Player)) return null;
        if (command.getName().equalsIgnoreCase("fenchant")) {
            if (args.length == 1) return getEnchantCommandNames();
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
        if (args.length >= 2) {
            try { level = Integer.parseInt(args[1]); }
            catch (Exception e) { level = numeralToNumber(args[1]); }
        }
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
        Iterator<Enchantment> fcIter = plugin.getFishchantments().iterator();
        while (fcIter.hasNext()) {
            Enchantment enchantment = fcIter.next();
            if (getEnchantCommandName(enchantment).equalsIgnoreCase(name)) return enchantment; 
        }
        return null;
    }

    private ArrayList<String> getEnchantCommandNames() {
        ArrayList<Enchantment> enchants = plugin.getFishchantments();
        Iterator<Enchantment> enchantIter = enchants.iterator();
        ArrayList<String> names = new ArrayList<>();
        while (enchantIter.hasNext()) {
            names.add(getEnchantCommandName(enchantIter.next()));
        }
        return names;
    }

    @SuppressWarnings("deprecation")
    private String getEnchantCommandName(Enchantment enchantment) {
        return enchantment.getName().toLowerCase().replaceAll(" ", "_");
    }

    private static int numeralToNumber(String numeral) {
        switch (numeral.toUpperCase()) {
            case "X": return 10;
            case "IX": return 9;
            case "VIII": return 8;
            case "VII": return 7;
            case "VI": return 6;
            case "V": return 5;
            case "IV": return 4;
            case "III": return 3;
            case "II": return 2;
            case "I": return 1;
            default: return 0;
        }
    }
}
