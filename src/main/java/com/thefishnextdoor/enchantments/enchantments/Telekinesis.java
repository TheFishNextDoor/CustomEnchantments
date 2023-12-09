package com.thefishnextdoor.enchantments.enchantments;

import java.util.Collection;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Telekinesis extends Enchantment {

    public Telekinesis(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Telekinesis";
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
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isTool(item.getType()) || InventoryUtil.isWeapon(item.getType());
    }

    public static void onBlockDropItems(Player player, List<Item> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.TELEKINESIS)) return;
        for (Item drop : drops) {
            InventoryUtil.give(player, drop.getItemStack());
        }
        drops.clear();
    }

    public static void onBlockDropItems(Player player, Collection<ItemStack> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.TELEKINESIS)) return;
        for (ItemStack drop : drops) {
            InventoryUtil.give(player, drop);
        }
        drops.clear();
    }

    public static void onMobLoot(Player player, List<ItemStack> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.TELEKINESIS)) return;
        for (ItemStack drop : drops) {
            InventoryUtil.give(player, drop);
        }
        drops.clear();
    }

    public static void onBlockXp(Player player, BlockBreakEvent event) {
        if (!EnchantUtil.holding(player, CustomEnchantment.TELEKINESIS)) return;
        player.giveExp(event.getExpToDrop());
        event.setExpToDrop(0);
    }

    public static void onMobXp(Player player, EntityDeathEvent event) {
        if (!EnchantUtil.holding(player, CustomEnchantment.TELEKINESIS)) return;
        player.giveExp(event.getDroppedExp());
        event.setDroppedExp(0);
    }
}