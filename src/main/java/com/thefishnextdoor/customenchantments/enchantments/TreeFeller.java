package com.thefishnextdoor.customenchantments.enchantments;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.PlayerTracker;
import com.thefishnextdoor.customenchantments.PlayerTracker.TrackedPlayer;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;
import com.thefishnextdoor.customenchantments.tools.WorldTools;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TreeFeller extends CustomEnchantment {

    private static final int RADIUS = 6;
    private static final int HEIGHT = 32;

    public TreeFeller(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Tree Feller";
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
    
    @Override
    public String getDescription() {
        return "Breaks the entire trunk of the tree. Rare drop from pillager.";
    }

    @SuppressWarnings("deprecation")
    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.TREE_FELLER)) {
            return;
        }

        if (!MaterialTools.isLog(block.getType())) {
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 2), true);
    }

    private static void selectTree(Location start, ArrayList<Block> logs, ArrayList<Block> leaves) {
        Material type = start.getBlock().getType();
        if (MaterialTools.isLog(type)) {
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
        boolean leaf = MaterialTools.isLeaves(type);

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
}
