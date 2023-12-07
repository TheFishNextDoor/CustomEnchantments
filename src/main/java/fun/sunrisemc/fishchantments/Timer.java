package fun.sunrisemc.fishchantments;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfAquaphobia;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfLevitating;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfMiningFatigue;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfRadiance;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfSlowness;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfWeakness;
import fun.sunrisemc.fishchantments.enchantments.Generic.Invisibility;
import fun.sunrisemc.fishchantments.enchantments.Generic.Spurs;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Anchor;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Leaping;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.SlowFalling;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.DragonScales;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.FireResistance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Haste;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Healing;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.HeroOfTheVillage;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.IncreasedHealth;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Strength;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.ConduitPower;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Gills;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.NightVision;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.DolphinsGrace;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.Swiftness;

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
                    CurseOfRadiance.onTimer(plugin, player);
                    CurseOfMiningFatigue.onTimer(plugin, player);
                    CurseOfSlowness.onTimer(plugin, player);
                    CurseOfWeakness.onTimer(plugin, player);
                    CurseOfLevitating.onTimer(plugin, player);
                    Invisibility.onTimer(plugin, player);
                    Gills.onTimer(plugin, player, helmet);
                    NightVision.onTimer(plugin, player, helmet);
                    ConduitPower.onTimer(plugin, player, helmet);
                    DragonScales.onTimer(plugin, player, chestplate);
                    Healing.onTimer(plugin, player, chestplate);
                    IncreasedHealth.onTimer(plugin, player, chestplate);
                    Strength.onTimer(plugin, player, chestplate);
                    Haste.onTimer(plugin, player, chestplate);
                    HeroOfTheVillage.onTimer(plugin, player, chestplate);
                    FireResistance.onTimer(plugin, player, chestplate);
                    DolphinsGrace.onTimer(plugin, player, leggings);
                    Swiftness.onTimer(plugin, player, leggings);
                    Anchor.onTimer(plugin, player, boots);
                    SlowFalling.onTimer(plugin, player, boots);
                    Spurs.onTimer(plugin, player, boots);
                    Leaping.onTimer(plugin, player, boots);
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
                    CurseOfAquaphobia.onTimer(plugin, player);
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
