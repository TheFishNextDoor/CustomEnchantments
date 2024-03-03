package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

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
        int level = EnchantTools.level(player.getInventory().getChestplate(), CustomEnchantment.FLAMING);
        if (level < 1) {
            return;
        }
        entity.setFireTicks(level * 20);
    }
}