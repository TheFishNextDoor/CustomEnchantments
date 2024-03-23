package com.thefishnextdoor.customenchantments.enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.CustomEnchantments;
import com.thefishnextdoor.customenchantments.enchantment.impl.AquaAspect;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfAquaphobia;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfIronGrip;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfLevitating;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfMiningFatigue;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfRadiance;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfSlowness;
import com.thefishnextdoor.customenchantments.enchantment.impl.CurseOfWeakness;
import com.thefishnextdoor.customenchantments.enchantment.impl.Enlightenment;
import com.thefishnextdoor.customenchantments.enchantment.impl.Excavating;
import com.thefishnextdoor.customenchantments.enchantment.impl.Fling;
import com.thefishnextdoor.customenchantments.enchantment.impl.Invisibility;
import com.thefishnextdoor.customenchantments.enchantment.impl.Precision;
import com.thefishnextdoor.customenchantments.enchantment.impl.Radiance;
import com.thefishnextdoor.customenchantments.enchantment.impl.Range;
import com.thefishnextdoor.customenchantments.enchantment.impl.Reflection;
import com.thefishnextdoor.customenchantments.enchantment.impl.Replanting;
import com.thefishnextdoor.customenchantments.enchantment.impl.Smelting;
import com.thefishnextdoor.customenchantments.enchantment.impl.Spurs;
import com.thefishnextdoor.customenchantments.enchantment.impl.Telekinesis;
import com.thefishnextdoor.customenchantments.enchantment.impl.Tilling;
import com.thefishnextdoor.customenchantments.enchantment.impl.TreeFeller;
import com.thefishnextdoor.customenchantments.enchantment.impl.Unbreakable;
import com.thefishnextdoor.customenchantments.enchantment.impl.Volley;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots.Anchor;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots.Bounce;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots.Crush;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots.Leaping;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.boots.SlowFalling;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.DragonScales;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.FireResistance;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.Flaming;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.Haste;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.Healing;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.HeroOfTheVillage;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.IncreasedHealth;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.chestplate.Strength;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.elytra.Boosters;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.elytra.Momentum;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet.ConduitPower;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet.Gills;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet.NightVision;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet.Sustenance;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.helmet.Worm;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings.DolphinsGrace;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings.Heavy;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.leggings.Swiftness;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Crippling;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Debilitating;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Destructive;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Disorienting;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.FireBlast;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Glass;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Harpoon;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Levitating;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Obscure;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Starving;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Teleport;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Venom;
import com.thefishnextdoor.customenchantments.enchantment.impl.exclusive.weapon.Withering;
import com.thefishnextdoor.customenchantments.nms.NMS;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

import net.md_5.bungee.api.ChatColor;

public abstract class CustomEnchantment {

    // ------------------- //
    // Custom Enchantments //
    // ------------------- //
    
    // General Enchantments
    public static Enchantment AQUA_ASPECT;
    public static Enchantment CURSE_OF_AQUAPHOBIA;
    public static Enchantment CURSE_OF_IRON_GRIP;
    public static Enchantment CURSE_OF_LEVITATING;
    public static Enchantment CURSE_OF_MINING_FATIGUE;
    public static Enchantment CURSE_OF_RADIANCE;
    public static Enchantment CURSE_OF_SLOWNESS;
    public static Enchantment CURSE_OF_WEAKNESS;
    public static Enchantment ENLIGHTENMENT;
    public static Enchantment EXCAVATING;
    public static Enchantment FLING;
    public static Enchantment INVISIBILITY;
    public static Enchantment PRECISION;
    public static Enchantment RADIANCE;
    public static Enchantment RANGE;
    public static Enchantment REFLECTION;
    public static Enchantment REPLANTING;
    public static Enchantment SMELTING;
    public static Enchantment SPURS;
    public static Enchantment TELEKINESIS;
    public static Enchantment TILLING;
    public static Enchantment TREE_FELLER;
    public static Enchantment UNBREAKABLE;
    public static Enchantment VOLLEY;

    // Mutually Exclusive Weapon Enchantments
    public static Enchantment BLOOD_TIPPED;
    public static Enchantment CRIPPLING;
    public static Enchantment DEBILITATING;
    public static Enchantment DESTRUCTIVE;
    public static Enchantment DISORIENTING;
    public static Enchantment FIRE_BLAST;
    public static Enchantment GLASS;
    public static Enchantment HARPOON;
    public static Enchantment LEVITATING;
    public static Enchantment LIFE_STEAL;
    public static Enchantment OBSCURE;
    public static Enchantment SALMON_SLINGER;
    public static Enchantment SEEKING;
    public static Enchantment STARVING;
    public static Enchantment TELEPORT;
    public static Enchantment VENOM;
    public static Enchantment WITHERING;

    // Mutually Exclusive Helmet Enchantments
    public static Enchantment CONDUIT_POWER;
    public static Enchantment GILLS;
    public static Enchantment NIGHT_VISION;
    public static Enchantment SUSTENANCE;
    public static Enchantment WORM;

    // Mutually Exclusive Chestplate Enchantments
    public static Enchantment DEATH_WISH;
    public static Enchantment DRAGON_SCALES;
    public static Enchantment FIRE_RESISTANCE;
    public static Enchantment FLAMING;
    public static Enchantment HASTE;
    public static Enchantment HEALING;
    public static Enchantment HERO_OF_THE_VILLAGE;
    public static Enchantment INCREASED_HEALTH;
    public static Enchantment PROJECTILE_RESISTANCE;
    public static Enchantment STRENGTH;

    // Mutually Exclusive Elytra Enchantments
    public static Enchantment BOOSTERS;
    public static Enchantment MOMENTUM;

    // Mutually Exclusive Leggings Enchantments
    public static Enchantment DOLPHINS_GRACE;
    public static Enchantment HEAVY;
    public static Enchantment SWIFTNESS;

    // Mutually Exclusive Boots Enchantments
    public static Enchantment ANCHOR;
    public static Enchantment BOUNCE;
    public static Enchantment CRUSH;
    public static Enchantment LEAPING;
    public static Enchantment SLOW_FALLING;

    // ---------------- //
    // Static Variables //
    // ---------------- //

    private static HashMap<String, CustomEnchantment> customEnchantmentLookup = new HashMap<>();

    // -------- //
    // Instance //
    // -------- //

    private NamespacedKey key;

    public CustomEnchantment() {
        this.key = NamespacedKey.minecraft(getName().toLowerCase().replace(" ", "_"));

        if (!customEnchantmentLookup.containsKey(key.toString())) {
            customEnchantmentLookup.put(key.toString(), this);
        }
    }

    public NamespacedKey getKey() {
        return key;
    }

    public int getStartLevel() {
        return 1;
    }

    public boolean isTreasure() {
        return true;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract int getMaxLevel();

    public abstract EnchantmentTarget getItemTarget();

    public abstract boolean isCursed();

    public abstract boolean conflictsWith(Enchantment other);

    public abstract boolean canEnchantItem(ItemStack item);

    // -------------- //
    // Static Methods //
    // -------------- //

    public static void registerAll(CustomEnchantments plugin) {
        NMS.unfreezeEnchantmentRegistry();
        AQUA_ASPECT = NMS.registerEnchantment(new AquaAspect());
        CURSE_OF_AQUAPHOBIA = NMS.registerEnchantment(new CurseOfAquaphobia());
        CURSE_OF_IRON_GRIP = NMS.registerEnchantment(new CurseOfIronGrip());
        CURSE_OF_LEVITATING = NMS.registerEnchantment(new CurseOfLevitating());
        CURSE_OF_MINING_FATIGUE = NMS.registerEnchantment(new CurseOfMiningFatigue());
        CURSE_OF_RADIANCE = NMS.registerEnchantment(new CurseOfRadiance());
        CURSE_OF_SLOWNESS = NMS.registerEnchantment(new CurseOfSlowness());
        CURSE_OF_WEAKNESS = NMS.registerEnchantment(new CurseOfWeakness());
        ENLIGHTENMENT = NMS.registerEnchantment(new Enlightenment());
        EXCAVATING = NMS.registerEnchantment(new Excavating());
        FLING = NMS.registerEnchantment(new Fling());
        INVISIBILITY = NMS.registerEnchantment(new Invisibility());
        PRECISION = NMS.registerEnchantment(new Precision());
        RADIANCE = NMS.registerEnchantment(new Radiance());
        RANGE = NMS.registerEnchantment(new Range());
        REFLECTION = NMS.registerEnchantment(new Reflection());
        REPLANTING = NMS.registerEnchantment(new Replanting());
        SMELTING = NMS.registerEnchantment(new Smelting());
        SPURS = NMS.registerEnchantment(new Spurs());
        TELEKINESIS = NMS.registerEnchantment(new Telekinesis());
        TILLING = NMS.registerEnchantment(new Tilling());
        TREE_FELLER = NMS.registerEnchantment(new TreeFeller());
        UNBREAKABLE = NMS.registerEnchantment(new Unbreakable());
        CONDUIT_POWER = NMS.registerEnchantment(new ConduitPower());
        GILLS = NMS.registerEnchantment(new Gills());
        NIGHT_VISION = NMS.registerEnchantment(new NightVision());
        SUSTENANCE = NMS.registerEnchantment(new Sustenance());
        WORM = NMS.registerEnchantment(new Worm());
        DEATH_WISH = NMS.registerEnchantment(new DeathWish());
        DRAGON_SCALES = NMS.registerEnchantment(new DragonScales());
        FIRE_RESISTANCE = NMS.registerEnchantment(new FireResistance());
        FLAMING = NMS.registerEnchantment(new Flaming());
        HASTE = NMS.registerEnchantment(new Haste());
        HEALING = NMS.registerEnchantment(new Healing());
        HERO_OF_THE_VILLAGE = NMS.registerEnchantment(new HeroOfTheVillage());
        INCREASED_HEALTH = NMS.registerEnchantment(new IncreasedHealth());
        PROJECTILE_RESISTANCE = NMS.registerEnchantment(new ProjectileResistance());
        STRENGTH = NMS.registerEnchantment(new Strength());
        BOOSTERS = NMS.registerEnchantment(new Boosters());
        MOMENTUM = NMS.registerEnchantment(new Momentum());
        DOLPHINS_GRACE = NMS.registerEnchantment(new DolphinsGrace());
        HEAVY = NMS.registerEnchantment(new Heavy());
        SWIFTNESS = NMS.registerEnchantment(new Swiftness());
        BLOOD_TIPPED = NMS.registerEnchantment(new BloodTipped());
        CRIPPLING = NMS.registerEnchantment(new Crippling());
        DEBILITATING = NMS.registerEnchantment(new Debilitating());
        DESTRUCTIVE = NMS.registerEnchantment(new Destructive());
        DISORIENTING = NMS.registerEnchantment(new Disorienting());
        FIRE_BLAST = NMS.registerEnchantment(new FireBlast());
        GLASS = NMS.registerEnchantment(new Glass());
        HARPOON = NMS.registerEnchantment(new Harpoon());
        LEVITATING = NMS.registerEnchantment(new Levitating());
        LIFE_STEAL = NMS.registerEnchantment(new LifeSteal());
        OBSCURE = NMS.registerEnchantment(new Obscure());
        SALMON_SLINGER = NMS.registerEnchantment(new SalmonSlinger());
        SEEKING = NMS.registerEnchantment(new Seeking());
        STARVING = NMS.registerEnchantment(new Starving());
        TELEPORT = NMS.registerEnchantment(new Teleport());
        VENOM = NMS.registerEnchantment(new Venom());
        WITHERING = NMS.registerEnchantment(new Withering());
        ANCHOR = NMS.registerEnchantment(new Anchor());
        BOUNCE = NMS.registerEnchantment(new Bounce());
        CRUSH = NMS.registerEnchantment(new Crush());
        LEAPING = NMS.registerEnchantment(new Leaping());
        SLOW_FALLING = NMS.registerEnchantment(new SlowFalling());
        VOLLEY = NMS.registerEnchantment(new Volley());
    }

    public static ArrayList<Enchantment> all() {
        ArrayList<Enchantment> customEnchantments = new ArrayList<>();
        Iterator<Enchantment> allEnchantments = Registry.ENCHANTMENT.iterator();
        while (allEnchantments.hasNext()) {
            Enchantment enchantment = allEnchantments.next();
            if (isCustomEnchantment(enchantment)) {
                customEnchantments.add(enchantment);
            }
        }
        return customEnchantments;
    }

    public static CustomEnchantment unWrap(Enchantment enchantment) {
        if (enchantment == null) {
            return null;
        }
        return customEnchantmentLookup.get(enchantment.getKey().toString());
    }

    public static boolean isCustomEnchantment(Enchantment enchantment) {
        // return enchantment instanceof CustomEnchantment; // Not reload safe
        if (enchantment == null) {
            return false;
        }
        return customEnchantmentLookup.containsKey(enchantment.getKey().toString());
    }

    public static boolean hasCustomEnchantments(ItemStack item) {
        for (Enchantment enchantment : EnchantTools.enchantments(item).keySet()) {
            if (isCustomEnchantment(enchantment)) {
                return true;
            }
        }
        return false;
    }

    public static HashMap<Enchantment, Integer> customEnchantments(ItemStack item) {
        HashMap<Enchantment, Integer>  foundFishchantments = new HashMap<>();
        for (Entry<Enchantment, Integer> entry : EnchantTools.enchantments(item).entrySet()) {
            if (isCustomEnchantment(entry.getKey())) {
                foundFishchantments.put(entry.getKey(), entry.getValue());
            }
        }
        return foundFishchantments;
    }

    public static String leveledLoreString(CustomEnchantment customEnchantment, Integer level) {
        String lore = nonLeveledLoreString(customEnchantment);
        if (level == 1 && customEnchantment.getMaxLevel() == 1) {
            return lore;
        }
        else {
            return lore + " " + (CustomEnchantments.getSettings().USE_ARABIC_NUMERALS ? level.toString() : EnchantTools.numeral(level));
        }
    }

    public static String nonLeveledLoreString(CustomEnchantment customEnchantment) {
        return customEnchantment.isCursed() ? ChatColor.RED + customEnchantment.getName() : ChatColor.GRAY + customEnchantment.getName();
    }
}
