package fun.sunrisemc.fishchantments.Enchantments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;

public class Generic {

    public static class Destructive extends Enchantment {

        public static final String NAME = "Destructive";

        public Destructive(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 4;
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
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Enchantment.ARROW_INFINITE.getName())) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isRangedWeapon(item.getType());
        }

        public static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (plugin == null || player == null || projectile == null || bow == null || block == null) return;
            final int level = Utl.Ench.getEnchantLevel(bow, plugin.DESTRUCTIVE);
            if (level < 1) return;
            ItemStack usedTool = bow;
            boolean hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
            if (!hasDrops) hasDrops = !block.getDrops(new ItemStack(new ItemStack(Material.SHEARS))).isEmpty();
            if (!hasDrops && level >= 2) {
                usedTool = new ItemStack(Material.WOODEN_PICKAXE);
                hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
            }
            if (!hasDrops && level >= 3) {
                usedTool = new ItemStack(Material.IRON_PICKAXE);
                hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
            }
            if (!hasDrops && level >= 4) {
                usedTool = new ItemStack(Material.DIAMOND_PICKAXE);
                hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
            }
            if (!hasDrops) return;
            if (!Utl.PrmChkr.canModify(player, block)) return;
            projectile.remove();
            block.breakNaturally(usedTool);
        }
    }

    public static class Range extends Enchantment {

        public static final String NAME = "Range";

        public Range(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 3;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isRangedWeapon(item.getType());
        }

        public static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (plugin == null || player == null || projectile == null) return;
            final int level = Utl.Ench.getEnchantLevel(item, plugin.RANGE);
            if (level < 1) return;
            double levelValue = level; 
            projectile.setVelocity(projectile.getVelocity().multiply(1.0 + (levelValue/5.0)));
        }
    }

    public static class Tilling extends Enchantment {

        public static final String NAME = "Tilling";

        public Tilling(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return EnchantmentTarget.TOOL;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHoe(item.getType());
        }

        public static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (plugin == null || player == null || projectile == null || bow == null || block == null) return;
            if (!Utl.Ench.hasEnchant(bow, plugin.TILLING)) return;
            projectile.remove();
            till(player, block);
        }

        public static void onTill(Plugin plugin, Player player, ItemStack hoe, Block block) {
            if (plugin == null || player == null || hoe == null || block == null) return;
            if (!Utl.Ench.hasEnchant(hoe, plugin.TILLING)) return;
            till(player, block);
        }

        private static void till(Player player, Block block) {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (isTillable(modifiedBlock.getType()) && Utl.PrmChkr.canModify(player, modifiedBlock)) modifiedBlock.setType(Material.FARMLAND);
            }
        }

        private static boolean isTillable(Material material) {
            return material == Material.DIRT || material == Material.GRASS_BLOCK || material == Material.DIRT_PATH;
        }
    }

    public static class Glowing extends Enchantment {

        public static final String NAME = "Radiance";

        public Glowing(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 5;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType()) || Utl.Mat.isArmor(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.GLOWING);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0));
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Ench.hasEnchant(helmet, plugin.GLOWING) || Utl.Ench.hasEnchant(chestplate, plugin.GLOWING) || Utl.Ench.hasEnchant(leggings, plugin.GLOWING) || Utl.Ench.hasEnchant(boots, plugin.GLOWING))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0));
        }
    }

    public static class Unbreakable extends Enchantment {

        public static final String NAME = "Unbreakable";

        public Unbreakable(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isEnchantable(item.getType());
        }

        public static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (plugin == null || player == null || item == null) return;
            if (!Utl.Ench.hasEnchant(item, plugin.UNBREAKABLE)) return;
            event.setCancelled(true);
        }
    }

    public static class Replanting extends Enchantment {

        public static final String NAME = "Replanting";

        public Replanting(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return EnchantmentTarget.TOOL;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isHoe(item.getType());
        }

        public static void onRightClick(Plugin plugin, Player player, ItemStack item, Block block) {
            final int level = Utl.Ench.getEnchantLevel(item, plugin.REPLANTING);
            if (level == 1) {
                if (Utl.PrmChkr.canModify(player, block)) harvest(player, block, item);
            }
            else if (level >= 2) {
                int x = block.getX(); int y = block.getY(); int z = block.getZ();
                int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
                for (int[] coords : allCoords) {
                    Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                    if (Utl.PrmChkr.canModify(player, modifiedBlock)) harvest(player, modifiedBlock, item);
                }
            }
        }

        public static void onBlockBreak(Plugin plugin, Player player, ItemStack item, Block block, BlockBreakEvent event) {
            final int level = Utl.Ench.getEnchantLevel(item, plugin.REPLANTING);
            if (level < 1) return;
            event.setCancelled(harvest(player, block, item));
        }

        private static boolean harvest(Player player, Block block, ItemStack item) {
            BlockState state = block.getState();
            if (!(state.getBlockData() instanceof Ageable)) return false;
            Ageable ageable = (Ageable) state.getBlockData();
            if (ageable.getAge() != ageable.getMaximumAge()) return false;
            Collection<ItemStack> drops = block.getDrops(item);
            ageable.setAge(0);
            block.setBlockData(ageable);
            for (ItemStack drop : drops) {
                block.getWorld().dropItemNaturally(block.getLocation(), drop);
            }
            return true;
        }
    }

    public static class Excavating extends Enchantment {

        public static final String NAME = "Excavating";

        public Excavating(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return EnchantmentTarget.TOOL;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isTool(item.getType());
        }

        public static void onBlockBreak(Plugin plugin, Player player, ItemStack item, Block block, BlockBreakEvent event) {
            if (!Utl.Ench.hasEnchant(item, plugin.EXCAVATING)) return;
            Vector direction = player.getLocation().getDirection();
            double x = Math.abs(direction.getX());
            double y = Math.abs(direction.getY());
            double z = Math.abs(direction.getZ());
            ArrayList<Block> blocks = new ArrayList<>();
            if (x > y && x > z) { // Looking along x axis
                blocks.add(block.getRelative(0, 0, 1));
                blocks.add(block.getRelative(0, 0, -1));
                blocks.add(block.getRelative(0, 1, 0));
                blocks.add(block.getRelative(0, -1, 0));
                blocks.add(block.getRelative(0, 1, 1));
                blocks.add(block.getRelative(0, 1, -1));
                blocks.add(block.getRelative(0, -1, 1));
                blocks.add(block.getRelative(0, -1, -1));
            }
            else if (z > x && z > y) {// Looking along z axis
                blocks.add(block.getRelative(0, 1, 0));
                blocks.add(block.getRelative(0, -1, 0));
                blocks.add(block.getRelative(1, 0, 0));
                blocks.add(block.getRelative(-1, 0, 0));
                blocks.add(block.getRelative(1, 1, 0));
                blocks.add(block.getRelative(1, -1, 0));
                blocks.add(block.getRelative(-1, 1, 0));
                blocks.add(block.getRelative(-1, -1, 0));
            }
            else if (y > x && y > z) { // Looking along Y axis
                blocks.add(block.getRelative(0, 0, 1));
                blocks.add(block.getRelative(0, 0, -1));
                blocks.add(block.getRelative(1, 0, 0));
                blocks.add(block.getRelative(-1, 0, 0));
                blocks.add(block.getRelative(1, 0, 1));
                blocks.add(block.getRelative(1, 0, -1));
                blocks.add(block.getRelative(-1, 0, 1));
                blocks.add(block.getRelative(-1, 0, -1));
            }
            Iterator<Block> iter = blocks.iterator();
            while (iter.hasNext()) {
                Block iblock = iter.next();
                if (Utl.PrmChkr.canModify(player, iblock) && (!iblock.getDrops(item).isEmpty() || !iblock.getDrops(new ItemStack(Material.SHEARS)).isEmpty())) iblock.breakNaturally(item);
            }
        }
    }

    public static class Fling extends Enchantment {

        public static final String NAME = "Fling";

        public Fling(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.FLING);
            if (level < 1) return;
            final double velocity = ((ranged ? level * 1.75 : level * 1.0) * 0.05) + 0.1;
            final Entity finalEntity = entity;
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    final Vector currentVelocity = finalEntity.getVelocity();
                    final Vector additionalVelocity = new Vector(0, velocity, 0);
                    final Vector newVelocity = currentVelocity.add(additionalVelocity);
                    finalEntity.setVelocity(newVelocity);
                }
            }, 0);
        }
    }

    public static class Accurate extends Enchantment {

        public static final String NAME = "Precision";

        public Accurate(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isRangedWeapon(item.getType());
        }

        public static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (plugin == null || player == null || projectile == null) return;
            if (!Utl.Ench.hasEnchant(item, plugin.ACCURATE)) return;
            Vector direction = player.getEyeLocation().getDirection();
            Vector velocity = projectile.getVelocity();
            velocity.setX(direction.getX() * velocity.length());
            velocity.setY(direction.getY() * velocity.length());
            velocity.setZ(direction.getZ() * velocity.length());
            projectile.setVelocity(velocity);
        }
    }

    public static class Invisibility extends Enchantment {

        public static final String NAME = "Invisivility";

        public Invisibility(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
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
            return EnchantmentTarget.WEARABLE;
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
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Ench.hasEnchant(helmet, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(chestplate, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(leggings, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(boots, plugin.INVISIBILITY))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 0));
        }
    }
}