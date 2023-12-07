package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Crippling extends Enchantment {

    public static final String NAME = "Crippling";

    public Crippling(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return NAME;
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
        if (name.equals(Glass.NAME)) return true;
        if (name.equals(Levitating.NAME)) return true;
        if (name.equals(BloodTipped.NAME)) return true;
        if (name.equals(Volley.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isWeapon(item.getType());
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity reciever, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.CRIPPLING, ranged);
        if (level < 1) return;
        int strength = level/3;
        reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40 + (level * 20)/(strength + 1), strength));
    }
}