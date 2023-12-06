package fun.sunrisemc.fishchantments.util;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;

public class WorldUtil {

    public static void cancelKnockback(Plugin plugin, final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                entity.setVelocity(new Vector(0, 0, 0));
            }
        }, 0);
    }

    public static boolean raining(Block block) {
        return block.getWorld().hasStorm() && rains(block);
    }

    public static boolean rains(Block block) {
        double temp = block.getTemperature();
        return temp < 0.95 && temp > 0.15;
    }

    public static boolean snowing(Block block) {
        return block.getWorld().hasStorm() && snows(block);
    }

    public static boolean snows(Block block) {
        double temp = block.getTemperature();
        return temp < 0.15;
    }

    public static boolean underBlock(Player player) {
        return player.getWorld().getHighestBlockYAt(player.getLocation()) > player.getLocation().getY();
    }
}