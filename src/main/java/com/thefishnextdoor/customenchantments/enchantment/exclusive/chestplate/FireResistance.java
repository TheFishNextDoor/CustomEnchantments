package com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveChestplateEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class FireResistance extends MutuallyExclusiveChestplateEnchantment {

    public FireResistance(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Fire Resistance";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Wearer is immune to fire damage. Rare drop from blaze.";
    }

    public static void modifyCancelStatus(Player player, EntityDamageEvent event) {
        DamageCause cause = event.getCause();
        if (cause != DamageCause.FIRE && cause != DamageCause.FIRE_TICK && cause != DamageCause.LAVA && cause != DamageCause.HOT_FLOOR) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getChestplate(), CustomEnchantment.FIRE_RESISTANCE)) {
            return;
        }
        event.setCancelled(true);
    }
}