package com.thefishnextdoor.enchantments.enchantments.exclusive.weapon;

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
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.Settings;
import com.thefishnextdoor.enchantments.CustomEnchantment.MutuallyExclusiveWeaponEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.EntityUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;
import com.thefishnextdoor.enchantments.util.BlockUtil;

public class Destructive extends MutuallyExclusiveWeaponEnchantment {

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
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.firesArrows(item.getType());
    }

    @Override
    public String getDescription() {
        return "Projectiles destroy blocks. Rare drop from creeper.";
    }

    public static void convertProjectile(Player player, Projectile projectile) {
        if (!EnchantUtil.holdingRangedWith(player, CustomEnchantment.DESTRUCTIVE)) return;
        Snowball snowball = (Snowball) EntityUtil.convert(projectile, EntityType.SNOWBALL);
        snowball.setItem(new ItemStack(Material.TNT));
    }

    public static void onProjectileHitBlock(Player player, Projectile projectile, Block block) {
        ItemStack item = InventoryUtil.getRangedItemInUse(player);
        final int level = EnchantUtil.level(item, CustomEnchantment.DESTRUCTIVE);
        if (level < 1) return;
        Material material = breakableMaterial(block);
        if (material == null) return;
        ItemStack newItem = item.clone();
        newItem.setType(material);
        BlockUtil.breakBlock(player, block, newItem);
        if (Settings.PLAY_EFFECTS) playEffect(block.getLocation().add(0.5, 0.5, 0.5));
    }

    public static void onProjectileHitEntity(Player player, Projectile projectile, Entity entity) {
        final int level = EnchantUtil.rangedLevel(player, CustomEnchantment.DESTRUCTIVE);
        if (level < 1) return;
        if (Settings.PLAY_EFFECTS) playEffect(entity.getLocation());
    }

    private static Material breakableMaterial(Block block) {
        ItemStack silkTouchPickaxe = new ItemStack(Material.IRON_PICKAXE);
        silkTouchPickaxe.addEnchantment(Enchantment.SILK_TOUCH, 1);
        if (!block.getDrops(silkTouchPickaxe).isEmpty()) return Material.IRON_PICKAXE;
        ItemStack shears = new ItemStack(Material.SHEARS);
        if (!block.getDrops(shears).isEmpty()) return Material.SHEARS;
        return null;
    }

    private static void playEffect(Location location) {
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_LARGE, location, 1);
        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }
}