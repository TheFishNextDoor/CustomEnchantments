package com.thefishnextdoor.customenchantments.enchantments;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantUtil;
import com.thefishnextdoor.customenchantments.util.MaterialUtil;

public class Telekinesis extends CustomEnchantment {

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
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
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
        return MaterialUtil.isTool(item.getType()) || MaterialUtil.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Dropped items are added to your inventory. Rare drop from enderman.";
    }

    public static void transferDrops(Player player, List<Item> drops) {
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) return;
        for (Item drop : drops) {
            give(player, drop.getItemStack());
        }
        drops.clear();
    }

    public static void transferDrops(Player player, Collection<ItemStack> drops) {
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) return;
        for (ItemStack drop : drops) {
            give(player, drop);
        }
        drops.clear();
    }

    public static void transferXp(Player player, BlockBreakEvent event) {
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) return;
        player.giveExp(event.getExpToDrop());
        event.setExpToDrop(0);
    }

    public static void transferXp(Player player, EntityDeathEvent event) {
        if (!EnchantUtil.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) return;
        player.giveExp(event.getDroppedExp());
        event.setDroppedExp(0);
    }

    private static void give(Player player, ItemStack item) {
        HashMap<Integer, ItemStack> excessItems = player.getInventory().addItem(item);
        if (excessItems.isEmpty()) return;
        Iterator<ItemStack> iter = excessItems.values().iterator();
        while (iter.hasNext()) {
            player.getWorld().dropItem(player.getLocation(), iter.next());
        }
    }
}