package fun.sunrisemc.fishchantments.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fun.sunrisemc.fishchantments.PlayerTracker;
import fun.sunrisemc.fishchantments.enchantment_definitions.Generic.Replanting;

public class ClickBlock implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_BLOCK) leftClick(event);
        else if (action == Action.RIGHT_CLICK_BLOCK) rightClick(event);
    }

    private void leftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        PlayerTracker.get(player).setMiningFace(event.getBlockFace());
    }

    private void rightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        Replanting.onRightClick(player, clickedBlock);
    }
}
