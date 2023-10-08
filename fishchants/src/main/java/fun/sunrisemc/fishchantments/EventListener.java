package fun.sunrisemc.fishchantments;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Destructive;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glow;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;
import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {
    private final Plugin plugin;

    EventListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource shooter = event.getEntity().getShooter();
        if (!(shooter instanceof Player)) return;
        Player player = (Player) shooter;
        Destructive.onArrowHitBlock(plugin, player, projectile, Plugin.getItemInHand(player), event.getHitBlock());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
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
        final boolean RANGED_WEAPON = weaponType == Material.BOW || weaponType == Material.CROSSBOW;
        if (!RANGED_ATTACK && RANGED_WEAPON) return;
        LifeSteal.onPlayerAttackEntity(plugin, player, entity, weapon, event.getDamage());
        Poison.onPlayerAttackEntity(plugin, player, entity, weapon);
        Wither.onPlayerAttackEntity(plugin, player, entity, weapon);
        Helium.onPlayerAttackEntity(plugin, player, entity, weapon);
        Glow.onPlayerAttackEntity(plugin, player, entity, weapon);
        Blindness.onPlayerAttackEntity(plugin, player, entity, weapon);
        Confusion.onPlayerAttackEntity(plugin, player, entity, weapon);
        Weakness.onPlayerAttackEntity(plugin, player, entity, weapon);
        Hunger.onPlayerAttackEntity(plugin, player, entity, weapon);
        Slowness.onPlayerAttackEntity(plugin, player, entity, weapon);
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        if (event.isCancelled()) return;
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        Unbreakable.onItemTakeDamage(plugin, player, item, event);
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
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
    public void disableAnvilEditingOfFishchantments(InventoryClickEvent event) {
        if (!(event.getInventory() instanceof AnvilInventory)) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        AnvilInventory anvil = (AnvilInventory) event.getInventory();
        Player player = (Player) event.getWhoClicked();
        if (event.getSlotType() != SlotType.RESULT) return;
        ItemStack result = anvil.getItem(event.getRawSlot());
        ItemStack zero = anvil.getItem(0);
        ItemStack one = anvil.getItem(1);
        if (!(plugin.hasEnchant(result) || plugin.hasEnchant(zero) || plugin.hasEnchant(one))) return;
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "You cannot edit items that have custom enchants.");
    }
}
