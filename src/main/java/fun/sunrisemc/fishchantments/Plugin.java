package fun.sunrisemc.fishchantments;

import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.commands.Fenchant;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Telekinesis;
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
        getServer().getPluginManager().registerEvents(new ProjectileHit(this), this);
        getServer().getPluginManager().registerEvents(new AttackEntity(this), this);
        getServer().getPluginManager().registerEvents(new BlockDropItems(this), this);
        getServer().getPluginManager().registerEvents(new Damage(this), this);
        getServer().getPluginManager().registerEvents(new EntityAttack(this), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(this), this);
        getServer().getPluginManager().registerEvents(new FireProjectile(this), this);
        getServer().getPluginManager().registerEvents(new Move(this), this);
        getServer().getPluginManager().registerEvents(new Till(this), this);
        getServer().getPluginManager().registerEvents(new HungerChange(this), this);
        getServer().getPluginManager().registerEvents(new Fall(this), this);
        getServer().getPluginManager().registerEvents(new ClickBlock(this), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(this), this);
        getServer().getPluginManager().registerEvents(new Suffocation(this), this);
        getServer().getPluginManager().registerEvents(new PrepareAnvil(), this);
        getServer().getPluginManager().registerEvents(new Grindstone(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
        getCommand("fenchant").setExecutor(new Fenchant());
        LOGGER.info("Plugin enabled");
    }
    
    public void onDisable() {
        LOGGER.info("Plugin disabled");
    }
    
    public void breakBlock(Player player, Block block) {
        breakBlock(player, block, null);
    }
    
    public void breakBlock(Player player, Block block, ItemStack item) {
        if (!PermChecker.canBreak(player, block)) return;
        blockDrops(player, block, item);
        block.setType(Material.AIR);
    }

    public void blockDrops(Player player, Block block) {
        blockDrops(player, block, null);
    }
    
    public void blockDrops(Player player, Block block, ItemStack item) {
        Collection<ItemStack> drops;
        if (item == null) drops = block.getDrops();
        else drops = block.getDrops(item);
        if (drops.isEmpty()) return;
        Telekinesis.onBlockDropItems(this, player, drops);
        if (drops.isEmpty()) return;
        World world = block.getWorld();
        for (ItemStack drop : drops) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
    }
}
