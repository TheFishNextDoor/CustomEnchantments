package fun.sunrisemc.fishchants;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

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
            if (args.length == 1) return plugin.getEnchantManager().getEnchantCommandNames();
            else if (args.length == 2) return Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            else return null;
        }
        return null;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return false;
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("fenchant")) return fishchant(player, args);
        return false;
    }

    boolean fishchant(Player player, String[] args) {
        if (args.length == 0) return false;
        String commandName = args[0];
        String name = plugin.getEnchantManager().getName(commandName);
        if (name == null) return false;
        int level = 1;
        if (args.length >= 2) {
            try { level = Integer.parseInt(args[1]); }
            catch (Exception e) { level = Enchant.numeralToNumber(args[1]); }
        }
        Enchant.addEnchant(player.getInventory().getItemInMainHand(), name, level);
        if (level > 0) player.sendMessage("Fishchantment added to item in hand.");
        else player.sendMessage("Fishchantment removed from item in hand.");
        return true;
    }
}
