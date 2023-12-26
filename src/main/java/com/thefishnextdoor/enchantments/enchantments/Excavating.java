package com.thefishnextdoor.enchantments.enchantments;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.PlayerTracker;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.BlockUtil;

public class Excavating extends CustomEnchantment {

    public Excavating(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Excavating";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isTool(item.getType());
    }

    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        if (!EnchantUtil.holding(player, CustomEnchantment.EXCAVATING)) return;
        BlockFace face = PlayerTracker.get(player).getMiningFace();
        ArrayList<Block> blocks = new ArrayList<>();
        if (face == BlockFace.EAST || face == BlockFace.WEST) { // Looking along x axis
            blocks.add(block.getRelative(0, 0, 1));
            blocks.add(block.getRelative(0, 0, -1));
            blocks.add(block.getRelative(0, 1, 0));
            blocks.add(block.getRelative(0, -1, 0));
            blocks.add(block.getRelative(0, 1, 1));
            blocks.add(block.getRelative(0, 1, -1));
            blocks.add(block.getRelative(0, -1, 1));
            blocks.add(block.getRelative(0, -1, -1));
        }
        else if (face == BlockFace.NORTH || face == BlockFace.SOUTH) {// Looking along z axis
            blocks.add(block.getRelative(0, 1, 0));
            blocks.add(block.getRelative(0, -1, 0));
            blocks.add(block.getRelative(1, 0, 0));
            blocks.add(block.getRelative(-1, 0, 0));
            blocks.add(block.getRelative(1, 1, 0));
            blocks.add(block.getRelative(1, -1, 0));
            blocks.add(block.getRelative(-1, 1, 0));
            blocks.add(block.getRelative(-1, -1, 0));
        }
        else if (face == BlockFace.UP || face == BlockFace.DOWN) { // Looking along Y axis
            blocks.add(block.getRelative(0, 0, 1));
            blocks.add(block.getRelative(0, 0, -1));
            blocks.add(block.getRelative(1, 0, 0));
            blocks.add(block.getRelative(-1, 0, 0));
            blocks.add(block.getRelative(1, 0, 1));
            blocks.add(block.getRelative(1, 0, -1));
            blocks.add(block.getRelative(-1, 0, 1));
            blocks.add(block.getRelative(-1, 0, -1));
        }
        Iterator<Block> iter = blocks.iterator();
        ItemStack item = InventoryUtil.getMeleeItemInUse(player);
        while (iter.hasNext()) {
            Block iblock = iter.next();
            if ((!iblock.getDrops(item).isEmpty() || !iblock.getDrops(new ItemStack(Material.SHEARS)).isEmpty())) BlockUtil.breakBlock(player, iblock, item);
        }
    }
}