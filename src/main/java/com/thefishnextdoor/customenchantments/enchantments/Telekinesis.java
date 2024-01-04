package com.thefishnextdoor.customenchantments.enchantments;

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

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.InventoryTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;

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
        if (item == null) {
            return false;
        }
        return MaterialTools.isTool(item.getType()) || MaterialTools.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Dropped items are added to your inventory. Rare drop from enderman.";
    }

    public static void transferDrops(Player player, List<Item> drops) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) {
            return;
        }
        for (Item drop : drops) {
            InventoryTools.give(player, drop.getItemStack());
        }
        drops.clear();
    }

    public static void transferDrops(Player player, Collection<ItemStack> drops) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) {
            return;
        }
        for (ItemStack drop : drops) {
            InventoryTools.give(player, drop);
        }
        drops.clear();
    }

    public static void transferXp(Player player, BlockBreakEvent event) {
        if (EnchantTools.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) {
            player.giveExp(event.getExpToDrop());
            event.setExpToDrop(0);
        }
    }

    public static void transferXp(Player player, EntityDeathEvent event) {
        if (EnchantTools.holdingMeleeWith(player, CustomEnchantment.TELEKINESIS)) {
            player.giveExp(event.getDroppedExp());
            event.setDroppedExp(0);
        }
    }
}