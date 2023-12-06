package fun.sunrisemc.fishchantments.enchantments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import fun.sunrisemc.fishchantments.PlayerTracker;
import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.PermChecker;
import fun.sunrisemc.fishchantments.Settings;
import fun.sunrisemc.fishchantments.Util;
import fun.sunrisemc.fishchantments.Util.Inventory;
import fun.sunrisemc.fishchantments.Util.World;
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
            return Util.Inventory.isEnchantable(item.getType());
        }

        public static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (plugin == null || player == null || item == null) return;
            if (!Util.Enchant.has(item, plugin.UNBREAKABLE)) return;
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
            return Util.Inventory.isTool(item.getType()) || Util.Inventory.isWeapon(item.getType());
        }

        public static void onBlockDropItems(Plugin plugin, Player player, List<Item> drops) {
            if (!Util.Enchant.holding(player, plugin.TELEKINESIS)) return;
            for (Item drop : drops) {
                Inventory.give(player, drop.getItemStack());
            }
            drops.clear();
        }

        public static void onBlockDropItems(Plugin plugin, Player player, Collection<ItemStack> drops) {
            if (!Util.Enchant.holding(player, plugin.TELEKINESIS)) return;
            for (ItemStack drop : drops) {
                Inventory.give(player, drop);
            }
            drops.clear();
        }

        public static void onMobLoot(Plugin plugin, Player player, List<ItemStack> drops) {
            if (!Util.Enchant.holding(player, plugin.TELEKINESIS)) return;
            for (ItemStack drop : drops) {
                Inventory.give(player, drop);
            }
            drops.clear();
        }

        public static void onBlockXp(Plugin plugin, Player player, BlockBreakEvent event) {
            if (!Util.Enchant.holding(player, plugin.TELEKINESIS)) return;
            player.giveExp(event.getExpToDrop());
            event.setExpToDrop(0);
        }

        public static void onMobXp(Plugin plugin, Player player, EntityDeathEvent event) {
            if (!Util.Enchant.holding(player, plugin.TELEKINESIS)) return;
            player.giveExp(event.getDroppedExp());
            event.setDroppedExp(0);
        }
    }

    public static class Radiance extends Enchantment {

        public static final String NAME = "Radiance";

        public Radiance(NamespacedKey key) {
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
            return Util.Inventory.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, LivingEntity reciever, boolean ranged) {
            final int level = Util.Enchant.weaponLevel(player, plugin.RADIANCE, ranged);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0));
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
            return Util.Inventory.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, final Entity entity, boolean ranged) {
            final int level = Util.Enchant.weaponLevel(player, plugin.FLING, ranged);
            if (level < 1) return;
            entity.teleport(entity.getLocation().add(0, 0.25, 0));
            final double y = (ranged ? 0.1 : 0.05) + (level * 0.1);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    entity.setVelocity(entity.getVelocity().add(new Vector(0, y, 0)));
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
            return Util.Inventory.isWeapon(item.getType()) || Util.Inventory.isTool(item.getType());
        }

        public static void onMobXp(Plugin plugin, Player player, EntityDeathEvent event) {
            int level = Util.Enchant.handLevel(player, plugin.ENLIGHTENMENT);
            if (level < 1) return;
            event.setDroppedExp(xp(level, event.getDroppedExp()));
        }

        public static void onBlockXp(Plugin plugin, Player player, BlockBreakEvent event) {
            int level = Util.Enchant.handLevel(player, plugin.ENLIGHTENMENT);
            if (level < 1) return;
            event.setExpToDrop(xp(level, event.getExpToDrop()));
        }

        private static int xp(int level, int xp) {
            return xp + (int) Math.round(xp * (level * 0.1));
        }
    }

    public static class Precision extends Enchantment {

        public static final String NAME = "Precision";

        public Precision(NamespacedKey key) {
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
            return Util.Inventory.isRanged(item.getType());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (plugin == null || player == null || projectile == null) return;
            if (!Util.Enchant.holding(player, plugin.PRECISION)) return;
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
            if (Util.Enchant.same(other, Enchantment.ARROW_INFINITE)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isRanged(item.getType());
        }

        public static void onProjectileHitBlock(Plugin plugin, Player player, Projectile projectile, Block block) {
            final int level = Util.Enchant.rangedLevel(player, plugin.DESTRUCTIVE);
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
            return Util.Inventory.isRanged(item.getType());
        }

        public static void onPlayerFireProjectile(Plugin plugin, Player player, Projectile projectile) {
            if (plugin == null || player == null || projectile == null) return;
            final int level = Util.Enchant.rangedLevel(player, plugin.RANGE);
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
            return Util.Inventory.isTool(item.getType());
        }

        public static void onBlockBreak(Plugin plugin, Player player, Block block, BlockBreakEvent event) {
            if (!Util.Enchant.holding(player, plugin.EXCAVATING)) return;
            BlockFace face = PlayerTracker.get(player).getMiningFace();
            ArrayList<Block> blocks = new ArrayList<>();
            if (face == BlockFace.EAST || face == BlockFace.WEST) { // Looking along x axis
                blocks.add(block.getRelative(0, 0, 1));
                blocks.add(block.getRelative(0, 0, -1));
                blocks.add(block.getRelative(0, 1, 0));
                blocks.add(block.getRelative(0, -1, 0));
                blocks.add(block.getRelative(0, 1, 1));
                blocks.add(block.getRelative(0, 1, -1));
                blocks.add(block.getRelative(0, -1, 1));
                blocks.add(block.getRelative(0, -1, -1));
            }
            else if (face == BlockFace.NORTH || face == BlockFace.SOUTH) {// Looking along z axis
                blocks.add(block.getRelative(0, 1, 0));
                blocks.add(block.getRelative(0, -1, 0));
                blocks.add(block.getRelative(1, 0, 0));
                blocks.add(block.getRelative(-1, 0, 0));
                blocks.add(block.getRelative(1, 1, 0));
                blocks.add(block.getRelative(1, -1, 0));
                blocks.add(block.getRelative(-1, 1, 0));
                blocks.add(block.getRelative(-1, -1, 0));
            }
            else if (face == BlockFace.UP || face == BlockFace.DOWN) { // Looking along Y axis
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
            ItemStack item = Inventory.getItemInUse(player);
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
            return Util.Inventory.isHoe(item.getType());
        }

        public static void onTill(Plugin plugin, Player player, Block block) {
            if (plugin == null || player == null || block == null) return;
            if (!Util.Enchant.holdingHoe(player, plugin.TILLING)) return;
            till(player, block);
        }

        private static void till(Player player, Block block) {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (isTillable(modifiedBlock.getType()) && PermChecker.canBreak(player, modifiedBlock)) modifiedBlock.setType(Material.FARMLAND);
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
            return Util.Inventory.isHoe(item.getType());
        }

        public static void onRightClick(Plugin plugin, Player player, Block block) {
            final int level = Util.Enchant.hoeLevel(player, plugin.REPLANTING);
            if (level < 1) return;
            ItemStack item = Inventory.getHoeInUse(player);
            if (item == null) return;
            if (level == 1) {
                if (PermChecker.canBreak(player, block)) harvest(plugin, player, block, item);
            }
            else {
                int x = block.getX(); int y = block.getY(); int z = block.getZ();
                int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
                for (int[] coords : allCoords) {
                    Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                    if (PermChecker.canBreak(player, modifiedBlock)) harvest(plugin, player, modifiedBlock, item);
                }
            }
        }

        public static void onBlockBreak(Plugin plugin, Player player, Block block, BlockBreakEvent event) {
            final int level = Util.Enchant.handLevel(player, plugin.REPLANTING);
            if (level < 1) return;
            ItemStack item = Inventory.getItemInUse(player);
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
            return Util.Inventory.isArmor(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player) {
            if (Util.Enchant.wearing(player, plugin.INVISIBILITY)) player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Settings.ARMOR_EFFECTS_PERIOD * 2, 0));
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
            int level = Util.Enchant.shieldLevel(player, plugin.REFLECTION);
            if (level < 1) return;
            if (level > 5) level = 5;
            projectile.setVelocity(projectile.getVelocity().multiply(3.5 + (level * 1.5)));
        }
    }

    public static class Spurs extends Enchantment {

        public static final String NAME = "Spurs";

        public Spurs(NamespacedKey key) {
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
            return EnchantmentTarget.ARMOR_FEET;
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
            return Util.Inventory.isBoots(item.getType());
        }

        public static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            if (player.getVehicle() == null || !(player.getVehicle() instanceof LivingEntity)) return;
            LivingEntity mount = (LivingEntity) player.getVehicle();
            int level = Util.Enchant.level(boots, plugin.SPURS);
            if (level < 1) return;
            mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Settings.QUICK_ARMOR_EFFECTS_PERIOD * 2, level - 1));
            mount.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Settings.QUICK_ARMOR_EFFECTS_PERIOD * 2, level - 1));
        }
    }

    public static class AquaAspect extends Enchantment {

        public static final String NAME = "Aqua Aspect";

        public AquaAspect(NamespacedKey key) {
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
            if (Util.Enchant.same(other, Enchantment.FIRE_ASPECT)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Util.Inventory.isWeapon(item.getType());
        }

        public static void onPlayerAttackEntity(Plugin plugin, Player player, final LivingEntity entity, EntityDamageByEntityEvent event, boolean ranged) {
            int level = Util.Enchant.weaponLevel(player, plugin.AQUA_ASPECT, ranged);
            if (level < 1) return;
            if (entity.getFireTicks() > 0) entity.setFireTicks(0);
            if (isAquaphobic(entity.getType())) event.setDamage(event.getDamage() + (level * 2.5));
            if (entity.getType() == EntityType.ENDERMAN) {
                World.cancelKnockback(plugin, entity);
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
}