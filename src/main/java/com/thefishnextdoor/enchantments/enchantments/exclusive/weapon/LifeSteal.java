package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class LifeSteal extends Enchantment {

    public LifeSteal(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Life Steal";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        return CustomEnchantment.isMutuallyExclusiveWeapon(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isWeapon(item.getType());
    }

    public static void onPlayerAttackEntity(Player player, EntityDamageByEntityEvent event, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.LIFE_STEAL, ranged);
        if (level < 1) return;
        double damage = event.getFinalDamage();
        if (!ranged) damage /= 2;
        heal(player, calcAddedHealth(damage, level));
    }

    private static double calcAddedHealth(double damage, int level) {
        if (level == 1) return damage/6;
        else if (level == 2) return damage/5;
        else if (level == 3) return damage/4;
        else if (level == 4) return damage/3;
        else if (level == 5) return damage/2;
        else if (level == 6) return damage/1.75;
        else if (level == 7) return damage/1.5;
        else if (level == 8) return damage/1.25;
        else if (level == 9) return damage;
        else if (level >= 10) return damage*1.25;
        else return 0;
    }

    private static void heal(Player player, double amount) {
        final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double newHealth = player.getHealth() + amount;
        if (newHealth > maxHealth) newHealth = maxHealth;
        player.setHealth(newHealth);
    }
}