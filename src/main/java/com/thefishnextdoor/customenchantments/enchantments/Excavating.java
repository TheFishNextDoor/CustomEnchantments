package com.thefishnextdoor.customenchantments.enchantments;

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

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.PlayerTracker;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.InventoryTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;
import com.thefishnextdoor.customenchantments.tools.WorldTools;

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
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
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
        if (item == null) {
            return false;
        }
        return MaterialTools.isTool(item.getType());
    }

    @Override
    public String getDescription() {
        return "Mine blocks in a 3x3 grid. Rare drop from charged creeper.";
    }

    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.EXCAVATING)) {
            return;
        }

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
        ItemStack item = InventoryTools.getMeleeItemInUse(player);
        while (iter.hasNext()) {
            Block iblock = iter.next();
            if ((!iblock.getDrops(item).isEmpty() || !iblock.getDrops(new ItemStack(Material.SHEARS)).isEmpty())) WorldTools.breakBlock(player, iblock, item);
        }
    }
}