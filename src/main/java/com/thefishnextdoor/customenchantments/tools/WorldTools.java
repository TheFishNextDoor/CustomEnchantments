package com.thefishnextdoor.customenchantments.tools;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.PermChecker;
import com.thefishnextdoor.customenchantments.enchantments.Smelting;
import com.thefishnextdoor.customenchantments.enchantments.Telekinesis;

public class WorldTools {

    public static void breakBlock(Player player, Block block) {
        WorldTools.breakBlock(player, block, null);
    }

    public static void breakBlock(Player player, Block block, ItemStack item) {
        if (PermChecker.canBreak(player, block)) {
            WorldTools.dropBlockItems(player, block, item);
            block.setType(Material.AIR);
        }
    }

    public static void dropBlockItems(Player player, Block block) {
        WorldTools.dropBlockItems(player, block, null);
    }

    public static void dropBlockItems(Player player, Block block, ItemStack item) {
        Collection<ItemStack> drops = item == null ? block.getDrops() : block.getDrops(item);
        BlockState state = block.getState();
        if (state instanceof InventoryHolder && !(state instanceof ShulkerBox)) {
            ItemStack[] contents = ((InventoryHolder) state).getInventory().getContents();
            for (ItemStack content : contents) {
                if (content == null) continue;
                drops.add(content);
            }
        }

        Smelting.onBlockDropItems(player, drops);
        Telekinesis.transferDrops(player, drops);

        World world = block.getWorld();
        for (ItemStack drop : drops) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
    }
}