package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.WorldUtil;

public class Destructive extends Enchantment {

    public Destructive(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Destructive";
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
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
        if (EnchantUtil.same(other, Enchantment.ARROW_INFINITE)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isRanged(item.getType());
    }

    public static void onProjectileHitBlock(Player player, Projectile projectile, Block block) {
        final int level = EnchantUtil.rangedLevel(player, CustomEnchantment.DESTRUCTIVE);
        if (level < 1) return;
        ItemStack usedTool = new ItemStack(new ItemStack(Material.SHEARS));
        boolean hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
        if (!hasDrops && level >= 2) {
            usedTool = new ItemStack(Material.WOODEN_PICKAXE);
            hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
        }
        if (!hasDrops && level >= 3) {
            usedTool = new ItemStack(Material.IRON_PICKAXE);
            hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
        }
        if (!hasDrops && level >= 4) {
            usedTool = new ItemStack(Material.DIAMOND_PICKAXE);
            hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
        }
        if (!hasDrops) return;
        projectile.remove();
        WorldUtil.breakBlock(player, block);
    }
}