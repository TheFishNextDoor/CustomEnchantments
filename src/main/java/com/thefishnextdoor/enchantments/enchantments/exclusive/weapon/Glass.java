package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.enchantments.Unbreakable;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class Glass extends Enchantment {

    public static final String NAME = "Glass";

    public Glass(NamespacedKey key) {
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
        return true;
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
        if (name.equals(Levitating.NAME)) return true;
        if (name.equals(BloodTipped.NAME)) return true;
        if (name.equals(Volley.NAME)) return true;
        if (EnchantUtil.same(other, Enchantment.DURABILITY)) return true;
        if (EnchantUtil.same(other, Enchantment.MENDING)) return true;
        if (name.equals(Unbreakable.NAME)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isWeapon(item.getType());
    }

    public static void onPlayerAttackEntity(Player player, double damage, EntityDamageByEntityEvent event, boolean ranged) {
        final int level = EnchantUtil.weaponLevel(player, CustomEnchantment.GLASS, ranged);
        if (level < 1) return;
        event.setDamage(damage * 1.5);
    }

    public static void onItemTakeDamage(Player player, ItemStack item, int damage, PlayerItemDamageEvent event) {
        if (EnchantUtil.has(item, CustomEnchantment.GLASS)) event.setDamage(damage * 32);
    }
}