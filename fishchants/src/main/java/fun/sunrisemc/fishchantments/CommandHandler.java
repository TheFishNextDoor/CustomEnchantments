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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor, TabCompleter {
    private static final boolean NUMERALS = false;
    private final Plugin plugin;
    private ArrayList<FishchantmentCommandData> commands = new ArrayList<FishchantmentCommandData>();

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
        if (!command.getName().equalsIgnoreCase("fenchant")) return false;
        if (args.length == 0) return false;
        String commandName = args[0];
        FishchantmentCommandData data = getData(commandName);
        Enchantment enchantment = data.getEnchantment();
        if (enchantment == null) return false;
        int level = 1;
        if (args.length >= 2) {
            try { level = Integer.parseInt(args[1]); }
            catch (Exception e) { level = numeralToNumber(args[1]); }
        }
        addEnchant(player.getInventory().getItemInMainHand(), data, level);
        if (level > 0) player.sendMessage("Fishchantment added to item in hand.");
        else player.sendMessage("Fishchantment removed from item in hand.");
        return true;
    }

    void registerEnchants() {
        commands = new ArrayList<FishchantmentCommandData>();
        new FishchantmentCommandData(plugin.DESTRUCTIVE, "destructive");
        new FishchantmentCommandData(plugin.TILLING, "tilling");
        new FishchantmentCommandData(plugin.REPLANTING, "replanting");
        new FishchantmentCommandData(plugin.UNBREAKABLE, "unbreakable");
        new FishchantmentCommandData(plugin.FOOD, "food");
        new FishchantmentCommandData(plugin.WORM, "worm");
        new FishchantmentCommandData(plugin.CRUSH, "crush");
        new FishchantmentCommandData(plugin.LIFE_STEAL, "life_steal");
        new FishchantmentCommandData(plugin.FLING, "fling");
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
        for (Enchantment enchantment : Enchantment.values()) {
            new FishchantmentCommandData(enchantment, enchantment.getName().toLowerCase().replaceAll(" ", "_"));
        }
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

    FishchantmentCommandData getData(Enchantment enchantment) {
        Iterator<FishchantmentCommandData> commandIter = commands.iterator();
        while (commandIter.hasNext()) {
            FishchantmentCommandData commandData = commandIter.next();
            if (commandData.getEnchantment() == enchantment) return commandData;
        }
        return null;
    }

    ArrayList<FishchantmentCommandData> getAllData() {
        return commands;
    }
    
    public static ItemStack addEnchant(ItemStack item, FishchantmentCommandData data, Integer level) {
        if (Plugin.hasEnchant(item, data.getEnchantment())) removeEnchant(item, data);
        if (level < 1) return item;
        if (level > data.getMaxLevel()) level = data.getMaxLevel();
        item.addEnchantment(data.getEnchantment(), level);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<String>();
        if (level == 1) lore.add(0, data.getLore());
        else {
            String levelString = NUMERALS ? numberToNumeral(level) : level.toString();
            lore.add(0, data.getLore() + " " + levelString);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack removeEnchant(ItemStack item, FishchantmentCommandData data) {
        if (!Plugin.hasEnchant(item, data.getEnchantment())) return item;
        item.removeEnchantment(data.getEnchantment());
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) return item;
        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            if (!line.contains(data.getLore())) newLore.add(line);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
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

    private static String numberToNumeral(int number) {
        switch (number) {
            case 10: return "X";
            case 9: return "IX";
            case 8: return "VIII";
            case 7: return "VII";
            case 6: return "VI";
            case 5: return "V";
            case 4: return "IV";
            case 3: return "III";
            case 2: return "II";
            case 1: return "I";
            default: return "";
        }
    }
}
