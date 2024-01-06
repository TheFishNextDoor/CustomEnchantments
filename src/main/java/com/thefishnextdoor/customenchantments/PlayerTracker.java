package com.thefishnextdoor.customenchantments;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class PlayerTracker {

    private static ArrayList<TrackedPlayer> trackedPlayers = new ArrayList<TrackedPlayer>();

    public static class TrackedPlayer {

        private final String id;
        
        private BlockFace miningFace = null;
        private long lastTreeFellerTick = 0;

        public TrackedPlayer(Player player) {
            this.id = id(player);
            trackedPlayers.add(this);
        }

        public boolean is(Player player) {
            return id(player).equals(id);
        }

        private static String id(Player player) {
            return player.getName();
        }

        public BlockFace getMiningFace() {
            return miningFace;
        }

        public void setMiningFace(BlockFace mining) {
            this.miningFace = mining;
        }

        public boolean treeFellerReady() {
            return currentTimeTicks() >= lastTreeFellerTick + Settings.TREE_FELLER_COOLDOWN - 2;
        }

        public void setTreeFellerTick() {
            this.lastTreeFellerTick = currentTimeTicks();
        }

        private static long currentTimeTicks() {
            return System.currentTimeMillis() / 50;
        }

    }

    public static TrackedPlayer get(Player player) {
        for (TrackedPlayer trackedPlayer : trackedPlayers) {
            if (trackedPlayer.is(player)) {
                return trackedPlayer;
            }
        }
        return new TrackedPlayer(player);
    }

    public static void remove(Player player) {
        Iterator<TrackedPlayer> iterator = trackedPlayers.iterator();
        while (iterator.hasNext()) {
            TrackedPlayer trackedPlayer = iterator.next();
            if (trackedPlayer.is(player)) {
                iterator.remove();
                return;
            }
        }
    }
}
