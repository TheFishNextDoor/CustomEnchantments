package com.thefishnextdoor.customenchantments.enchantment.impl;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.PlayerTracker;
import com.thefishnextdoor.customenchantments.PlayerTracker.TrackedPlayer;
import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;
import com.thefishnextdoor.customenchantments.util.WorldTools;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TreeFeller extends CustomEnchantment {

    public static final int RADIUS = 6;
    public static final int HEIGHT = 32;
    public static final int DELAY_TICKS = 50;

    @Override
    public String getName() {
        return "Tree Feller";
    }

    @Override
    public String getDescription() {
        return "Breaks the entire trunk of the tree. Rare drop from pillager.";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return EnchantTools.same(other, CustomEnchantment.EXCAVATING);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isAxe(item.getType());
    }

    @SuppressWarnings("deprecation")
    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.TREE_FELLER)) {
            return;
        }

        if (!TreeFeller.isLog(block.getType())) {
            return;
        }

        TrackedPlayer trackedPlayer = PlayerTracker.get(player);
        if (!trackedPlayer.treeFellerReady()) {
            return;
        }

        ArrayList<Block> logs = new ArrayList<>();
        ArrayList<Block> leaves = new ArrayList<>();
        selectTree(block.getLocation(), logs, leaves);
        
        if (logs.size() <= 3 || leaves.size() <= 15) {
            return;
        }

        trackedPlayer.setTreeFellerTick();
        logs.remove(block);
        for (Block log : logs) {
            WorldTools.breakBlock(player, log);
        }

        String msg = ChatColor.GRAY + "" + ChatColor.ITALIC + "You feel tired after chopping down a tree";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, DELAY_TICKS, 2), true);
    }

    private static void selectTree(Location start, ArrayList<Block> logs, ArrayList<Block> leaves) {
        Material type = start.getBlock().getType();
        if (TreeFeller.isLog(type)) {
            selectTree(start, start, type, logs, leaves, new HashSet<Location>(), 0);
        }
    }

    private static void selectTree(Location start, Location current, Material logType, ArrayList<Block> logs, ArrayList<Block> leaves, HashSet<Location> checked, int outOfTree) {
        if (checked.contains(current)) {
            return;
        }
        checked.add(current);

        if (Math.abs(current.getY() - start.getY()) > HEIGHT) {
            return;
        }
        if (Math.abs(current.getX() - start.getX()) > RADIUS) {
            return;
        }
        if (Math.abs(current.getZ() - start.getZ()) > RADIUS) {
            return;
        }

        Block block = current.getBlock();
        Material type = block.getType();
        boolean log = type == logType;
        boolean leaf = TreeFeller.isLeaves(type);

        if (log) {
            logs.add(block);
        }
        else if (leaf) {
            leaves.add(block);
        }

        if (log || leaf) {
            outOfTree = 0;
        }
        else {
            outOfTree++;
        }

        if (outOfTree > 1) {
            return;
        }

        selectTree(start, current.clone().add(1, 0, 0), logType, logs, leaves, checked, outOfTree);
        selectTree(start, current.clone().add(-1, 0, 0), logType, logs, leaves, checked, outOfTree);
        selectTree(start, current.clone().add(0, 0, 1), logType, logs,leaves, checked, outOfTree);
        selectTree(start, current.clone().add(0, 0, -1), logType, logs, leaves, checked, outOfTree);
        selectTree(start ,current.clone().add(0, 1, 0), logType, logs, leaves, checked, outOfTree);
        selectTree(start, current.clone().add(0, -1, 0), logType, logs, leaves, checked, outOfTree);
    }

    private static boolean isLog(Material type) {
        switch (type) {
            case OAK_LOG:
            case OAK_WOOD:
            case BIRCH_LOG:
            case BIRCH_WOOD:
            case SPRUCE_LOG:
            case SPRUCE_WOOD:
            case JUNGLE_LOG:
            case JUNGLE_WOOD:
            case ACACIA_LOG:
            case ACACIA_WOOD:
            case DARK_OAK_LOG:
            case DARK_OAK_WOOD:
            case MANGROVE_LOG:
            case MANGROVE_WOOD:
            case CHERRY_LOG:
            case CHERRY_WOOD:
                return true;
            default:
                return false;
        }
    }

    private static boolean isLeaves(Material type) {
        switch (type) {
            case OAK_LEAVES:
            case BIRCH_LEAVES:
            case SPRUCE_LEAVES:
            case JUNGLE_LEAVES:
            case ACACIA_LEAVES:
            case DARK_OAK_LEAVES:
            case MANGROVE_LEAVES:
            case CHERRY_LEAVES:
                return true;
            default:
                return false;
        }
    }
}
