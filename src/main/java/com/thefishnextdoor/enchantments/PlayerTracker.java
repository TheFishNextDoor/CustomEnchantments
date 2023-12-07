package com.thefishnextdoor.enchantments;

import java.util.ArrayList;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class PlayerTracker {
    private static ArrayList<TrackedPlayer> trackedPlayers = new ArrayList<TrackedPlayer>();

    public static class TrackedPlayer {
        private final String name;
        private BlockFace mining = null;

        public TrackedPlayer(Player player) {
            this.name = player.getName();
            trackedPlayers.add(this);
        }

        public boolean is(Player player) {
            return player.getName().equals(name);
        }

        public BlockFace getMiningFace() {
            return mining;
        }

        public void setMiningFace(BlockFace mining) {
            this.mining = mining;
        }
    }

    public static TrackedPlayer get(Player player) {
        for (TrackedPlayer trackedPlayer : trackedPlayers) {
            if (trackedPlayer.is(player)) return trackedPlayer;
        }
        return new TrackedPlayer(player);
    }

    public static void remove(Player player) {
        for (TrackedPlayer trackedPlayer : trackedPlayers) {
            if (trackedPlayer.is(player)) {
                trackedPlayers.remove(trackedPlayer);
                return;
            }
        }
    }
}
