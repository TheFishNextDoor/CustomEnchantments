package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class LifeSteal extends MutuallyExclusiveWeaponEnchantment {

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
    public boolean isCursed() {
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
        return "A portion of the damage you deal is added to your health. Rare drop from wither.";
    }

    public static void onPlayerAttackEntity(Player player, EntityDamageByEntityEvent event, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.LIFE_STEAL, ranged);
        if (level < 1) {
            return;
        }

        double damage = event.getFinalDamage();
        if (!ranged) {
            damage /= 2;
        }

        heal(player, calcAddedHealth(damage, level));
    }

    private static double calcAddedHealth(double damage, int level) {
        switch (level) {
            case 1:
                return damage/6;
            case 2:
                return damage/5;
            case 3:
                return damage/4;
            case 4:
                return damage/3;
            case 5:
                return damage/2;
            case 6:
                return damage/1.75;
            case 7:
                return damage/1.5;
            case 8:
                return damage/1.25;
            case 9:
                return damage;
            case 10:
                return damage*1.25;
            default:
                return 0;
        }
    }

    private static void heal(Player player, double amount) {
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double newHealth = player.getHealth() + amount;
        newHealth = Math.min(newHealth, maxHealth);
        player.setHealth(newHealth);
    }
}