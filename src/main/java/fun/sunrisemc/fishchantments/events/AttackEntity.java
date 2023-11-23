package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.Utl;
import fun.sunrisemc.fishchantments.enchantments.Generic.AquaAspect;
import fun.sunrisemc.fishchantments.enchantments.Generic.Fling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Radiance;
import fun.sunrisemc.fishchantments.enchantments.Generic.Reflection;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Obscure;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.DeathWish;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.BloodTipped;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Disorienting;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Glass;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Levitating;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Starving;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.LifeSteal;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Venom;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Crippling;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Debilitating;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Withering;

public class AttackEntity implements Listener {
    Plugin plugin;

    public AttackEntity(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof LivingEntity)) return;
        LivingEntity reciever = (LivingEntity) event.getEntity(); 
        double damage = event.getFinalDamage();
        Entity damager = event.getDamager();
        boolean RANGED_ATTACK = false;
        if (damager instanceof Projectile) {
            RANGED_ATTACK = true;
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Entity) damager = (Entity) shooter;
            if (reciever instanceof Player) {
                Player player = (Player) reciever;
                if (player.isBlocking() && damage == 0) Reflection.onDeflectProjectile(plugin, player, projectile);
            }
        }
        if (!(damage > 0)) return;
        if (!(damager instanceof Player)) return;
        Player player = (Player) damager;
        if (!RANGED_ATTACK && Utl.Mtrl.isRanged(Utl.Nvntry.getItemInUse(player).getType())) return;

        // Event modifiers
        DeathWish.onPlayerAttackEntity(plugin, player, damage, event);
        Glass.onPlayerAttackEntity(plugin, player, damage, event, RANGED_ATTACK);
        AquaAspect.onPlayerAttackEntity(plugin, player, reciever, event, RANGED_ATTACK);
        LifeSteal.onPlayerAttackEntity(plugin, player, damage, RANGED_ATTACK);

        Fling.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Venom.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Withering.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        BloodTipped.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Levitating.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Radiance.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Obscure.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Disorienting.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Debilitating.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Starving.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
        Crippling.onPlayerAttackEntity(plugin, player, reciever, RANGED_ATTACK);
    }   
}
