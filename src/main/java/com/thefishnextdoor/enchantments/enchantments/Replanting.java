package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.PermChecker;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.BlockUtil;

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
        return InventoryUtil.isHoe(item.getType());
    }

    public static void onRightClick(Player player, Block block) {
        final int level = EnchantUtil.hoeLevel(player, CustomEnchantment.REPLANTING);
        if (level < 1) return;
        ItemStack item = InventoryUtil.getHoeInUse(player);
        if (item == null) return;
        if (level == 1) {
            if (PermChecker.canBreak(player, block)) harvest(player, block, item);
        }
        else {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (PermChecker.canBreak(player, modifiedBlock)) harvest(player, modifiedBlock, item);
            }
        }
    }

    public static void onBlockBreak(Player player, Block block, BlockBreakEvent event) {
        final int level = EnchantUtil.meleeLevel(player, CustomEnchantment.REPLANTING);
        if (level < 1) return;
        ItemStack item = InventoryUtil.getMeleeItemInUse(player);
        event.setCancelled(harvest(player, block, item));
    }

    private static boolean harvest(Player player, Block block, ItemStack item) {
        BlockState state = block.getState();
        if (!(state.getBlockData() instanceof Ageable)) return false;
        Ageable ageable = (Ageable) state.getBlockData();
        if (ageable.getAge() != ageable.getMaximumAge()) return false;
        BlockUtil.dropBlockItems(player, block, item);
        ageable.setAge(0);
        block.setBlockData(ageable);
        return true;
    }
}