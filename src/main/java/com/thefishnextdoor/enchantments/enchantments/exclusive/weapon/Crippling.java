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

    public Crippling(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Crippling";
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
        if (EnchantUtil.same(other, CustomEnchantment.LIFE_STEAL)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.VENOM)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.WITHERING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.OBSCURE)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DISORIENTING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.DEBILITATING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.STARVING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.GLASS)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.LEVITATING)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.BLOOD_TIPPED)) return true;
        if (EnchantUtil.same(other, CustomEnchantment.VOLLEY)) return true;
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