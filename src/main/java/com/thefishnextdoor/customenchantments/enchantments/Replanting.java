package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.PermChecker;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.InventoryTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;
import com.thefishnextdoor.customenchantments.tools.WorldTools;

public class Replanting extends CustomEnchantment {

    public Replanting(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Replanting";
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

    @Override
    public String getDescription() {
        return "Replants anything harvested automatically. Rare drop from pillager.";
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
            if (isHarvestable(block) && PermChecker.canBreak(player, block)) {
                harvest(player, block, item);
            }
        }
        else {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (isHarvestable(modifiedBlock) && PermChecker.canBreak(player, modifiedBlock)) {
                    harvest(player, modifiedBlock, item);
                }
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
        
        WorldTools.dropBlockItems(player, block, item);
        ageable.setAge(0);
        block.setBlockData(ageable);
        return true;
    }

    private static boolean isHarvestable(Block block) {
        BlockState state = block.getState();
        if (!(state.getBlockData() instanceof Ageable)) {
            return false;
        }

        Ageable ageable = (Ageable) state.getBlockData();
        if (ageable.getAge() != ageable.getMaximumAge()) {
            return false;
        }

        return true;
    }
}