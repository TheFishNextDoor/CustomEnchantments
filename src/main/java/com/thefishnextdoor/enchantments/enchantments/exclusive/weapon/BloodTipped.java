package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class BloodTipped extends Enchantment {

    public static final String NAME = "Blood Tipped";

    public BloodTipped(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
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
    @SuppressWarnings("deprecation")
    public boolean conflictsWith(Enchantment other) {
        String name = other.getName();
        if (name.equals(LifeSteal.NAME)) return true;
        if (name.equals(Venom.NAME)) return true;
        if (name.equals(Withering.NAME)) return true;
        if (name.equals(Obscure.NAME)) return true;
        if (name.equals(Disorienting.NAME)) return true;
        if (name.equals(Debilitating.NAME)) return true;
        if (name.equals(Starving.NAME)) return true;
        if (name.equals(Crippling.NAME)) return true;
        if (name.equals(Glass.NAME)) return true;
        if (name.equals(Levitating.NAME)) return true;
        if (name.equals(Volley.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isRanged(item.getType());
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity livingEntity, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.BLOODTIPPED, ranged);
        if (level < 1) return;
        livingEntity.addPotionEffects(player.getActivePotionEffects());
    }

    public static void onPlayerFireProjectile(Player player) {
        if (EnchantUtil.holdingRanged(player, CustomEnchantment.BLOODTIPPED)) player.damage(1);
    }
}