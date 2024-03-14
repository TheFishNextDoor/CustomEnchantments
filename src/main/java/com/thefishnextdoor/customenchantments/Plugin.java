package com.thefishnextdoor.customenchantments;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.event.BlockBreak;
import com.thefishnextdoor.customenchantments.event.BlockDropItems;
import com.thefishnextdoor.customenchantments.event.DropItem;
import com.thefishnextdoor.customenchantments.event.EntityDamage;
import com.thefishnextdoor.customenchantments.event.EntityDamageByEntity;
import com.thefishnextdoor.customenchantments.event.EntityDeath;
import com.thefishnextdoor.customenchantments.event.HungerChange;
import com.thefishnextdoor.customenchantments.event.InventoryClick;
import com.thefishnextdoor.customenchantments.event.PlayerInteract;
import com.thefishnextdoor.customenchantments.event.PlayerItemDamage;
import com.thefishnextdoor.customenchantments.event.PlayerMove;
import com.thefishnextdoor.customenchantments.event.PlayerQuit;
import com.thefishnextdoor.customenchantments.event.PrepareAnvil;
import com.thefishnextdoor.customenchantments.event.ProjectileHit;
import com.thefishnextdoor.customenchantments.event.ProjectileLaunch;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Plugin extends JavaPlugin {
    
    public static final Logger LOGGER = Logger.getLogger("Fish's Custom Enchantments");

    private static Plugin instance;
    private static Settings settings;
    private static Commands commands;

    public void onEnable() {
        instance = this;

        settings = new Settings(this);
        commands = new Commands(this);

        CustomEnchantment.registerAll(this);

        ArmorEffects.startTask(this);
        Seeking.startTask(this);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EntityDamage(), this);
        pluginManager.registerEvents(new EntityDamageByEntity(), this);
        pluginManager.registerEvents(new PlayerQuit(), this);
        pluginManager.registerEvents(new ProjectileHit(), this);
        pluginManager.registerEvents(new BlockDropItems(), this);
        pluginManager.registerEvents(new PlayerItemDamage(), this);
        pluginManager.registerEvents(new ProjectileLaunch(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new HungerChange(), this);
        pluginManager.registerEvents(new PlayerInteract(), this);
        pluginManager.registerEvents(new BlockBreak(), this);
        pluginManager.registerEvents(new PrepareAnvil(), this);
        pluginManager.registerEvents(new InventoryClick(), this);
        pluginManager.registerEvents(new EntityDeath(), this);
        pluginManager.registerEvents(new DropItem(), this);

        forceLoadClasses();

        LOGGER.info("Plugin enabled");
    }
    
    public void onDisable() {
        ArmorEffects.stopTask();
        Seeking.stopTask();
        LOGGER.info("Plugin disabled");
    }

    public void reload() {
        settings = new Settings(this);
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static Commands getCommands() {
        return commands;
    }

    private void forceLoadClasses() {
        // This fixes an issue where some commands wouldn't work if you reloaded before running the command atleast once.
        try {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            EnchantTools.namesOfCompatibleEnchantments(item);
        }
        catch (Exception e) {
            LOGGER.warning("Failed to force load classes.");
        }
    }
}
