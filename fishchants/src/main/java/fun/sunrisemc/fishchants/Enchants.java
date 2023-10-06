package fun.sunrisemc.fishchants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchants {

    public static class GrassSeeds {

        final static String NAME = "Grass Seeds";

        public static void onArrowHitBlock(Player player, Arrow arrow, ItemStack bow, Block block) {
            if (hasEnchant(bow, NAME)) {
                player.sendMessage(NAME);
                block.setType(Material.GLOWSTONE);
            }
        }
    }

    public static ItemStack addEnchant(ItemStack item, String name, Integer level, boolean numerals) {
        if (level < 1 || level > 10) return item;
        if (hasEnchant(item, name)) {
            if (getEnchantLevel(item, name) < level) removeEnchant(item, name);
            else return item; 
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (level == 1) lore.add(name);
        else {
            String levelString = numerals ? "X" : level.toString();
            lore.add(name + " " + levelString);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack removeEnchant(ItemStack item, String name) {
        if (!hasEnchant(item, name)) return item;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            if (!line.contains(name)) {
                newLore.add(line);
            }
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean hasEnchant(ItemStack item, String name) {
        return getEnchantLevel(item, name) > 0;
    }

    public static int getEnchantLevel(ItemStack item, String name) {
        Iterator<String> lore = item.getItemMeta().getLore().iterator();
        while (lore.hasNext()) {
            String line = lore.next();
            if (line.contains(name)) return toEnchantLevel(line.replaceAll(name, "").trim());
        }
        return 0;
    }

    public static int toEnchantLevel(String levelString) {
        if (levelString.isEmpty()) return 1;
        try { return Integer.parseInt(levelString); }
        catch (Exception e) { return numeralToNumber(levelString); }
    }

    public static int numeralToNumber(String numeral) {
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

    public static String numberToNumeral(int number) {
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