package com.thefishnextdoor.enchantments.enchantments;

import java.util.ArrayList;

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
        if (!EnchantUtil.holding(player, CustomEnchantment.TREE_FELLER)) return;
        if (!InventoryUtil.isLog(block.getType())) return;
        TrackedPlayer trackedPlayer = PlayerTracker.get(player);
        if (!trackedPlayer.treeFellerReady()) return;
        ArrayList<Block> logs = new ArrayList<>();
        ArrayList<Block> leaves = new ArrayList<>();
        logs(block.getLocation(), logs, leaves, 25, 10);
        logs.remove(block);
        for (Block log : logs) {
            BlockUtil.breakBlock(player, log);
        }
        if (logs.size() <= 2) return;
        trackedPlayer.setTreeFellerTick();
        String msg = ChatColor.GRAY + "" + ChatColor.ITALIC + "You feel tired after chopping down a tree";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Settings.TREE_FELLER_COOLDOWN, 2), true);
    }

    private static void logs(Location loc, ArrayList<Block> logs, ArrayList<Block> leaves, int height, int radius) {
        logs(loc, logs, leaves, height, radius, 0, 0, 0, 0);
    }

    private static void logs(Location loc, ArrayList<Block> logs, ArrayList<Block> leaves, int height, int radius, int dx, int dy, int dz, int dl) {
        if (dx > radius || dx < -radius || dz > radius || dz < -radius) return;
        if (dy > height || dl > 6) return;
        Block block = loc.getBlock();
        Material type = block.getType();
        if (InventoryUtil.isLog(type)) {
            if (logs.contains(block)) return;
            logs.add(block);
            dl = 0;
            logs(loc.clone().add(0, 1, 0), logs, leaves, height, radius, dx, dy + 1, dz, dl);
        }
        else if (InventoryUtil.isLeaves(type)) {
            if (leaves.contains(block)) return;
            leaves.add(block);
            dl++;
        }
        else {
            return;
        }
        logs(loc.clone().add(1, 0, 0), logs, leaves, height, radius, dx + 1, dy, dz, dl);
        logs(loc.clone().add(-1, 0, 0), logs, leaves, height, radius, dx - 1, dy, dz, dl);
        logs(loc.clone().add(0, 0, 1), logs, leaves, height, radius, dx, dy, dz + 1, dl);
        logs(loc.clone().add(0, 0, -1), logs, leaves, height, radius, dx, dy, dz - 1, dl);
        logs(loc.clone().add(1, 0, 1), logs, leaves, height, radius, dx + 1, dy, dz + 1, dl);
        logs(loc.clone().add(1, 0, -1), logs, leaves, height, radius, dx + 1, dy, dz - 1, dl);
        logs(loc.clone().add(-1, 0, 1), logs, leaves, height, radius, dx - 1, dy, dz + 1, dl);
        logs(loc.clone().add(-1, 0, -1), logs, leaves, height, radius, dx - 1, dy, dz - 1, dl);
    }
}
