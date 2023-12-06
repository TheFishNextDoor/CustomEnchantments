package fun.sunrisemc.fishchantments;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class PermChecker {
    private static BlockBreakEvent checking = null;

    public static boolean canBreak(Player player, Block block) {
        BlockBreakEvent event = new BlockBreakEvent(block, player);
        checking = event;
        Bukkit.getServer().getPluginManager().callEvent(event);
        checking = null;
        return !event.isCancelled();
    }

    public static boolean isReal(BlockBreakEvent event) {
        return !checking.equals(event);
    }
}