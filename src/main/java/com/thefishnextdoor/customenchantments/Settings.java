package com.thefishnextdoor.customenchantments;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.customenchantments.util.NumberTools;

public class Settings {
    
    public final boolean MOBS_DROP_BOOKS;
    public final boolean REMOVE_OVERRIDDEN_ENCHANTMENTS;
    public final boolean CHECK_LORE;
    public final boolean USE_ARABIC_NUMERALS;
    public final boolean PLAY_EFFECTS;
    public final double ANVIL_COST_MULTIPLIER;
    public final int MAX_ANVIL_COST_LEVELS;
    public final int OVERRIDE_ANVIL_COST_LEVELS;
    public final int DISENCHANT_COMMAND_COST_LEVELS;
    public final int COMBINE_ENCHANTMENTS_COMMAND_COST_LEVELS;
    public final int SEEKING_ENCHANTMENT_RADIUS;

    public Settings(CustomEnchantments plugin) {
        FileConfiguration config = getPluginConfig(plugin);
        MOBS_DROP_BOOKS = config.getBoolean("mobs-drop-books", true);
        REMOVE_OVERRIDDEN_ENCHANTMENTS = config.getBoolean("remove-overridden-enchantments", true);
        CHECK_LORE = config.getBoolean("check-lore", true);
        USE_ARABIC_NUMERALS = config.getBoolean("use-arabic-numerals", false);
        PLAY_EFFECTS = config.getBoolean("play-effects", true);
        ANVIL_COST_MULTIPLIER = NumberTools.clamp(config.getDouble("anvil-cost-multiplier", 1.0), 0.0, 100.0);
        MAX_ANVIL_COST_LEVELS = NumberTools.clamp(config.getInt("max-anvil-cost-levels", -1), -1, Integer.MAX_VALUE);
        OVERRIDE_ANVIL_COST_LEVELS = NumberTools.clamp(config.getInt("override-anvil-cost-levels", -1), -1, Integer.MAX_VALUE);
        DISENCHANT_COMMAND_COST_LEVELS = NumberTools.clamp(config.getInt("disenchant-command-cost-levels", 3), 0, Integer.MAX_VALUE);
        COMBINE_ENCHANTMENTS_COMMAND_COST_LEVELS = NumberTools.clamp(config.getInt("combine-enchantments-command-cost-levels", 3), 0, Integer.MAX_VALUE);
        SEEKING_ENCHANTMENT_RADIUS = NumberTools.clamp(config.getInt("seeking-enchantment-radius", 12), 4, 64);
    }

    private static FileConfiguration getPluginConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
        return plugin.getConfig();
    }
}