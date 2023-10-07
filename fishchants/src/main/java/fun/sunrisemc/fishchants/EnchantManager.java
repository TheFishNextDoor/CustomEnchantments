package fun.sunrisemc.fishchants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

class EnchantManager {
    private static final boolean NUMERALS = true;
    private ArrayList<FishchantmentCommandData> commands = new ArrayList<FishchantmentCommandData>();

    void registerEnchants() {
        commands = new ArrayList<FishchantmentCommandData>();
        new FishchantmentCommandData(Enchants.GrassSeeds.NAME, "grass_seeds");
        new FishchantmentCommandData(Enchants.LifeSteal.NAME, "life_steal");
    }

    class FishchantmentCommandData {
        private final String NAME;
        private final String COMMAND_NAME;

        FishchantmentCommandData(String name, String commandName) {
            this.NAME = name;
            this.COMMAND_NAME = commandName;
            commands.add(this);
        }

        String getName() {
            return NAME;
        }

        String getCommandName() {
            return COMMAND_NAME;
        }
    }

    static ItemStack addEnchant(ItemStack item, String name, Integer level) {
        if (hasEnchant(item, name)) removeEnchant(item, name);
        if (level < 1) return item;
        if (level > 10) level = 10;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<String>();
        if (level == 1) lore.add(0, name);
        else {
            String levelString = NUMERALS ? numberToNumeral(level) : level.toString();
            lore.add(0, name + " " + levelString);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    static ItemStack removeEnchant(ItemStack item, String name) {
        if (!hasEnchant(item, name)) return item;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) return item; // Probably redundant
        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            if (!line.contains(name)) newLore.add(line);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
    }

    static boolean hasEnchant(ItemStack item, String name) {
        return getEnchantLevel(item, name) > 0;
    }

    static int getEnchantLevel(ItemStack item, String name) {
        if (item == null) return 0;
        List<String> lore = item.getItemMeta().getLore();
        if (lore == null) return 0;
        Iterator<String> loreIter = lore.iterator();
        while (loreIter.hasNext()) {
            String line = loreIter.next();
            if (line.contains(name)) return toEnchantLevel(line.replaceAll(name, "").trim());
        }
        return 0;
    }

    static int toEnchantLevel(String levelString) {
        if (levelString.isEmpty()) return 1;
        try { return Integer.parseInt(levelString); }
        catch (Exception e) { return numeralToNumber(levelString); }
    }

    static int numeralToNumber(String numeral) {
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

    static String numberToNumeral(int number) {
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

    ArrayList<String> getEnchantCommandNames() {
        ArrayList<String> commandNames = new ArrayList<String>();
        Iterator<FishchantmentCommandData> commandIter = commands.iterator();
        while (commandIter.hasNext()) {
            commandNames.add(commandIter.next().getCommandName());
        }
        return commandNames;
    }

    String getName(String commandName) {
        Iterator<FishchantmentCommandData> commandIter = commands.iterator();
        while (commandIter.hasNext()) {
            FishchantmentCommandData commandData = commandIter.next();
            if (commandData.getCommandName().equalsIgnoreCase(commandName)) return commandData.getName();
        }
        return null;
    }
}