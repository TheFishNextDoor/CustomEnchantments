package com.thefishnextdoor.customenchantments;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.customenchantments.commands.EnchantInfo;
import com.thefishnextdoor.customenchantments.commands.EnchantedBook;
import com.thefishnextdoor.customenchantments.commands.Fenchant;
import com.thefishnextdoor.customenchantments.commands.CombineEnchantments;
import com.thefishnextdoor.customenchantments.commands.FCE;
import com.thefishnextdoor.customenchantments.commands.Disenchant;
import com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.events.BlockBreak;
import com.thefishnextdoor.customenchantments.events.BlockDropItems;
import com.thefishnextdoor.customenchantments.events.DropItem;
import com.thefishnextdoor.customenchantments.events.EntityDamage;
import com.thefishnextdoor.customenchantments.events.EntityDamageByEntity;
import com.thefishnextdoor.customenchantments.events.EntityDeath;
import com.thefishnextdoor.customenchantments.events.HungerChange;
import com.thefishnextdoor.customenchantments.events.InventoryClick;
import com.thefishnextdoor.customenchantments.events.PlayerInteract;
import com.thefishnextdoor.customenchantments.events.PlayerItemDamage;
import com.thefishnextdoor.customenchantments.events.PlayerMove;
import com.thefishnextdoor.customenchantments.events.PlayerQuit;
import com.thefishnextdoor.customenchantments.events.PrepareAnvil;
import com.thefishnextdoor.customenchantments.events.ProjectileHit;
import com.thefishnextdoor.customenchantments.events.ProjectileLaunch;

public class Plugin extends JavaPlugin {
    
    public static final Logger LOGGER = Logger.getLogger("Fish's Custom Enchantments");

    private static Plugin instance;
    private static Settings Settings;

    public void onEnable() {
        instance = this;
        Settings = new Settings();
        CustomEnchantment.init(this);
        ArmorEffects.startTask(this);
        Seeking.startTask(this);
        registerEvents();
        registerCommands();
        forceLoadClasses();
        LOGGER.info("Plugin enabled");
    }
    
    public void onDisable() {
        ArmorEffects.stopTask();
        Seeking.stopTask();
        LOGGER.info("Plugin disabled");
    }

    public static void reload() {
        Settings = new Settings();
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static Settings getSettings() {
        return Settings;
    }

    private void registerEvents() {
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
    }

    private void registerCommands() {
        getCommand("fce").setExecutor(new FCE());
        getCommand("fenchant").setExecutor(new Fenchant());
        getCommand("enchantinfo").setExecutor(new EnchantInfo());
        getCommand("disenchant").setExecutor(new Disenchant());
        getCommand("enchantedbook").setExecutor(new EnchantedBook());
        getCommand("combineenchantments").setExecutor(new CombineEnchantments());
    }

    private void forceLoadClasses() {
        // This fixes an issue where some commands wouldn't work if you reloaded before running the command atleast once.
        try {
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            Commands.recommendedEnchantmentNames(item);
        }
        catch (Exception e) {
            LOGGER.warning("Failed to force load classes.");
        }
    }
}
