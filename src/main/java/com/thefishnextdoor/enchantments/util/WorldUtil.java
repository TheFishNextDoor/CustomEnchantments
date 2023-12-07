package com.thefishnextdoor.enchantments.util;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.PermChecker;
import com.thefishnextdoor.enchantments.Plugin;
import com.thefishnextdoor.enchantments.enchantments.Telekinesis;

public class WorldUtil {

    public static void breakBlock(Player player, Block block) {
        WorldUtil.breakBlock(player, block, null);
    }

    public static void breakBlock(Player player, Block block, ItemStack item) {
        if (!PermChecker.canBreak(player, block)) return;
        WorldUtil.blockDrops(player, block, item);
        block.setType(Material.AIR);
    }

    public static void blockDrops(Player player, Block block) {
        WorldUtil.blockDrops(player, block, null);
    }

    public static void blockDrops(Player player, Block block, ItemStack item) {
        Collection<ItemStack> drops;
        if (item == null) drops = block.getDrops();
        else drops = block.getDrops(item);
        if (drops.isEmpty()) return;
        Telekinesis.onBlockDropItems(player, drops);
        if (drops.isEmpty()) return;
        World world = block.getWorld();
        for (ItemStack drop : drops) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
    }

    public static void cancelKnockback(Plugin plugin, final Entity entity) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                entity.setVelocity(new Vector(0, 0, 0));
            }
        }, 0);
    }

    public static boolean raining(Block block) {
        return block.getWorld().hasStorm() && rains(block);
    }

    public static boolean rains(Block block) {
        double temp = block.getTemperature();
        return temp < 0.95 && temp > 0.15;
    }

    public static boolean snowing(Block block) {
        return block.getWorld().hasStorm() && snows(block);
    }

    public static boolean snows(Block block) {
        double temp = block.getTemperature();
        return temp < 0.15;
    }

    public static boolean underBlock(Player player) {
        return player.getWorld().getHighestBlockYAt(player.getLocation()) > player.getLocation().getY();
    }
}