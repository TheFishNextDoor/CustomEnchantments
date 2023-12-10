package com.thefishnextdoor.enchantments;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
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
        pluginManager.registerEvents(new Damage(this), this);
        pluginManager.registerEvents(new AttackEntity(this), this);
        pluginManager.registerEvents(new Quit(), this);
        pluginManager.registerEvents(new ProjectileHit(), this);
        pluginManager.registerEvents(new BlockDropItems(), this);
        pluginManager.registerEvents(new EntityAttack(), this);
        pluginManager.registerEvents(new ItemDamage(), this);
        pluginManager.registerEvents(new FireProjectile(), this);
        pluginManager.registerEvents(new Move(), this);
        pluginManager.registerEvents(new Till(), this);
        pluginManager.registerEvents(new HungerChange(), this);
        pluginManager.registerEvents(new Fall(), this);
        pluginManager.registerEvents(new ClickBlock(), this);
        pluginManager.registerEvents(new BreakBlock(), this);
        pluginManager.registerEvents(new Suffocation(), this);
        pluginManager.registerEvents(new PrepareAnvil(), this);
        pluginManager.registerEvents(new Grindstone(), this);
        pluginManager.registerEvents(new EntityDeath(this), this);
    }

    private void registerCommands() {
        getCommand("reloadenchantments").setExecutor(new Reload(this));
        getCommand("fenchant").setExecutor(new Fenchant());
    }
}
