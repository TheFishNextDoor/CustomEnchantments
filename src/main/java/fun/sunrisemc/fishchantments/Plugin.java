package fun.sunrisemc.fishchantments;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.commands.Fenchant;
import fun.sunrisemc.fishchantments.events.ProjectileHit;
import fun.sunrisemc.fishchantments.events.Quit;
import fun.sunrisemc.fishchantments.events.ItemDamage;
import fun.sunrisemc.fishchantments.events.Fall;
import fun.sunrisemc.fishchantments.events.AttackEntity;
import fun.sunrisemc.fishchantments.events.BlockDropItems;
import fun.sunrisemc.fishchantments.events.BreakBlock;
import fun.sunrisemc.fishchantments.events.ClickBlock;
import fun.sunrisemc.fishchantments.events.Damage;
import fun.sunrisemc.fishchantments.events.EntityAttack;
import fun.sunrisemc.fishchantments.events.FireProjectile;
import fun.sunrisemc.fishchantments.events.Move;
import fun.sunrisemc.fishchantments.events.Grindstone;
import fun.sunrisemc.fishchantments.events.HungerChange;
import fun.sunrisemc.fishchantments.events.EntityDeath;
import fun.sunrisemc.fishchantments.events.PrepareAnvil;
import fun.sunrisemc.fishchantments.events.Suffocation;
import fun.sunrisemc.fishchantments.events.Till;

public class Plugin extends JavaPlugin {
    public static final Logger LOGGER = Logger.getLogger("Fish's Custom Enchantments");

    public Plugin() {
        CustomEnchantment.init(this);
    }
    
    public void onEnable() {
        CustomEnchantment.registerAll();
        Timer.start(this);
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
        getServer().getPluginManager().registerEvents(new Damage(this), this);
        getServer().getPluginManager().registerEvents(new AttackEntity(this), this);
        getCommand("fenchant").setExecutor(new Fenchant());
        LOGGER.info("Plugin enabled");
    }
    
    public void onDisable() {
        LOGGER.info("Plugin disabled");
    }
}
