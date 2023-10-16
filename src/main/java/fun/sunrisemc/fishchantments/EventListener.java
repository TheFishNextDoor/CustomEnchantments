package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Enchantments.Generic.Accurate;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Excavating;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Fling;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Replanting;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Tilling;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Unbreakable;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Range;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Boot.Crush;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Helmet.Food;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Helmet.Worm;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Blindness;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Confusion;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Helium;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Hunger;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.LifeSteal;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Poison;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Slowness;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Weakness;
import fun.sunrisemc.fishchantments.Enchantments.Specialties.Weapon.Wither;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Glowing;
import fun.sunrisemc.fishchantments.Enchantments.Generic.Destructive;

public class EventListener implements Listener {
    private static final boolean ALLOW_EDIT = true;
    private static final boolean DROP_BOOKS = true;
    private final Plugin plugin;

    EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArrowHitBlock(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        ItemStack item = Utl.getItemInHand(player);
        Destructive.onArrowHitBlock(plugin, player, projectile, item, event.getHitBlock());
        Tilling.onArrowHitBlock(plugin, player, projectile, item, event.getHitBlock());
    }

    @EventHandler
    public void onPlayerAttackEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity damager = event.getDamager();
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) { // Include ranged player attacks
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player) damager = (Player) shooter;
        }
        if (!(damager instanceof Player && event.getEntity() instanceof LivingEntity)) return;
        Player player = (Player) damager;
        LivingEntity entity = (LivingEntity) event.getEntity();
        ItemStack weapon = Utl.getItemInHand(player);
        Material weaponType = weapon.getType();
        if (!RANGED_ATTACK && Utl.Mat.isRangedWeapon(weaponType) && !Utl.Mat.isMeleeWeapon(weaponType)) return;
        final double damage = event.getDamage();
        if (plugin == null || player == null || entity == null || weapon == null || damage == 0) return;
        LifeSteal.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Fling.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Poison.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Wither.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Helium.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Glowing.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Blindness.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Confusion.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Weakness.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Hunger.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
        Slowness.onPlayerAttackEntity(plugin, player, entity, weapon, damage, RANGED_ATTACK);
    }

    @EventHandler
    public void onItemTakeDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        Unbreakable.onItemTakeDamage(plugin, player, item, event);
    }

    @EventHandler
    public void onPlayerShootProjectile(ProjectileLaunchEvent event) {
        if (event.isCancelled()) return;
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = projectile.getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        ItemStack item = Utl.getItemInHand(player);
        Range.onPlayerShootProjectile(plugin, player, projectile, item);
        Accurate.onPlayerShootProjectile(plugin, player, projectile, item);
    }

    @EventHandler
    public void onTill(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (clickedBlock == null || event.getItem() == null) return;
        Tilling.onTill(plugin, event.getPlayer(), Utl.getItemInHand(event.getPlayer()), clickedBlock);
    }

    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null) return;
        Food.onHungerLoss(plugin, player, helmet, event);
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        Entity damagedEntity = event.getEntity();
        if (!(damagedEntity instanceof Player)) return;
        Player player = (Player) damagedEntity;
        ItemStack boots = player.getInventory().getBoots();
        if (boots == null) return;
        final double fallDamage = event.getDamage();
        ArrayList<LivingEntity> nearbyMobs = new ArrayList<>();
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity instanceof LivingEntity) nearbyMobs.add((LivingEntity) entity);
        }
        Crush.onFall(plugin, player, boots, fallDamage, nearbyMobs);
    }

    @EventHandler
    public void onRightClickCrop(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK) return;
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;
        ItemStack item = Utl.getItemInHand(player);
        if (item == null) return;
        Replanting.onRightClick(plugin, player, item, clickedBlock);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!Utl.PrmChkr.isReal(event)) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        ItemStack item = Utl.getItemInHand(player);
        if (item == null) return;
        Replanting.onBlockBreak(plugin, player, item, block, event);
        Excavating.onBlockBreak(plugin, player, item, block, event);
    }

    @EventHandler
    public void onSuffocation(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helmet = player.getInventory().getHelmet();
        if (helmet == null) return;
        Worm.onSuffocate(plugin, player, helmet, event);
    }

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event) {
        ItemStack result = event.getResult();
        ItemStack zero = event.getInventory().getItem(0);
        ItemStack one = event.getInventory().getItem(1);
        if (!(plugin.hasFishchantment(zero) || plugin.hasFishchantment(one))) return; // Don't worry about vanilla stuff
        if (result == null && zero != null && one != null) result = zero.clone(); // Make item always show up in result slot
        if (!plugin.canMerge(zero, one)) {
            event.setResult(null);
            return;
        }
        if (!ALLOW_EDIT) return;
        ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            int level = Utl.Ench.getEnchantLevel(zero, enchantment);
            plugin.addEnchant(result, enchantment, level, true, false);
        }
        fishchantments = plugin.getFishchantments(one);
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            int level = Utl.Ench.getEnchantLevel(one, enchantment);
            plugin.addEnchant(result, enchantment, level, false, true);
        }
        event.setResult(result);
    }

    @EventHandler
    public void onGrindstoneComplete(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getInventory() instanceof GrindstoneInventory)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        GrindstoneInventory grindstone = (GrindstoneInventory) event.getInventory();
        if (event.getSlotType() != SlotType.RESULT) return;
        ItemStack result = grindstone.getItem(event.getRawSlot());
        ItemStack zero = grindstone.getItem(0);
        ItemStack one = grindstone.getItem(1);
        if (!(plugin.hasFishchantment(zero) || plugin.hasFishchantment(one))) return;
        ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
        fishchantments.addAll(plugin.getFishchantments(one));
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            plugin.removeEnchant(result, enchantment);
        }
    }

    @EventHandler
    public void addMobLoot(EntityDeathEvent event) {
        if (!DROP_BOOKS) return;
        LivingEntity entity = event.getEntity();
        if (entity.getKiller() == null) return;
        EntityType type = entity.getType();
        List<ItemStack> drops = event.getDrops();
        if (type == EntityType.ENDERMAN && Utl.chance(0.03)) drops.add(plugin.enchantedBook(plugin.BLINDNESS, 1));
        if (type == EntityType.CAVE_SPIDER && Utl.chance(0.03)) drops.add(plugin.enchantedBook(plugin.POISON, 1));
        if (type == EntityType.SPIDER && Utl.chance(0.03)) drops.add(plugin.enchantedBook(plugin.NIGHT_VISION, 1));
        if (type == EntityType.MAGMA_CUBE && Utl.chance(0.03)) drops.add(plugin.enchantedBook(plugin.GLOWING, 1));
        if (type == EntityType.ZOMBIE && Utl.chance(0.03)) drops.add(plugin.enchantedBook(plugin.FOOD, 1));
        if (type == EntityType.DROWNED && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.WATER_BREATHING, 1));
        if (type == EntityType.HUSK && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.HUNGER, 1));
        if (type == EntityType.SHULKER && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.HELIUM, 1));
        if (type == EntityType.SILVERFISH && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.WORM, 1));
        if (type == EntityType.BLAZE && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.FIRE_RESISTANCE, 1));
        if (type == EntityType.PILLAGER && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.HERO_OF_THE_VILLAGE, 1));
        if (type == EntityType.GHAST && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.SLOW_FALL, 1));
        if (type == EntityType.PIGLIN && Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.HASTE, 1));
        if (type == EntityType.PIGLIN_BRUTE && Utl.chance(2.0)) drops.add(plugin.enchantedBook(plugin.STRENGTH, 1));
        if (type == EntityType.ILLUSIONER && Utl.chance(3.0)) drops.add(plugin.enchantedBook(plugin.CONFUSION, 1));
        if (type == EntityType.RAVAGER && Utl.chance(8.0)) drops.add(plugin.enchantedBook(plugin.CRUSH, 1));
        if (type == EntityType.CREEPER) {
            Creeper creeper = (Creeper) entity;
            if (creeper.isPowered() && Utl.chance(15.0)) drops.add(plugin.enchantedBook(plugin.DESTRUCTIVE, 1));
        }
        if (type == EntityType.WITCH) {
            if (Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.WEAKNESS, 1));
            if (Utl.chance(0.1)) drops.add(plugin.enchantedBook(plugin.SPEED, 1));
            if (Utl.chance(0.01)) drops.add(plugin.enchantedBook(plugin.INVISIBILITY, 1));
        }
        if (type == EntityType.WITHER) {
            if (Utl.chance(10.0)) drops.add(plugin.enchantedBook(plugin.WITHER, 1));
            if (Utl.chance(2.5)) drops.add(plugin.enchantedBook(plugin.LIFE_STEAL, 1));
        }
        if (type == EntityType.ENDER_DRAGON) {
            if (Utl.chance(10.0)) drops.add(plugin.enchantedBook(plugin.RESISTANCE, 1));
            if (Utl.chance(2.5)) drops.add(plugin.enchantedBook(plugin.HEALTH_BOOST, 1));
        }
        if (type == EntityType.ELDER_GUARDIAN ) {
            if (Utl.chance(60.0)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Utl.chance(40.0)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        }
        if (type == EntityType.GUARDIAN ) {
            if (Utl.chance(0.06)) drops.add(plugin.enchantedBook(plugin.CONDUIT_POWER, 1));
            if (Utl.chance(0.04)) drops.add(plugin.enchantedBook(plugin.DOLPHINS_GRACE, 1));
        }
        if (type == EntityType.SKELETON) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.RANGE, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
        }
        if (type == EntityType.STRAY) {
            for (int i = 0; i < drops.size(); i++) {
                ItemStack drop = drops.get(i);
                if (drop.getType() != Material.BOW) continue;
                if (Utl.chance(3.0)) plugin.addEnchant(drop, plugin.SLOWNESS, 1, false, false);
                if (Utl.chance(1.0)) plugin.addEnchant(drop, plugin.ACCURATE, 1, false, false);
            }
        }
    }
}
