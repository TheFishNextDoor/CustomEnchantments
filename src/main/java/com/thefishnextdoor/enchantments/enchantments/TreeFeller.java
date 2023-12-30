package com.thefishnextdoor.enchantments.enchantments;

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

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.PlayerTracker;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.PlayerTracker.TrackedPlayer;
import com.thefishnextdoor.enchantments.util.BlockUtil;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TreeFeller extends CustomEnchantment {

    private static final int RADIUS = 8;
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
        return EnchantUtil.same(other, CustomEnchantment.EXCAVATING);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isAxe(item.getType());
    }
    
    @Override
    public String getDescription() {
        return "Breaks the entire trunk of the tree. Rare drop from pillager.";
    }

    @SuppressWarnings("deprecation")
    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.TREE_FELLER)) return;
        if (!InventoryUtil.isLog(block.getType())) return;

        TrackedPlayer trackedPlayer = PlayerTracker.get(player);
        if (!trackedPlayer.treeFellerReady()) return;

        ArrayList<Block> logs = new ArrayList<>();
        ArrayList<Block> leaves = new ArrayList<>();
        logs(block.getLocation(), logs, leaves);
        
        if (logs.size() <= 3 || leaves.size() <= 15) return;

        trackedPlayer.setTreeFellerTick();
        for (Block log : logs) {
            BlockUtil.breakBlock(player, log);
        }

        String msg = ChatColor.GRAY + "" + ChatColor.ITALIC + "You feel tired after chopping down a tree";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Settings.TREE_FELLER_COOLDOWN, 2), true);
    }

    private static void logs(Location start, ArrayList<Block> logs, ArrayList<Block> leaves) {
        Material type = start.getBlock().getType();
        if (!InventoryUtil.isLog(type)) return;
        logs(start, start, type, logs, leaves,  new HashSet<Location>(), 0);
    }

    private static void logs(Location start, Location current, Material logType, ArrayList<Block> logs, ArrayList<Block> leaves, HashSet<Location> checked, int outOfTree) {
        if (checked.contains(current)) return;
        checked.add(current);

        if (Math.abs(current.getY() - start.getY()) > HEIGHT) return;
        if (Math.abs(current.getX() - start.getX()) > RADIUS) return;
        if (Math.abs(current.getZ() - start.getZ()) > RADIUS) return;

        Block block = current.getBlock();
        Material type = block.getType();
        boolean log = type == logType;
        boolean leaf = InventoryUtil.isLeaves(type);

        if (log && !current.equals(start)) logs.add(block);
        else if (leaf) leaves.add(block);

        if (log || leaf) outOfTree = 0;
        else if (outOfTree > 1) return;
        else outOfTree++;

        logs(start, current.clone().add(1, 0, 0), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(-1, 0, 0), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(0, 0, 1), logType, logs,leaves, checked, outOfTree);
        logs(start, current.clone().add(0, 0, -1), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(1, 0, 1), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(1, 0, -1), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(-1, 0, 1), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(-1, 0, -1), logType, logs, leaves, checked, outOfTree);
        logs(start ,current.clone().add(0, 1, 0), logType, logs, leaves, checked, outOfTree);
        logs(start, current.clone().add(0, -1, 0), logType, logs, leaves, checked, outOfTree);
    }
}
