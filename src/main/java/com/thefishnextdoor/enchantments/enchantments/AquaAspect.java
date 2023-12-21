package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public class AquaAspect extends Enchantment {

    public AquaAspect(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Aqua Aspect";
    }

    @Override
    public int getMaxLevel() {
        return 2;
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
        if (EnchantUtil.same(other, Enchantment.FIRE_ASPECT)) return true;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isWeapon(item.getType());
    }

    public static void modifyDamage(Player player, final LivingEntity entity, EntityDamageByEntityEvent event, boolean ranged) {
        int level = EnchantUtil.weaponLevel(player, CustomEnchantment.AQUA_ASPECT, ranged);
        if (level < 1) return;
        if (entity.getFireTicks() > 0) entity.setFireTicks(0);
        if (isAquaphobic(entity.getType())) event.setDamage(event.getDamage() + (level * 2.5));
        if (entity.getType() == EntityType.ENDERMAN) {
            EntityUtil.cancelKnockback(entity);
            Snowball snowball = (Snowball) entity.getWorld().spawnEntity(entity.getLocation().add(0, 4, 0), EntityType.SNOWBALL);
            snowball.setVelocity(new Vector(0, -2.0, 0));
            snowball.setShooter(player);
        }
    }

    private static boolean isAquaphobic(EntityType type) {
        if (type == EntityType.ENDERMAN) return true;
        if (type == EntityType.BLAZE) return true;
        if (type == EntityType.MAGMA_CUBE) return true;
        if (type == EntityType.STRIDER) return true;
        return false;
    }
}