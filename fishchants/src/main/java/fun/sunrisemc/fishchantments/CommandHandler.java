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
            if (addEnchant(player.getInventory().getItemInMainHand(), enchantment, level, true)) player.sendMessage("Enchantment added to item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be added to item in hand.");
        }
        else {
            if (removeEnchant(player.getInventory().getItemInMainHand(), enchantment)) player.sendMessage("Enchantment removed from item in hand.");
            else player.sendMessage(ChatColor.RED + "Enchantment could not be removed from item in hand.");
        }
        return true;
    }

    private boolean addEnchant(ItemStack item, Enchantment enchantment, Integer level, boolean force) {
        if (level < 1) return false;
        if (Plugin.hasEnchant(item, enchantment) && (force || level > Plugin.getEnchantLevel(item, enchantment))) removeEnchant(item, enchantment);
        if (level > enchantment.getMaxLevel()) level = enchantment.getMaxLevel();
        try { item.addEnchantment(enchantment, level); }
        catch (Exception e) { return false; }
        if (!plugin.isFishchantment(enchantment)) return true;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<String>();
        lore.add(0, getLore(enchantment, level));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }

    private boolean removeEnchant(ItemStack item, Enchantment enchantment) {
        if (!Plugin.hasEnchant(item, enchantment)) return false;
        item.removeEnchantment(enchantment);
        if (!plugin.isFishchantment(enchantment)) return true;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) return true;
        List<String> newLore = new ArrayList<>();
        String enchantLore = getLore(enchantment, 1);
        for (String line : lore) {
            if (!line.contains(enchantLore)) newLore.add(line);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
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

    private String getEnchantCommandName(Enchantment enchantment) {
        return enchantment.getName().toLowerCase().replaceAll(" ", "_");
    }

    private static String getLore(Enchantment enchantment, Integer level) {
        if (level < 0) return null;
        String lore = ChatColor.GRAY + enchantment.getName();
        if (level == 1) return lore;
        else return lore + " " + (NUMERALS ? numberToNumeral(level) : level.toString());
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
