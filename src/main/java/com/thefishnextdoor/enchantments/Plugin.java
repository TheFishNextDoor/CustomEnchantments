package com.thefishnextdoor.enchantments;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.enchantments.commands.Fenchant;
import com.thefishnextdoor.enchantments.commands.Reload;
import com.thefishnextdoor.enchantments.events.EntityDamageByEntity;
import com.thefishnextdoor.enchantments.events.BlockDropItems;
import com.thefishnextdoor.enchantments.events.BlockBreak;
import com.thefishnextdoor.enchantments.events.PlayerInteract;
import com.thefishnextdoor.enchantments.events.EntityDamage;
import com.thefishnextdoor.enchantments.events.EntityDeath;
import com.thefishnextdoor.enchantments.events.ProjectileLaunch;
import com.thefishnextdoor.enchantments.events.InventoryClick;
import com.thefishnextdoor.enchantments.events.HungerChange;
import com.thefishnextdoor.enchantments.events.PlayerItemDamage;
import com.thefishnextdoor.enchantments.events.PlayerMove;
import com.thefishnextdoor.enchantments.events.PrepareAnvil;
import com.thefishnextdoor.enchantments.events.ProjectileHit;
import com.thefishnextdoor.enchantments.events.Quit;

public class Plugin extends JavaPlugin {
    public static final Logger LOGGER = Logger.getLogger("Fish's Custom Enchantments");

    public Plugin() {
        CustomEnchantment.init(this);
    }
    
    public void onEnable() {
        Settings.loadPluginConfig(this);
        CustomEnchantment.registerAll();
        Timer.start(this);
        registerEvents();
        registerCommands();
        LOGGER.info("Plugin enabled");
    }
    
    public void onDisable() {
        Timer.stop();
        LOGGER.info("Plugin disabled");
    }

    public void reload() {
        Settings.loadPluginConfig(this);
        Timer.reload();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EntityDamage(this), this);
        pluginManager.registerEvents(new EntityDamageByEntity(this), this);
        pluginManager.registerEvents(new Quit(), this);
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
        pluginManager.registerEvents(new EntityDeath(this), this);
    }

    private void registerCommands() {
        getCommand("reloadenchantments").setExecutor(new Reload(this));
        getCommand("fenchant").setExecutor(new Fenchant());
    }
}
