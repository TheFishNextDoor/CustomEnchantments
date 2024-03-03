package com.thefishnextdoor.customenchantments.enchantment;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.EndOfTick;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class Fling extends CustomEnchantment {

    public Fling(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fling";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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
        return MaterialTools.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Sends attacked entities upwards. Rare drop from creeper.";
    }

    public static void onPlayerAttackEntity(Player player, final Entity entity, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.FLING, ranged);
        if (level < 1) {
            return;
        }
        entity.teleport(entity.getLocation().add(0, 0.25, 0));
        final double y = (ranged ? 0.1 : 0.05) + (level * 0.1);
        EndOfTick.setVelocity(entity, new Vector(0, y, 0));

    }
}