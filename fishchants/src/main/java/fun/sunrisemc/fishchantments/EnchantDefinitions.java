package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import java.util.Collection;

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

        public Destructive(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Destructive";
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
        public boolean conflictsWith(Enchantment other) {
            if (other == Enchantment.ARROW_INFINITE) return true;
            return false;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (plugin == null || player == null || projectile == null || bow == null || block == null) return;
            final int level = Plugin.getEnchantLevel(bow, plugin.DESTRUCTIVE);
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
            if (!Plugin.playerCanModify(player, block)) return;
            projectile.remove();
            block.breakNaturally(usedTool);
        }
    }

    public static class Tilling extends Enchantment {

        public Tilling(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Tilling";
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
            return true;
        }

        static void onArrowHitBlock(Plugin plugin, Player player, Projectile projectile, ItemStack bow, Block block) {
            if (plugin == null || player == null || projectile == null || bow == null || block == null) return;
            if (!Plugin.hasEnchant(bow, plugin.TILLING)) return;
            projectile.remove();
            till(player, block);
        }

        static void onTill(Plugin plugin, Player player, ItemStack hoe, Block block) {
            if (plugin == null || player == null || hoe == null || block == null) return;
            if (!Plugin.hasEnchant(hoe, plugin.TILLING)) return;
            till(player, block);
        }

        private static void till(Player player, Block block) {
            int x = block.getX(); int y = block.getY(); int z = block.getZ();
            int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
            for (int[] coords : allCoords) {
                Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                if (isTillable(modifiedBlock.getType()) && Plugin.playerCanModify(player, modifiedBlock)) modifiedBlock.setType(Material.FARMLAND);
            }
        }

        private static boolean isTillable(Material material) {
            return material == Material.DIRT || material == Material.GRASS_BLOCK || material == Material.GRASS_PATH;
        }
        
    }

    public static class Replanting extends Enchantment {

        public Replanting(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Replanting";
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
            return true;
        }

        static void onRightClick(Plugin plugin, Player player, ItemStack item, Block block) {
            final int level = Plugin.getEnchantLevel(item, plugin.REPLANTING);
            if (level == 1) {
                if (Plugin.playerCanModify(player, block)) harvest(player, block, item);
            }
            else if (level >= 2) {
                int x = block.getX(); int y = block.getY(); int z = block.getZ();
                int[][] allCoords = {{x, y, z},{x + 1, y, z},{x - 1, y, z},{x, y, z + 1},{x, y, z - 1},{x + 1, y, z + 1},{x + 1, y, z - 1},{x - 1, y, z + 1},{x - 1, y, z - 1}};
                for (int[] coords : allCoords) {
                    Block modifiedBlock = block.getWorld().getBlockAt(coords[0], coords[1], coords[2]);
                    if (Plugin.playerCanModify(player, modifiedBlock)) harvest(player, modifiedBlock, item);
                }
            }
        }

        static void onBlockBreak(Plugin plugin, Player player, ItemStack item, Block block, BlockBreakEvent event) {
            final int level = Plugin.getEnchantLevel(item, plugin.REPLANTING);
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

    public static class Unbreakable extends Enchantment {

        public Unbreakable(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Unbreakable";
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
            return true;
        }

        static void onItemTakeDamage(Plugin plugin, Player player, ItemStack item, PlayerItemDamageEvent event) {
            if (plugin == null || player == null || item == null) return;
            if (!Plugin.hasEnchant(item, plugin.UNBREAKABLE)) return;
            event.setCancelled(true);
        }
    }

    public static class Food extends Enchantment {

        public Food(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Sustenance";
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
            return true;
        }

        static void onHungerLoss(Plugin plugin, Player player, ItemStack item, FoodLevelChangeEvent event) {
            if (!Plugin.hasEnchant(item, plugin.FOOD)) return;
            event.setFoodLevel(20);
        }
    }

    public static class Worm extends Enchantment {

        public Worm(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Worm";
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
            return true;
        }

        static void onSuffocate(Plugin plugin, Player player, ItemStack item, EntityDamageEvent event) {
            if (!Plugin.hasEnchant(item, plugin.WORM)) return;
            event.setCancelled(true);
        }
    }

    public static class Crush extends Enchantment {

        public Crush(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Crush";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onFall(Plugin plugin, Player player, ItemStack item, double fallDamage, ArrayList<LivingEntity> fellOn) {
            final int level = Plugin.getEnchantLevel(item, plugin.CRUSH);
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

        public LifeSteal(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Life Steal";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.LIFE_STEAL);
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

        public Fling(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Fling";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player player, Entity entity, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.FLING);
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

        public Range(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Range";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (plugin == null || player == null || projectile == null || item == null) return;
            final int level = Plugin.getEnchantLevel(item, plugin.RANGE);
            if (level < 1) return;
            projectile.setVelocity(projectile.getVelocity().multiply(1 + (level/5)));
        }
    }

    public static class Accurate extends Enchantment {

        public Accurate(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Precision";
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
            return true;
        }

        static void onPlayerShootProjectile(Plugin plugin, Player player, Projectile projectile, ItemStack item) {
            if (plugin == null || player == null || projectile == null || item == null) return;
            if (!Plugin.hasEnchant(item, plugin.ACCURATE)) return;
            Vector direction = player.getEyeLocation().getDirection();
            Vector velocity = projectile.getVelocity();
            velocity.setX(direction.getX() * velocity.length());
            velocity.setY(direction.getY() * velocity.length());
            velocity.setZ(direction.getZ() * velocity.length());
            projectile.setVelocity(velocity);
        }
    }

    public static class Poison extends Enchantment {

        public Poison(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Venom";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.POISON);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 + (level * 10), level/3), false);
        }
    }

    public static class Wither extends Enchantment {

        public Wither(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Withering";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.WITHER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 + (level * 20), level/5), false);
        }
    }

    public static class Helium extends Enchantment {

        public Helium(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Levitating";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.HELIUM);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, level * 20, 0), false);
        }
    }

    public static class Glowing extends Enchantment {

        public Glowing(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Radiance";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.GLOWING);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, level * 50, 0), false);
        }
    }

    public static class Blindness extends Enchantment {

        public Blindness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Obscure";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.BLINDNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, level * 20, 0), false);
        }
    }

    public static class Confusion extends Enchantment {

        public Confusion(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Disorienting";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.CONFUSION);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 20, 0), false);
        }
    }

    public static class Weakness extends Enchantment {

        public Weakness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Debilitating";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.WEAKNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 + (level * 10), level/4), false);
        }
    }

    public static class Hunger extends Enchantment {

        public Hunger(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Starving";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.HUNGER);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, level * 20, level/2), false);
        }
    }

    public static class Slowness extends Enchantment {

        public Slowness(NamespacedKey key) {
            super(key);
        }

        @Override
        public String getName() {
            return "Crippling";
        }

        @Override
        public int getMaxLevel() {
            return 10;
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
            return true;
        }

        static void onPlayerAttackEntity(Plugin plugin, Player attacker, LivingEntity reciever, ItemStack weapon, double damage, boolean ranged) {
            final int level = Plugin.getEnchantLevel(weapon, plugin.SLOWNESS);
            if (level < 1) return;
            reciever.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 20, level/2), false);
        }
    }
}