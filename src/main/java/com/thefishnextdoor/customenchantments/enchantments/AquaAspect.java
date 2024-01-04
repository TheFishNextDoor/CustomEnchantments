package com.thefishnextdoor.customenchantments.enchantments;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.EntityTools;
import com.thefishnextdoor.customenchantments.tools.MaterialTools;

public class AquaAspect extends CustomEnchantment {

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
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        if (EnchantTools.same(other, Enchantment.FIRE_ASPECT)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        return MaterialTools.isWeapon(item.getType());
    }

    @Override
    public String getDescription() {
        return "Deal extra damage to enderman, blaze etc. Rare drop from drowned.";
    }

    public static void modifyDamage(Player player, final LivingEntity entity, EntityDamageByEntityEvent event, boolean ranged) {
        int level = EnchantTools.weaponLevel(player, CustomEnchantment.AQUA_ASPECT, ranged);
        if (level < 1) {
            return;
        }

        if (entity.getFireTicks() > 0) {
            entity.setFireTicks(0);
        }

        if (isAquaphobic(entity.getType())) {
            event.setDamage(event.getDamage() + (level * 2.5));
        }

        if (entity instanceof Enderman) {
            EntityTools.cancelKnockback(entity);
            teleport((Enderman) entity, player);
        }
    }

    private static boolean isAquaphobic(EntityType type) {
        switch (type) {
            case ENDERMAN:
            case BLAZE:
            case MAGMA_CUBE:
            case STRIDER:
                return true;
            default:
                return false;
        }
    }

    private static void teleport(Enderman enderman, Player attacker) {
        World world = enderman.getWorld();
        Location location = enderman.getLocation().add(0, enderman.getHeight() + 1.0, 0);
        Snowball snowball = (Snowball) world.spawnEntity(location, EntityType.SNOWBALL);
        snowball.setVelocity(new Vector(0, -2.0, 0));
        snowball.setShooter(attacker);
        snowball.setVisibleByDefault(false);
    }
}