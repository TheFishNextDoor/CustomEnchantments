package fun.sunrisemc.fishchants;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("Fishchants");

  public void onEnable()
  {
    LOGGER.info("Fishchants enabled");
  }

  public void onDisable()
  {
    LOGGER.info("Fishchants disabled");
  }
}
