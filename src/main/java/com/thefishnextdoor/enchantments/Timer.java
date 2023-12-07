package com.thefishnextdoor.enchantments;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfAquaphobia;
import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfLevitating;
import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfMiningFatigue;
import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfRadiance;
import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfSlowness;
import com.thefishnextdoor.enchantments.definitions.Curses.CurseOfWeakness;
import com.thefishnextdoor.enchantments.definitions.Generic.Invisibility;
import com.thefishnextdoor.enchantments.definitions.Generic.Spurs;
import com.thefishnextdoor.enchantments.definitions.specialties.Boot.Anchor;
import com.thefishnextdoor.enchantments.definitions.specialties.Boot.Leaping;
import com.thefishnextdoor.enchantments.definitions.specialties.Boot.SlowFalling;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.DragonScales;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.FireResistance;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.Haste;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.Healing;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.HeroOfTheVillage;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.IncreasedHealth;
import com.thefishnextdoor.enchantments.definitions.specialties.Chestplate.Strength;
import com.thefishnextdoor.enchantments.definitions.specialties.Helmet.ConduitPower;
import com.thefishnextdoor.enchantments.definitions.specialties.Helmet.Gills;
import com.thefishnextdoor.enchantments.definitions.specialties.Helmet.NightVision;
import com.thefishnextdoor.enchantments.definitions.specialties.Legging.DolphinsGrace;
import com.thefishnextdoor.enchantments.definitions.specialties.Legging.Swiftness;

public class Timer {
    private static Plugin plugin = null;
    private static int armorEffectsTaskId = -1;
    private static int curseOfAquaphobiaTaskId = -1;

    public static void start(final Plugin p) {
        plugin = p;
        if (armorEffectsTaskId == -1) armorEffectsTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                while (players.hasNext()) {
                    Player player = players.next();
                    ItemStack helmet = player.getInventory().getHelmet();
                    ItemStack chestplate = player.getInventory().getChestplate();
                    ItemStack leggings = player.getInventory().getLeggings();
                    ItemStack boots = player.getInventory().getBoots();
                    CurseOfRadiance.onTimer(player);
                    CurseOfMiningFatigue.onTimer(player);
                    CurseOfSlowness.onTimer(player);
                    CurseOfWeakness.onTimer(player);
                    CurseOfLevitating.onTimer(player);
                    Invisibility.onTimer(player);
                    Gills.onTimer(player, helmet);
                    NightVision.onTimer(player, helmet);
                    ConduitPower.onTimer(player, helmet);
                    DragonScales.onTimer(player, chestplate);
                    Healing.onTimer(player, chestplate);
                    IncreasedHealth.onTimer(player, chestplate);
                    Strength.onTimer(player, chestplate);
                    Haste.onTimer(player, chestplate);
                    HeroOfTheVillage.onTimer(player, chestplate);
                    FireResistance.onTimer(player, chestplate);
                    DolphinsGrace.onTimer(player, leggings);
                    Swiftness.onTimer(player, leggings);
                    Anchor.onTimer(player, boots);
                    SlowFalling.onTimer(player, boots);
                    Spurs.onTimer(player, boots);
                    Leaping.onTimer(player, boots);
                }
            }
        }, Settings.ARMOR_EFFECTS_PERIOD_TICKS, Settings.ARMOR_EFFECTS_PERIOD_TICKS);
        if (curseOfAquaphobiaTaskId == -1) curseOfAquaphobiaTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                Player player;
                while (players.hasNext()) {
                    player = players.next();
                    CurseOfAquaphobia.onTimer(player);
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
}
