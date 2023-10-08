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
    private ArrayList<FishchantmentCommandData> commands = new ArrayList<FishchantmentCommandData>();
    private final Plugin plugin;

    CommandHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    class FishchantmentCommandData {
        private final Enchantment ENCHANT;
        private final String COMMAND_NAME;

        FishchantmentCommandData(Enchantment enchant, String commandName) {
            this.ENCHANT = enchant;
            this.COMMAND_NAME = commandName;
            commands.add(this);
        }

        Enchantment getEnchantment() {
            return ENCHANT;
        }

        String getCommandName() {
            return COMMAND_NAME;
        }

        String getLore() {
            return ChatColor.GRAY + ENCHANT.getName();
        }

        int getMaxLevel() {
            return ENCHANT.getMaxLevel();
        }
    }

    void registerEnchants() {
        commands = new ArrayList<FishchantmentCommandData>();
        new FishchantmentCommandData(plugin.DESTRUCTIVE, "destructive");
        new FishchantmentCommandData(plugin.TILLING, "tilling");
        new FishchantmentCommandData(plugin.UNBREAKABLE, "unbreakable");
        new FishchantmentCommandData(plugin.LIFE_STEAL, "life_steal");
        new FishchantmentCommandData(plugin.RANGE, "range");
        new FishchantmentCommandData(plugin.ACCURATE, "accurate");
        new FishchantmentCommandData(plugin.POISON, "poison");
        new FishchantmentCommandData(plugin.WITHER, "wither");
        new FishchantmentCommandData(plugin.HELIUM, "levitation");
        new FishchantmentCommandData(plugin.GLOWING, "glowing");
        new FishchantmentCommandData(plugin.BLINDNESS, "blindess");
        new FishchantmentCommandData(plugin.CONFUSION, "confusion");
        new FishchantmentCommandData(plugin.WEAKNESS, "weakness");
        new FishchantmentCommandData(plugin.HUNGER, "hunger");
        new FishchantmentCommandData(plugin.SLOWNESS, "slowness");
    }

    ArrayList<String> getEnchantCommandNames() {
        ArrayList<String> commandNames = new ArrayList<String>();
        Iterator<FishchantmentCommandData> commandIter = commands.iterator();
        while (commandIter.hasNext()) {
            commandNames.add(commandIter.next().getCommandName());
        }
        return commandNames;
    }

    FishchantmentCommandData getData(String commandName) {
        Iterator<FishchantmentCommandData> commandIter = commands.iterator();
        while (commandIter.hasNext()) {
            FishchantmentCommandData commandData = commandIter.next();
            if (commandData.getCommandName().equalsIgnoreCase(commandName)) return commandData;
        }
        return null;
    }

    ArrayList<FishchantmentCommandData> getAllData() {
        return commands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(command.getPermission())) return null;
        if (!(sender instanceof Player)) return null;
        if (command.getName().equalsIgnoreCase("fenchant")) {
            if (args.length == 1) return getEnchantCommandNames();
            else if (args.length == 2) {
                ArrayList<String> levels = new ArrayList<>();
                FishchantmentCommandData data = getData(args[0]);
                if (data == null) return null;
                int maxLevel = data.getMaxLevel();
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
        if (command.getName().equalsIgnoreCase("fenchant")) return fishchant(player, args);
        return false;
    }

    boolean fishchant(Player player, String[] args) {
        if (args.length == 0) return false;
        String commandName = args[0];
        FishchantmentCommandData data = getData(commandName);
        Enchantment enchantment = data.getEnchantment();
        if (enchantment == null) return false;
        int level = 1;
        if (args.length >= 2) {
            try { level = Integer.parseInt(args[1]); }
            catch (Exception e) { level = Plugin.numeralToNumber(args[1]); }
        }
        Plugin.addEnchant(player.getInventory().getItemInMainHand(), data, level);
        if (level > 0) player.sendMessage("Fishchantment added to item in hand.");
        else player.sendMessage("Fishchantment removed from item in hand.");
        return true;
    }
}
