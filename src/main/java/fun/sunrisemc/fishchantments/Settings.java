package fun.sunrisemc.fishchantments;

import org.bukkit.configuration.file.FileConfiguration;

import fun.sunrisemc.fishchantments.util.NumberUtil;

public class Settings {
    public static boolean USE_NUMBERS = false;
    public static boolean REMOVE_OVERRIDDEN_ENCHANTMENTS = true;
    public static boolean CHECK_LORE = true;
    public static int ARMOR_EFFECTS_PERIOD_TICKS = 20;

    public static void load(FileConfiguration config) {
        USE_NUMBERS = config.getBoolean("use-numbers", USE_NUMBERS);
        REMOVE_OVERRIDDEN_ENCHANTMENTS = config.getBoolean("remove-overridden-enchantments", REMOVE_OVERRIDDEN_ENCHANTMENTS);
        CHECK_LORE = config.getBoolean("check-lore", CHECK_LORE);
        ARMOR_EFFECTS_PERIOD_TICKS = NumberUtil.clamp(config.getInt("armor-effects-period-ticks", ARMOR_EFFECTS_PERIOD_TICKS), 5, 160);
    }
}
