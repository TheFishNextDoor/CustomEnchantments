package com.thefishnextdoor.enchantments.util;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.PermChecker;
import com.thefishnextdoor.enchantments.enchantments.Smelting;
import com.thefishnextdoor.enchantments.enchantments.Telekinesis;

public class BlockUtil {

    public static void breakBlock(Player player, Block block) {
        BlockUtil.breakBlock(player, block, null);
    }

    public static void breakBlock(Player player, Block block, ItemStack item) {
        if (!PermChecker.canBreak(player, block)) return;
        if (block.getState() instanceof InventoryHolder) return;
        BlockUtil.dropBlockItems(player, block, item);
        block.setType(Material.AIR);
    }

    public static void dropBlockItems(Player player, Block block) {
        BlockUtil.dropBlockItems(player, block, null);
    }

    public static void dropBlockItems(Player player, Block block, ItemStack item) {
        Collection<ItemStack> drops;
        if (item == null) drops = block.getDrops();
        else drops = block.getDrops(item);
        if (drops.isEmpty()) return;
        Smelting.onBlockDropItems(player, drops);
        Telekinesis.transferDrops(player, drops);
        if (drops.isEmpty()) return;
        World world = block.getWorld();
        for (ItemStack drop : drops) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
    }
}