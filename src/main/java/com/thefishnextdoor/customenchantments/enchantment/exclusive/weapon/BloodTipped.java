package com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.enchantment.type.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

public class BloodTipped extends MutuallyExclusiveWeaponEnchantment {

    public BloodTipped(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Blood Tipped";
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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Take slight damage when firing, applies your potion effects to the arrow. Rare drop from piglin.";
    }

    public static void onPlayerAttackEntity(Player player, LivingEntity livingEntity, boolean ranged) {
        final int level = EnchantTools.weaponLevel(player, CustomEnchantment.BLOOD_TIPPED, ranged);
        if (level < 1) {
            return;
        }
        livingEntity.addPotionEffects(player.getActivePotionEffects());
    }

    public static void onPlayerFireProjectile(Player player, Projectile projectile) {
        if (!EnchantTools.holdingRangedWith(player, CustomEnchantment.BLOOD_TIPPED)) {
            return;
        }

        player.damage(1, player);

        if (!(projectile instanceof Arrow)) {
            return;
        }

        Arrow arrow = (Arrow) projectile;
        for (PotionEffect effect : player.getActivePotionEffects()) {
            arrow.addCustomEffect(effect, true);
        }

        if (arrow.hasCustomEffects()) {
            arrow.setPickupStatus(PickupStatus.DISALLOWED);
        }
    }
}