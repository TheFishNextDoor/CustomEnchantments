package com.thefishnextdoor.enchantments;

import org.bukkit.configuration.file.FileConfiguration;

import com.thefishnextdoor.enchantments.util.NumberUtil;

public class Settings {
    public static boolean USE_ARABIC_NUMERALS = false;
    public static boolean REMOVE_OVERRIDDEN_ENCHANTMENTS = true;
    public static boolean CHECK_LORE = true;
    public static boolean MOBS_DROP_BOOKS = true;
    public static int ARMOR_EFFECTS_PERIOD_TICKS = 20;

    public static void load(FileConfiguration config) {
        USE_ARABIC_NUMERALS = config.getBoolean("use-arabic-numerals", USE_ARABIC_NUMERALS);
        REMOVE_OVERRIDDEN_ENCHANTMENTS = config.getBoolean("remove-overridden-enchantments", REMOVE_OVERRIDDEN_ENCHANTMENTS);
        CHECK_LORE = config.getBoolean("check-lore", CHECK_LORE);
        MOBS_DROP_BOOKS = config.getBoolean("mobs-drop-books", MOBS_DROP_BOOKS);
        ARMOR_EFFECTS_PERIOD_TICKS = NumberUtil.clamp(config.getInt("armor-effects-period-ticks", ARMOR_EFFECTS_PERIOD_TICKS), 5, 160);
    }
}
