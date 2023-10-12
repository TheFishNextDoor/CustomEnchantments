package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Crush;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Destructive;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Fling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Food;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glowing;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Replanting;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Tilling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Worm;
import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
    private static final boolean ALLOW_EDIT = true;
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
        ItemStack item = Plugin.getItemInHand(player);
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
        ItemStack weapon = Plugin.getItemInHand(player);
        Material weaponType = weapon.getType();
        if (!RANGED_ATTACK && Plugin.isRangedWeapon(weaponType) && !Plugin.isMeleeWeapon(weaponType)) return;
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
        ItemStack item = Plugin.getItemInHand(player);
        Range.onPlayerShootProjectile(plugin, player, projectile, item);
        Accurate.onPlayerShootProjectile(plugin, player, projectile, item);
    }

    @EventHandler
    public void onTill(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (clickedBlock == null || event.getItem() == null) return;
        Tilling.onTill(plugin, event.getPlayer(), Plugin.getItemInHand(event.getPlayer()), clickedBlock);
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
        ItemStack item = Plugin.getItemInHand(player);
        if (item == null) return;
        Replanting.onRightClick(plugin, player, item, clickedBlock);
    }

    @EventHandler
    public void onHarvestCrop(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player == null || block == null) return;
        ItemStack item = Plugin.getItemInHand(player);
        if (item == null) return;
        Replanting.onBlockBreak(plugin, player, item, block, event);
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
        ItemStack zero = event.getInventory().getItem(0);
        ItemStack one = event.getInventory().getItem(1);
        if (!(plugin.hasFishchantment(zero) || plugin.hasFishchantment(one))) return;
        if (!ALLOW_EDIT) return;
        ItemStack result = event.getResult();
        if (plugin.hasFishchantment(result)) return; // Don't need to fix
        ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
        fishchantments.addAll(plugin.getFishchantments(one));
        for (int i = 0; i < fishchantments.size(); i++) {
            Enchantment enchantment = fishchantments.get(i);
            int zeroLevel = Plugin.getEnchantLevel(zero, enchantment);
            int oneLevel = Plugin.getEnchantLevel(one, enchantment);
            int level = zeroLevel > oneLevel ? zeroLevel : oneLevel;
            plugin.addEnchant(result, enchantment, level, false, true);
        }
        event.setResult(result);
    }

    @EventHandler
    public void onAnvilComplete(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getInventory() instanceof AnvilInventory)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        AnvilInventory anvil = (AnvilInventory) event.getInventory();
        Player player = (Player) event.getWhoClicked();
        if (event.getSlotType() != SlotType.RESULT) return;
        ItemStack result = anvil.getItem(event.getRawSlot());
        ItemStack zero = anvil.getItem(0);
        ItemStack one = anvil.getItem(1);
        if (!(plugin.hasFishchantment(zero) || plugin.hasFishchantment(one))) return;
        if (plugin.hasFishchantment(result)) return; // Don't need to fix
        if (ALLOW_EDIT) {
            ArrayList<Enchantment> fishchantments = plugin.getFishchantments(zero);
            fishchantments.addAll(plugin.getFishchantments(one));
            for (int i = 0; i < fishchantments.size(); i++) {
                Enchantment enchantment = fishchantments.get(i);
                int zeroLevel = Plugin.getEnchantLevel(zero, enchantment);
                int oneLevel = Plugin.getEnchantLevel(one, enchantment);
                int level = zeroLevel > oneLevel ? zeroLevel : oneLevel;
                plugin.addEnchant(result, enchantment, level, false, true);
            }
        }
        else {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot edit items that have custom enchants.");
        }
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
}
