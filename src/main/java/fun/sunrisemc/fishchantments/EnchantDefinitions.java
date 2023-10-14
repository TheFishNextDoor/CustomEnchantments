package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EnchantDefinitions {

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
            return EnchantmentTarget.ALL;
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

        static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
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
            return EnchantmentTarget.ALL;
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

        static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (plugin == null || player == null || projectile == null || bow == null || block == null) return;
            if (!Utl.Ench.hasEnchant(bow, plugin.TILLING)) return;
            projectile.remove();
            till(player, block);
        }

        static void onTill(Plugin plugin, Player player, ItemStack hoe, Block block) {
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
            return material == Material.DIRT || material == Material.GRASS_BLOCK || material == Material.GRASS_PATH;
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
            return EnchantmentTarget.ALL;
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

        static void onRightClick(Plugin plugin, Player player, ItemStack item, Block block) {
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

        static void onBlockBreak(Plugin plugin, Player player, ItemStack item, Block block, BlockBreakEvent event) {
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
            return EnchantmentTarget.ALL;
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

        static void onBlockBreak(Plugin plugin, Player player, ItemStack item, Block block, BlockBreakEvent event) {
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
            return EnchantmentTarget.ALL;
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

        static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (plugin == null || player == null || item == null) return;
            if (!Utl.Ench.hasEnchant(item, plugin.UNBREAKABLE)) return;
            event.setCancelled(true);
        }
    }

    public static class Food extends Enchantment {

        public static final String NAME = "Sustenance";

        public Food(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isHelmet(item.getType());
        }

        static void onHungerLoss(Plugin plugin, Player player, ItemStack item, FoodLevelChangeEvent event) {
            if (!Utl.Ench.hasEnchant(item, plugin.FOOD)) return;
            event.setFoodLevel(20);
        }
    }

    public static class Worm extends Enchantment {

        public static final String NAME = "Worm";

        public Worm(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isHelmet(item.getType());
        }

        static void onSuffocate(Plugin plugin, Player player, ItemStack item, EntityDamageEvent event) {
            if (!Utl.Ench.hasEnchant(item, plugin.WORM)) return;
            event.setCancelled(true);
        }
    }

    public static class Crush extends Enchantment {

        public static final String NAME = "Crush";

        public Crush(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public int getMaxLevel() {
            return 8;
        }

        @Override
        public int getStartLevel() {
            return 1;
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
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
            if (name.equals(Enchantment.PROTECTION_FALL.getName())) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isBoots(item.getType());
        }

        static void onFall(Plugin plugin, Player player, ItemStack item, double fallDamage, ArrayList<LivingEntity> fellOn) {
            final int level = Utl.Ench.getEnchantLevel(item, plugin.CRUSH);
            if (level < 1) return;
            final double damage = calcDamage(fallDamage, level);
            for (LivingEntity entity : fellOn) {
                entity.damage(damage, player);
            }
        }

        private static double calcDamage(double damage, int level) {
            if (level == 1) return damage/3;
            else if (level == 2) return damage/2;
            else if (level == 3) return damage;
            else if (level == 4) return damage * 1.5;
            else if (level == 5) return damage* 1.75;
            else if (level == 6) return damage * 2;
            else if (level == 7) return damage * 2.25;
            else if (level == 8) return damage * 2.5;
            else if (level == 9) return damage * 2.75;
            else if (level >= 10) return damage* 3;
            else return 0;
        }
    }

    public static class LifeSteal extends Enchantment {

        public static final String NAME = "Life Steal";

        public LifeSteal(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.LIFE_STEAL);
            if (level < 1) return;
            if (!ranged) damage /= 2;
            heal(player, calcAddedHealth(damage, level));
        }

        private static double calcAddedHealth(double damage, int level) {
            if (level == 1) return damage/6;
            else if (level == 2) return damage/5;
            else if (level == 3) return damage/4;
            else if (level == 4) return damage/3;
            else if (level == 5) return damage/2;
            else if (level == 6) return damage/1.75;
            else if (level == 7) return damage/1.5;
            else if (level == 8) return damage/1.25;
            else if (level == 9) return damage;
            else if (level >= 10) return damage*1.25;
            else return 0;
        }

        private static void heal(Player player, double amount) {
            final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double newHealth = player.getHealth() + amount;
            if (newHealth > maxHealth) newHealth = maxHealth;
            player.setHealth(newHealth);
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
            return EnchantmentTarget.ALL;
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

        static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
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
            return EnchantmentTarget.ALL;
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

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (plugin == null || player == null || projectile == null) return;
            final int level = Utl.Ench.getEnchantLevel(item, plugin.RANGE);
            if (level < 1) return;
            projectile.setVelocity(projectile.getVelocity().multiply(1 + (level/5)));
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
            return EnchantmentTarget.ALL;
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

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
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

    public static class Poison extends Enchantment {

        public static final String NAME = "Venom";

        public Poison(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.POISON);
            if (level < 1) return;
            int strength = level/2;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40 + (level * 20)/(strength + 1), strength), false);
        }
    }

    public static class Wither extends Enchantment {

        public static final String NAME = "Withering";

        public Wither(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.WITHER);
            if (level < 1) return;
            int strength = level/3;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40 + (level * 20)/(strength + 1), strength), false);
        }
    }

    public static class Helium extends Enchantment {

        public static final String NAME = "Levitating";

        public Helium(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType()) || Utl.Mat.isBoots(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.HELIUM);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, level * 20, 0), false);
        }

        static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Ench.getEnchantLevel(boots, plugin.HELIUM);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 120, level-1), false);
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
            return EnchantmentTarget.ALL;
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

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.GLOWING);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0), false);
        }

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Ench.hasEnchant(helmet, plugin.GLOWING) || Utl.Ench.hasEnchant(chestplate, plugin.GLOWING) || Utl.Ench.hasEnchant(leggings, plugin.GLOWING) || Utl.Ench.hasEnchant(boots, plugin.GLOWING))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0), false);
        }
    }

    public static class Blindness extends Enchantment {

        public static final String NAME = "Obscure";

        public Blindness(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.BLINDNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0), false);
        }
    }

    public static class Confusion extends Enchantment {

        public static final String NAME = "Disorienting";

        public Confusion(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.CONFUSION);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0), false);
        }
    }

    public static class Weakness extends Enchantment {

        public static final String NAME = "Debilitating";

        public Weakness(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.WEAKNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 + (level * 10), level/4), false);
        }
    }

    public static class Hunger extends Enchantment {

        public static final String NAME = "Starving";

        public Hunger(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Slowness.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.HUNGER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, level * 20, level/2), false);
        }
    }

    public static class Slowness extends Enchantment {

        public static final String NAME = "Crippling";

        public Slowness(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            if (name.equals(LifeSteal.NAME)) return true;
            if (name.equals(Poison.NAME)) return true;
            if (name.equals(Wither.NAME)) return true;
            if (name.equals(Helium.NAME)) return true;
            if (name.equals(Blindness.NAME)) return true;
            if (name.equals(Confusion.NAME)) return true;
            if (name.equals(Weakness.NAME)) return true;
            if (name.equals(Hunger.NAME)) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return Utl.Mat.isWeapon(item.getType());
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Utl.Ench.getEnchantLevel(weapon, plugin.SLOWNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 20, level/2), false);
        }
    }

    public static class Speed extends Enchantment {

        public static final String NAME = "Swiftness";

        public Speed(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isLeggings(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Ench.getEnchantLevel(boots, plugin.SPEED);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, level-1), false);
        }
    }

    public static class Jump extends Enchantment {

        public static final String NAME = "Leaping";

        public Jump(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isBoots(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Ench.getEnchantLevel(boots, plugin.JUMP);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, level-1), false);
        }
    }

    public static class SlowFall extends Enchantment {

        public static final String NAME = "Slow Falling";

        public SlowFall(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isBoots(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack boots) {
            int level = Utl.Ench.getEnchantLevel(boots, plugin.SLOW_FALL);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, level-1), false);
        }
    }

    public static class Resistance extends Enchantment {

        public static final String NAME = "Resistance";

        public Resistance(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.RESISTANCE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.RESISTANCE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.RESISTANCE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.RESISTANCE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, level-1), false);
        }
    }

    public static class Regeneration extends Enchantment {

        public static final String NAME = "Healing";

        public Regeneration(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.REGENERATION);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.REGENERATION);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.REGENERATION);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.REGENERATION);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, level-1), false);
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Ench.hasEnchant(helmet, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(chestplate, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(leggings, plugin.INVISIBILITY) || Utl.Ench.hasEnchant(boots, plugin.INVISIBILITY))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 0), false);
        }
    }

    public static class FireResistance extends Enchantment {

        public static final String NAME = "Fire Resistance";

        public FireResistance(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            if (!(Utl.Ench.hasEnchant(helmet, plugin.FIRE_RESISTANCE) || Utl.Ench.hasEnchant(chestplate, plugin.FIRE_RESISTANCE) || Utl.Ench.hasEnchant(leggings, plugin.FIRE_RESISTANCE) || Utl.Ench.hasEnchant(boots, plugin.FIRE_RESISTANCE))) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 0), false);
        }
    }

    public static class WaterBreathing extends Enchantment {

        public static final String NAME = "Gills";

        public WaterBreathing(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isHelmet(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            if (!Utl.Ench.hasEnchant(helmet, plugin.WATER_BREATHING)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 120, 0), false);
        }
    }

    public static class Strength extends Enchantment {

        public static final String NAME = "Strength";

        public Strength(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isChestplate(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack chestplate) {
            int level = Utl.Ench.getEnchantLevel(chestplate, plugin.STRENGTH);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, level-1), false);
        }
    }

    public static class Haste extends Enchantment {

        public static final String NAME = "Haste";

        public Haste(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isChestplate(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack chestplate) {
            int level = Utl.Ench.getEnchantLevel(chestplate, plugin.HASTE);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, level-1), false);
        }
    }

    public static class HealthBoost extends Enchantment {

        public static final String NAME = "Increased Health";

        public HealthBoost(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.HEALTH_BOOST);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.HEALTH_BOOST);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.HEALTH_BOOST);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.HEALTH_BOOST);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 120, level-1), false);
        }
    }

    public static class NightVision extends Enchantment {

        public static final String NAME = "Night Vision";

        public NightVision(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isHelmet(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            if (!Utl.Ench.hasEnchant(helmet, plugin.NIGHT_VISION)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 900, 0), false);
        }
    }

    public static class DolphinsGrace extends Enchantment {

        public static final String NAME = "Dolphins Grace";

        public DolphinsGrace(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isLeggings(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack leggings) {
            if (!Utl.Ench.hasEnchant(leggings, plugin.DOLPHINS_GRACE)) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 120, 0), false);
        }
    }

    public static class ConduitPower extends Enchantment {

        public static final String NAME = "Conduit Power";

        public ConduitPower(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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
            return Utl.Mat.isHelmet(item.getType());
        }

        static void onTimer(Plugin plugin, Player player, ItemStack helmet) {
            int level = Utl.Ench.getEnchantLevel(helmet, plugin.CONDUIT_POWER);
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 120, level-1), false);
        }
    }

    public static class HeroOfTheVillage extends Enchantment {

        public static final String NAME = "Hero of the Village";

        public HeroOfTheVillage(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.HERO_OF_THE_VILLAGE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.HERO_OF_THE_VILLAGE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.HERO_OF_THE_VILLAGE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.HERO_OF_THE_VILLAGE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 120, level-1), false);
        }
    }

    public static class MiningFatigueCurse extends Enchantment {

        public static final String NAME = "Curse of Mining Fatigue";

        public MiningFatigueCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return true;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.MINING_FATIGUE_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.MINING_FATIGUE_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.MINING_FATIGUE_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.MINING_FATIGUE_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 120, level-1), false);
        }
    }

    public static class SlownessCurse extends Enchantment {

        public static final String NAME = "Curse of Slowness";

        public SlownessCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return true;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.SLOWNESS_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.SLOWNESS_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.SLOWNESS_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.SLOWNESS_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, level-1), false);
        }
    }

    public static class WeaknessCurse extends Enchantment {

        public static final String NAME = "Curse of Weakness";

        public WeaknessCurse(NamespacedKey key) {
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
            return EnchantmentTarget.ALL;
        }

        @Override
        public boolean isTreasure() {
            return false;
        }

        @Override
        public boolean isCursed() {
            return true;
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

        static void onTimer(Plugin plugin, Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
            int helmetLevel = Utl.Ench.getEnchantLevel(helmet, plugin.WEAKNESS_CURSE);
            int chestplateLevel = Utl.Ench.getEnchantLevel(chestplate, plugin.WEAKNESS_CURSE);
            int leggingsLevel = Utl.Ench.getEnchantLevel(leggings, plugin.WEAKNESS_CURSE);
            int bootsLevel = Utl.Ench.getEnchantLevel(boots, plugin.WEAKNESS_CURSE);
            int level = Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
            if (level < 1) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, level-1), false);
        }
    }
}