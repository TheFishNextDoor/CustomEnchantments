package com.thefishnextdoor.customenchantments.enchantments.exclusive.weapon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantment;
import com.thefishnextdoor.customenchantments.Plugin;
import com.thefishnextdoor.customenchantments.Tag;
import com.thefishnextdoor.customenchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment.ArrowTransformEnchantment;
import com.thefishnextdoor.customenchantments.tools.EnchantTools;
import com.thefishnextdoor.customenchantments.tools.EntityTools;
import com.thefishnextdoor.customenchantments.tools.InventoryTools;
import com.thefishnextdoor.customenchantments.tools.WorldTools;

public class Destructive extends ArrowTransformEnchantment {

    public Destructive(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Destructive";
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
        return "Projectiles destroy blocks. Rare drop from creeper.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (EnchantTools.holdingRangedWith(player, CustomEnchantment.DESTRUCTIVE)) {
            Snowball snowball = (Snowball) EntityTools.convert(projectile, EntityType.SNOWBALL);
            snowball.setItem(new ItemStack(Material.TNT));
            Tag.EXPLODE_ON_IMPACT.applyTo(snowball);
        }
    }

    public static void onProjectileHitBlock(Player player, Projectile projectile, Block block) {
        if (!Tag.EXPLODE_ON_IMPACT.isOn(projectile)) {
            return;
        }

        Material material = breakWith(block);
        if (material == null) {
            return;
        }

        ItemStack item = InventoryTools.getRangedItemInUse(player);
        ItemStack newItem;
        if (item != null) {
            newItem = item.clone();
            newItem.setType(material);
        } 
        else {
            newItem = new ItemStack(material);
        }
        WorldTools.breakBlock(player, block, newItem);
        playEffect(block.getLocation().add(0.5, 0.5, 0.5));
    }

    public static void onProjectileHitEntity(Player player, Projectile projectile, Entity entity) {
        if (!Tag.EXPLODE_ON_IMPACT.isOn(projectile)) {
            return;
        }
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.damage(10.0, player);
        }
        playEffect(entity.getLocation());
    }

    private static Material breakWith(Block block) {
        ItemStack silkTouchPickaxe = new ItemStack(Material.IRON_PICKAXE);
        silkTouchPickaxe.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (!block.getDrops(silkTouchPickaxe).isEmpty()) {
            return Material.IRON_PICKAXE;
        }

        ItemStack shears = new ItemStack(Material.SHEARS);
        if (!block.getDrops(shears).isEmpty()) {
            return Material.SHEARS;
        }
        
        return null;
    }

    private static void playEffect(Location location) {
        if (!Plugin.getSettings().PLAY_EFFECTS) {
            return;
        }
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_LARGE, location, 1);
        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }
}