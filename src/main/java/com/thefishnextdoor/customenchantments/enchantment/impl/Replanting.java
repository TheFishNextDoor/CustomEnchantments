package com.thefishnextdoor.customenchantments.enchantment.impl;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.InventoryTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;
import com.thefishnextdoor.customenchantments.util.PermChecker;
import com.thefishnextdoor.customenchantments.util.WorldTools;

public class Replanting extends CustomEnchantment {

    @Override
    public String getName() {
        return "Replanting";
    }

    @Override
    public String getDescription() {
        return "Replants anything harvested automatically. Rare drop from pillager.";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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
        return MaterialTools.isHoe(item.getType());
    }

    public static void onRightClick(Player player, Block block) {
        final int level = EnchantTools.hoeLevel(player, CustomEnchantment.REPLANTING);
        if (level < 1) {
            return;
        }

        ItemStack item = InventoryTools.getHoeInUse(player);
        if (item == null) {
            return;
        }

        if (level == 1) {
            harvest(player, block, item);
        }
        else {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                harvest(player, modifiedBlock, item);
            }
        }
    }

    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        final int level = EnchantTools.meleeLevel(player, CustomEnchantment.REPLANTING);
        if (level < 1) {
            return;
        }
        ItemStack item = InventoryTools.getMeleeItemInUse(player);
        if (harvest(player, block, item)) {
            event.setCancelled(true);
        }
    }

    private static boolean harvest(Player player, Block block, ItemStack item) {
        BlockState state = block.getState();
        if (!(state.getBlockData() instanceof Ageable)) {
            return false;
        }

        Ageable ageable = (Ageable) state.getBlockData();
        if (ageable.getAge() != ageable.getMaximumAge()) {
            return false;
        }

        if (!PermChecker.canBreak(player, block)) {
            return false;
        }
        
        WorldTools.dropBlockItems(player, block, item);
        ageable.setAge(0);
        block.setBlockData(ageable);
        return true;
    }
}