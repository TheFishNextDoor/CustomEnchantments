package fun.sunrisemc.fishchants;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
  private static final Logger LOGGER=Logger.getLogger("Fishchants");
  private CommandHandler commandHandler;
  private Enchant enchantManager;

  public void onEnable() {
    getServer().getPluginManager().registerEvents(new EventListener(), this);
    commandHandler = new CommandHandler(this);
    getCommand("fenchant").setExecutor(commandHandler);
    enchantManager = new Enchant();
    enchantManager.registerEnchants();
    LOGGER.info("Fishchants enabled");
  }

  public void onDisable() {
    LOGGER.info("Fishchants disabled");
  }

  Enchant getEnchantManager() {
    return enchantManager;
  }
}
