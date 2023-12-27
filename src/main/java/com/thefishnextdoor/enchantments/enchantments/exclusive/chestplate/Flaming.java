package com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

public class Flaming extends MutuallyExclusiveChestplateEnchantment {

    public Flaming(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Flaming";
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
    public String getDescription() {
        return "Attackers will be lit on fire. Rare drop from blaze.";
    }

    public static void onEntityAttackPlayer(Player player, LivingEntity entity) {
        int level = EnchantUtil.level(player.getInventory().getChestplate(), CustomEnchantment.FLAMING);
        if (level < 1) return;
        entity.setFireTicks(level * 20);
    }
}