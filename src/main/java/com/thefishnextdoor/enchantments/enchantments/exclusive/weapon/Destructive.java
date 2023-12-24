package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.BlockUtil;

public class Destructive extends Enchantment {

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
        if (EnchantUtil.same(other, Enchantment.ARROW_INFINITE)) return true;
        return CustomEnchantment.isMutuallyExclusiveWeapon(other);
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isRanged(item.getType());
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRanged(player, CustomEnchantment.DESTRUCTIVE)) return;
        Snowball snowball = (Snowball) EntityUtil.convert(projectile, EntityType.SNOWBALL);
        snowball.setItem(new ItemStack(Material.TNT));
    }

    public static void onProjectileHitBlock(Player player, Projectile projectile, Block block) {
        ItemStack item = InventoryUtil.getRangedItemInUse(player);
        final int level = EnchantUtil.level(item, CustomEnchantment.DESTRUCTIVE);
        if (level < 1) return;
        if (!breakable(block)) return;
        BlockUtil.breakBlock(player, block, item);
        if (Settings.PLAY_EFFECTS) playEffect(block.getLocation().add(0.5, 0.5, 0.5));
    }

    public static void onProjectileHitEntity(Player player, Projectile projectile, Entity entity) {
        final int level = EnchantUtil.rangedLevel(player, CustomEnchantment.DESTRUCTIVE);
        if (level < 1) return;
        if (Settings.PLAY_EFFECTS) playEffect(entity.getLocation());
    }

    private static boolean breakable(Block block) {
        ItemStack silkTouchPickaxe = new ItemStack(Material.IRON_PICKAXE);
        silkTouchPickaxe.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (!block.getDrops(silkTouchPickaxe).isEmpty()) return true;
        ItemStack shears = new ItemStack(Material.SHEARS);
        if (!block.getDrops(shears).isEmpty()) return true;
        return false;
    }

    private static void playEffect(Location location) {
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_LARGE, location, 1);
        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }
}