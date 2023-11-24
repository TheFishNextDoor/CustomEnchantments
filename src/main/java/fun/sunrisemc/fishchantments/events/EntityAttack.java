package fun.sunrisemc.fishchantments.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fun.sunrisemc.fishchantments.Plugin;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Flaming;

public class EntityAttack implements Listener {
    Plugin plugin;

    public EntityAttack(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        double damage = event.getFinalDamage();
        if (!(damage > 0)) return;
        Entity damager = event.getDamager();
        if (!(damager instanceof LivingEntity)) return;
        LivingEntity attacker = (LivingEntity) damager;
        boolean melee = event.getCause() == DamageCause.ENTITY_ATTACK;
        Flaming.onEntityAttackPlayer(plugin, player, attacker, melee);
    }   
}