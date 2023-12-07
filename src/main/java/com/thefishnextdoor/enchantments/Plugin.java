package com.thefishnextdoor.enchantments;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.enchantments.commands.Fenchant;
import com.thefishnextdoor.enchantments.commands.Reload;
import com.thefishnextdoor.enchantments.events.AttackEntity;
import com.thefishnextdoor.enchantments.events.BlockDropItems;
import com.thefishnextdoor.enchantments.events.BreakBlock;
import com.thefishnextdoor.enchantments.events.ClickBlock;
import com.thefishnextdoor.enchantments.events.Damage;
import com.thefishnextdoor.enchantments.events.EntityAttack;
import com.thefishnextdoor.enchantments.events.EntityDeath;
import com.thefishnextdoor.enchantments.events.Fall;
import com.thefishnextdoor.enchantments.events.FireProjectile;
import com.thefishnextdoor.enchantments.events.Grindstone;
import com.thefishnextdoor.enchantments.events.HungerChange;
import com.thefishnextdoor.enchantments.events.ItemDamage;
import com.thefishnextdoor.enchantments.events.Move;
import com.thefishnextdoor.enchantments.events.PrepareAnvil;
import com.thefishnextdoor.enchantments.events.ProjectileHit;
import com.thefishnextdoor.enchantments.events.Quit;
import com.thefishnextdoor.enchantments.events.Suffocation;
import com.thefishnextdoor.enchantments.events.Till;

public class Plugin extends JavaPlugin {
    public static final Logger LOGGER = Logger.getLogger("Fish's Custom Enchantments");

    public Plugin() {
        CustomEnchantment.init(this);
    }
    
    public void onEnable() {
        loadConfig();
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
        reloadConfig();
        loadConfig();
        Timer.reload();
    }

    private void loadConfig() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        Settings.load(getConfig());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new Damage(this), this);
        getServer().getPluginManager().registerEvents(new AttackEntity(this), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new ProjectileHit(), this);
        getServer().getPluginManager().registerEvents(new BlockDropItems(), this);
        getServer().getPluginManager().registerEvents(new EntityAttack(), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(), this);
        getServer().getPluginManager().registerEvents(new FireProjectile(), this);
        getServer().getPluginManager().registerEvents(new Move(), this);
        getServer().getPluginManager().registerEvents(new Till(), this);
        getServer().getPluginManager().registerEvents(new HungerChange(), this);
        getServer().getPluginManager().registerEvents(new Fall(), this);
        getServer().getPluginManager().registerEvents(new ClickBlock(), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new Suffocation(), this);
        getServer().getPluginManager().registerEvents(new PrepareAnvil(), this);
        getServer().getPluginManager().registerEvents(new Grindstone(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
    }

    private void registerCommands() {
        getCommand("reloadenchantments").setExecutor(new Reload(this));
        getCommand("fenchant").setExecutor(new Fenchant());
    }
}
