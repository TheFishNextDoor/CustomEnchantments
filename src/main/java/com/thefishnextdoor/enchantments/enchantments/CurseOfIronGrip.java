package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.MaterialUtil;

public class CurseOfIronGrip extends CustomEnchantment {

    public CurseOfIronGrip(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Curse of Iron Grip";
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
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        Material type = item.getType();
        return MaterialUtil.isWeapon(type) || MaterialUtil.isTool(type);
    }

    @Override
    public String getDescription() {
        return "Prevents the holder from dropping the item. Rare drop from witch.";
    }

    public static void modifyCancelStatus(Player player, ItemStack item, PlayerDropItemEvent event) {
        if (!EnchantUtil.has(item, CustomEnchantment.CURSE_OF_IRON_GRIP)) return;
        event.setCancelled(true);
    }
}
