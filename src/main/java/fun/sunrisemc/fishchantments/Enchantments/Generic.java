package fun.sunrisemc.fishchantments.enchantments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;
import fun.sunrisemc.fishchantments.Utl.Nvntry;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Glass;

public class Generic {

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
        @SuppressWarnings("deprecation")
        public boolean conflictsWith(Enchantment other) {
            String name = other.getName();
            if (name.equals(Glass.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isEnchantable(item.getType());
        }

        public static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (plugin == null || player == null || item == null) return;
            if (!Utl.Nchnt.has(item, plugin.UNBREAKABLE)) return;
            event.setCancelled(true);
        }
    }

    public static class Telekinesis extends Enchantment {

        public static final String NAME = "Telekinesis";

        public Telekinesis(NamespacedKey key) {
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
            return Utl.Mtrl.isTool(item.getType()) || Utl.Mtrl.isWeapon(item.getType());
        }

        public static void onBlockDropItems(Plugin plugin, Player player, List<Item> drops) {
            if (!Utl.Nchnt.holding(player, plugin.TELEKINESIS)) return;
            for (Item drop : drops) {
                Nvntry.give(player, drop.getItemStack());
            }
            drops.clear();
        }

        public static void onBlockDropItems(Plugin plugin, Player player, Collection<ItemStack> drops) {
            if (!Utl.Nchnt.holding(player, plugin.TELEKINESIS)) return;
            for (ItemStack drop : drops) {
                Nvntry.give(player, drop);
            }
            drops.clear();
        }

        public static void onMobLoot(Plugin plugin, Player player, List<ItemStack> drops) {
            if (!Utl.Nchnt.holding(player, plugin.TELEKINESIS)) return;
            for (ItemStack drop : drops) {
                Nvntry.give(player, drop);
            }
            drops.clear();
        }

        public static void onMobXp(Plugin plugin, Player player, EntityDeathEvent event) {
            if (!Utl.Nchnt.holding(player, plugin.TELEKINESIS)) return;
            int xp = event.getDroppedExp();
            event.setDroppedExp(0);
            player.giveExp(xp);
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
            return Utl.Mtrl.isWeapon(item.getType()) || Utl.Mtrl.isArmor(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.GLOWING, ranged);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0));
        }

        public static void onTimer(Plugin plugin, Player player) {
            if (!Utl.Nchnt.wearing(player, plugin.GLOWING)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, 0));
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
            return Utl.Mtrl.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, boolean ranged) {
            final int level = Utl.Nchnt.weaponLevel(player, plugin.FLING, ranged);
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

    public static class Enlightenment extends Enchantment {

        public static final String NAME = "Enlightenment";

        public Enlightenment(NamespacedKey key) {
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
            return Utl.Mtrl.isWeapon(item.getType());
        }

        public static void onMobXp(Plugin plugin, Player player, EntityDeathEvent event) {
            int level = Utl.Nchnt.handLevel(player, plugin.ENLIGHTENMENT);
            if (level < 1) return;
            int xp = event.getDroppedExp();
            event.setDroppedExp(xp + (int) Math.round(xp * (level * 0.1)));
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
            return Utl.Mtrl.isRanged(item.getType());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (plugin == null || player == null || projectile == null) return;
            if (!Utl.Nchnt.holding(player, plugin.ACCURATE)) return;
            Vector direction = player.getEyeLocation().getDirection();
            Vector velocity = projectile.getVelocity();
            velocity.setX(direction.getX() * velocity.length());
            velocity.setY(direction.getY() * velocity.length());
            velocity.setZ(direction.getZ() * velocity.length());
            projectile.setVelocity(velocity);
        }
    }

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
        public boolean conflictsWith(Enchantment other) {
            if (Utl.Nchnt.same(other, Enchantment.ARROW_INFINITE)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mtrl.isRanged(item.getType());
        }

        public static void onProjectileHitBlock(Plugin plugin, Player player, Projectile projectile, Block block) {
            final int level = Utl.Nchnt.rangedLevel(player, plugin.DESTRUCTIVE);
            if (level < 1) return;
            ItemStack usedTool = new ItemStack(new ItemStack(Material.SHEARS));
            boolean hasDrops = !block.getDrops(new ItemStack(usedTool)).isEmpty();
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
            projectile.remove();
            plugin.breakBlock(player, block);
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
            return Utl.Mtrl.isRanged(item.getType());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (plugin == null || player == null || projectile == null) return;
            final int level = Utl.Nchnt.rangedLevel(player, plugin.RANGE);
            if (level < 1) return;
            double levelValue = level; 
            projectile.setVelocity(projectile.getVelocity().multiply(1.0 + (levelValue/5.0)));
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
            return Utl.Mtrl.isTool(item.getType());
        }

        public static void onBlockBreak(Plugin plugin, Player player, Block block, BlockBreakEvent event) {
            if (!Utl.Nchnt.holding(player, plugin.EXCAVATING)) return;
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
            ItemStack item = Nvntry.getItemInUse(player);
            while (iter.hasNext()) {
                Block iblock = iter.next();
                if ((!iblock.getDrops(item).isEmpty() || !iblock.getDrops(new ItemStack(Material.SHEARS)).isEmpty())) plugin.breakBlock(player, iblock, item);
            }
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
            return Utl.Mtrl.isHoe(item.getType());
        }

        public static void onProjectileHitBlock(Plugin plugin, Player player, Projectile projectile, Block block) {
            if (!Utl.Nchnt.holdingRanged(player, plugin.TILLING)) return;
            projectile.remove();
            till(player, block);
        }

        public static void onTill(Plugin plugin, Player player, Block block) {
            if (plugin == null || player == null || block == null) return;
            if (!Utl.Nchnt.holdingHoe(player, plugin.TILLING)) return;
            till(player, block);
        }

        private static void till(Player player, Block block) {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (isTillable(modifiedBlock.getType()) && Utl.PrmChkr.canBreak(player, modifiedBlock)) modifiedBlock.setType(Material.FARMLAND);
            }
        }

        private static boolean isTillable(Material material) {
            return material == Material.DIRT || material == Material.GRASS_BLOCK || material == Material.DIRT_PATH;
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
            return Utl.Mtrl.isHoe(item.getType());
        }

        public static void onRightClick(Plugin plugin, Player player, Block block) {
            final int level = Utl.Nchnt.hoeLevel(player, plugin.REPLANTING);
            if (level < 1) return;
            ItemStack item = Nvntry.getHoeInUse(player);
            if (item == null) return;
            if (level == 1) {
                if (Utl.PrmChkr.canBreak(player, block)) harvest(plugin, player, block, item);
            }
            else {
                int x = block.getX(); int y = block.getY(); int z = block.getZ();
                int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
                for (int[] coords : allCoords) {
                    Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                    if (Utl.PrmChkr.canBreak(player, modifiedBlock)) harvest(plugin, player, modifiedBlock, item);
                }
            }
        }

        public static void onBlockBreak(Plugin plugin, Player player, Block block, BlockBreakEvent event) {
            final int level = Utl.Nchnt.handLevel(player, plugin.REPLANTING);
            if (level < 1) return;
            ItemStack item = Nvntry.getItemInUse(player);
            event.setCancelled(harvest(plugin, player, block, item));
        }

        private static boolean harvest(Plugin plugin, Player player, Block block, ItemStack item) {
            BlockState state = block.getState();
            if (!(state.getBlockData() instanceof Ageable)) return false;
            Ageable ageable = (Ageable) state.getBlockData();
            if (ageable.getAge() != ageable.getMaximumAge()) return false;
            plugin.blockDrops(player, block, item);
            ageable.setAge(0);
            block.setBlockData(ageable);
            return true;
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
            return Utl.Mtrl.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            if (Utl.Nchnt.wearing(player, plugin.INVISIBILITY)) player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Plugin.getSettings().ARMOR_EFFECTS_PERIOD * 2, 0));
        }
    }

    public static class Momentum extends Enchantment {

        public static final String NAME = "Momentum";

        public Momentum(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Boosters.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return item.getType() == Material.ELYTRA;
        }

        public static void onGliding(Plugin plugin, Player player) {
            Vector velocity = player.getVelocity();
            double speed = velocity.length();
            if (speed < 0.6 || speed > 2.5) return;
            float pitch = -((float) Math.toDegrees(Math.asin(velocity.getY() / velocity.length())));
            if (pitch <= 0) return;
            int level = Utl.Nchnt.level(player.getInventory().getChestplate(), plugin.MOMENTUM);
            if (level < 1) return;
            if (level > 10) level = 10;
            Vector increase = velocity.clone().normalize().multiply(level * (pitch/10) * 0.002);
            player.setVelocity(velocity.add(increase));
        }
    }

    public static class Boosters extends Enchantment {

        public static final String NAME = "Boosters";

        public Boosters(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_TORSO;
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
            if (name.equals(Momentum.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return item.getType() == Material.ELYTRA;
        }

        public static void onGliding(Plugin plugin, Player player) {
            if (player.isInWater()) return;
            Vector velocity = player.getVelocity();
            int level = Utl.Nchnt.level(player.getInventory().getChestplate(), plugin.BOOSTERS);
            if (level < 1) return;
            if (level > 10) level = 10;
            if (velocity.length() > 0.75 + (0.25 * level)) return;
            Vector increase = player.getLocation().getDirection().clone().normalize().multiply(level * 0.01);
            player.setVelocity(velocity.add(increase));
        }
    }

    public static class Reflection extends Enchantment {

        public static final String NAME = "Reflection";

        public Reflection(NamespacedKey key) {
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
            return item.getType() == Material.SHIELD;
        }

        public static void onDeflectProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (projectile.isOnGround()) return;
            int level = Utl.Nchnt.shieldLevel(player, plugin.REFLECTION);
            if (level < 1) return;
            if (level > 5) level = 5;
            projectile.setVelocity(projectile.getVelocity().multiply(3.5 + (level * 1.5)));
        }
    }
}