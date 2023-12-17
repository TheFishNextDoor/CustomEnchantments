package com.thefishnextdoor.enchantments;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Settings {
    public static boolean MOBS_DROP_BOOKS = true;
    public static boolean REMOVE_OVERRIDDEN_ENCHANTMENTS = true;
    public static boolean CHECK_LORE = true;
    public static boolean USE_ARABIC_NUMERALS = false;
    
    public static void load(FileConfiguration config) {
        MOBS_DROP_BOOKS = config.getBoolean("mobs-drop-books", MOBS_DROP_BOOKS);
        REMOVE_OVERRIDDEN_ENCHANTMENTS = config.getBoolean("remove-overridden-enchantments", REMOVE_OVERRIDDEN_ENCHANTMENTS);
        CHECK_LORE = config.getBoolean("check-lore", CHECK_LORE);
        USE_ARABIC_NUMERALS = config.getBoolean("use-arabic-numerals", USE_ARABIC_NUMERALS);
    }

    public static void loadPluginConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        load(plugin.getConfig());
    }
}
