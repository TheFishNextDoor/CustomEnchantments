package com.thefishnextdoor.enchantments;

import java.util.ArrayList;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class PlayerTracker {
    private static ArrayList<TrackedPlayer> trackedPlayers = new ArrayList<TrackedPlayer>();

    public static class TrackedPlayer {
        private final String id;
        private BlockFace miningFace = null;

        public TrackedPlayer(Player player) {
            this.id = id(player);
            trackedPlayers.add(this);
        }

        public boolean is(Player player) {
            return id(player).equals(id);
        }

        public BlockFace getMiningFace() {
            return miningFace;
        }

        public void setMiningFace(BlockFace mining) {
            this.miningFace = mining;
        }

        private static String id(Player player) {
            return player.getName();
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
