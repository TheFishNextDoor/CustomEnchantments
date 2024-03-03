package com.thefishnextdoor.customenchantments.enchantment;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.PermChecker;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Tilling extends CustomEnchantment {

    public Tilling(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Tilling";
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
        return MaterialTools.isHoe(item.getType());
    }

    @Override
    public String getDescription() {
        return "Hoe farmland 3x3. Rare drop from pillager.";
    }

    public static void onTill(Player player, Block block) {
        if (player == null || block == null) {
            return;
        }
        if (!EnchantTools.holdingHoeWith(player, CustomEnchantment.TILLING)) {
            return;
        }
        till(player, block);
    }

    private static void till(Player player, Block block) {
        int x = block.getX(); int y = block.getY(); int z = block.getZ();
        int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
        for (int[] coords : allCoords) {
            Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
            if (isTillable(modifiedBlock.getType()) && PermChecker.canBreak(player, modifiedBlock)) {
                modifiedBlock.setType(Material.FARMLAND);
            }
        }
    }

    private static boolean isTillable(Material material) {
        return material == Material.DIRT || material == Material.GRASS_BLOCK || material == Material.DIRT_PATH;
    }
}