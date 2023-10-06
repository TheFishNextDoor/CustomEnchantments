package fun.sunrisemc.fishchants;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

public class EventListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            ProjectileSource shooter = arrow.getShooter();
            if (!(shooter instanceof Player)) return;
            Player player = (Player) shooter;
            Enchants.GrassSeeds.onArrowHitBlock(player, arrow, player.getItemInHand(), event.getHitBlock());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.getPlayer().sendMessage("Hi"); // Debug
    }
}
