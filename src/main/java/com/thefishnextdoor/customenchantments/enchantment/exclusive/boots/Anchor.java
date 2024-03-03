package com.thefishnextdoor.customenchantments.enchantment.exclusive.boots;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.ArmorEffects;
import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveBootsEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public class Anchor extends MutuallyExclusiveBootsEnchantment {

    public Anchor(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Anchor";
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
        return "Wearer sinks in water and has increased traction in water. Should be combined with depth strider for best effect. Rare drop from drowned.";
    }

    public static void whenSwimming(Player player) {
        if (!EnchantTools.has(player.getInventory().getBoots(), CustomEnchantment.ANCHOR)) {
            return;
        }

        Vector velocity = player.getVelocity();
        double y = velocity.getY();
        if (y <= 0) {
            velocity.setY(y - (0.06));
        }
        else {
            velocity.setY(y - (0.03));
        }
        player.setVelocity(velocity);
    }

    public static void onTimer(Player player, ItemStack boots) {
        if (!player.isInWater()) {
            return;
        }
        if (!EnchantTools.has(player.getInventory().getBoots(), CustomEnchantment.ANCHOR)) {
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, ArmorEffects.PERIOD * 2, 2));
    }
}