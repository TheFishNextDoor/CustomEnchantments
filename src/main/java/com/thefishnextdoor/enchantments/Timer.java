package com.thefishnextdoor.enchantments;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.enchantments.CurseOfAquaphobia;
import com.thefishnextdoor.enchantments.enchantments.CurseOfLevitating;
import com.thefishnextdoor.enchantments.enchantments.CurseOfMiningFatigue;
import com.thefishnextdoor.enchantments.enchantments.CurseOfRadiance;
import com.thefishnextdoor.enchantments.enchantments.CurseOfSlowness;
import com.thefishnextdoor.enchantments.enchantments.CurseOfWeakness;
import com.thefishnextdoor.enchantments.enchantments.Invisibility;
import com.thefishnextdoor.enchantments.enchantments.Spurs;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Anchor;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Leaping;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.SlowFalling;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DragonScales;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.FireResistance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Haste;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Healing;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.HeroOfTheVillage;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.IncreasedHealth;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Strength;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.ConduitPower;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Gills;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.NightVision;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.DolphinsGrace;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.Swiftness;

public class Timer {
    private static Plugin plugin = null;
    private static int armorEffectsTaskId = -1;
    private static int curseOfAquaphobiaTaskId = -1;

    public static class ArmorCheckOptimizer {
        public final ItemStack HELMET;
        public final ItemStack CHESTPLATE;
        public final ItemStack LEGGINGS;
        public final ItemStack BOOTS;
        public final boolean CHECK_HELMET;
        public final boolean CHECK_CHESTPLATE;
        public final boolean CHECK_LEGGINGS;
        public final boolean CHECK_BOOTS;

        public ArmorCheckOptimizer(Player player) {
            HELMET = player.getInventory().getHelmet();
            CHESTPLATE = player.getInventory().getChestplate();
            LEGGINGS = player.getInventory().getLeggings();
            BOOTS = player.getInventory().getBoots();
            CHECK_HELMET = couldHaveCustomEnchantment(HELMET);
            CHECK_CHESTPLATE = couldHaveCustomEnchantment(CHESTPLATE);
            CHECK_LEGGINGS = couldHaveCustomEnchantment(LEGGINGS);
            CHECK_BOOTS = couldHaveCustomEnchantment(BOOTS);
        }
    }

    public static void start(final Plugin p) {
        plugin = p;
        if (armorEffectsTaskId == -1) armorEffectsTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                while (players.hasNext()) {
                    Player player = players.next();
                    ArmorCheckOptimizer o = new ArmorCheckOptimizer(player);
                    if (! o.CHECK_HELMET && !o.CHECK_CHESTPLATE && !o.CHECK_LEGGINGS && !o.CHECK_BOOTS) continue;
                    CurseOfRadiance.onTimer(player, o);
                    CurseOfMiningFatigue.onTimer(player, o);
                    CurseOfSlowness.onTimer(player, o);
                    CurseOfWeakness.onTimer(player, o);
                    CurseOfLevitating.onTimer(player, o);
                    Invisibility.onTimer(player, o);
                    if (o.CHECK_HELMET) {
                        Gills.onTimer(player, o.HELMET);
                        NightVision.onTimer(player, o.HELMET);
                        ConduitPower.onTimer(player, o.HELMET);
                    }
                    if (o.CHECK_CHESTPLATE) {
                        DragonScales.onTimer(player, o.CHESTPLATE);
                        Healing.onTimer(player, o.CHESTPLATE);
                        IncreasedHealth.onTimer(player, o.CHESTPLATE);
                        Strength.onTimer(player, o.CHESTPLATE);
                        Haste.onTimer(player, o.CHESTPLATE);
                        HeroOfTheVillage.onTimer(player, o.CHESTPLATE);
                        FireResistance.onTimer(player, o.CHESTPLATE);
                    }
                    if (o.CHECK_LEGGINGS) {
                        DolphinsGrace.onTimer(player, o.LEGGINGS);
                        Swiftness.onTimer(player, o.LEGGINGS);
                    }
                    if (o.CHECK_BOOTS) {
                        Anchor.onTimer(player, o.BOOTS);
                        SlowFalling.onTimer(player, o.BOOTS);
                        Spurs.onTimer(player, o.BOOTS);
                        Leaping.onTimer(player, o.BOOTS);
                    }
                }
            }
        }, 0, Settings.ARMOR_EFFECTS_PERIOD_TICKS);
        if (curseOfAquaphobiaTaskId == -1) curseOfAquaphobiaTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                while (players.hasNext()) {
                    Player player = players.next();
                    CurseOfAquaphobia.onTimer(player, new ArmorCheckOptimizer(player));
                }
            }
        }, 40, 40);
    }

    public static void stop() {
        if (armorEffectsTaskId != -1) {
            Bukkit.getScheduler().cancelTask(armorEffectsTaskId);
            armorEffectsTaskId = -1;
        }
        if (curseOfAquaphobiaTaskId != -1) {
            Bukkit.getScheduler().cancelTask(curseOfAquaphobiaTaskId);
            curseOfAquaphobiaTaskId = -1;
        }
    }

    public static void reload() {
        stop();
        if (plugin != null) start(plugin);
    }

    private static boolean couldHaveCustomEnchantment (ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().hasLore();
    }
}
