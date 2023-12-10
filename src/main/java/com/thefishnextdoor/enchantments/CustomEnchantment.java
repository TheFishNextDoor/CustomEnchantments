package com.thefishnextdoor.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import com.thefishnextdoor.enchantments.enchantments.AquaAspect;
import com.thefishnextdoor.enchantments.enchantments.CurseOfAquaphobia;
import com.thefishnextdoor.enchantments.enchantments.CurseOfLevitating;
import com.thefishnextdoor.enchantments.enchantments.CurseOfMiningFatigue;
import com.thefishnextdoor.enchantments.enchantments.CurseOfRadiance;
import com.thefishnextdoor.enchantments.enchantments.CurseOfSlowness;
import com.thefishnextdoor.enchantments.enchantments.CurseOfWeakness;
import com.thefishnextdoor.enchantments.enchantments.Destructive;
import com.thefishnextdoor.enchantments.enchantments.Enlightenment;
import com.thefishnextdoor.enchantments.enchantments.Excavating;
import com.thefishnextdoor.enchantments.enchantments.Fling;
import com.thefishnextdoor.enchantments.enchantments.Invisibility;
import com.thefishnextdoor.enchantments.enchantments.Precision;
import com.thefishnextdoor.enchantments.enchantments.Radiance;
import com.thefishnextdoor.enchantments.enchantments.Range;
import com.thefishnextdoor.enchantments.enchantments.Reflection;
import com.thefishnextdoor.enchantments.enchantments.Replanting;
import com.thefishnextdoor.enchantments.enchantments.Smelting;
import com.thefishnextdoor.enchantments.enchantments.Spurs;
import com.thefishnextdoor.enchantments.enchantments.Telekinesis;
import com.thefishnextdoor.enchantments.enchantments.Tilling;
import com.thefishnextdoor.enchantments.enchantments.Unbreakable;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Anchor;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Bounce;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Crush;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.Leaping;
import com.thefishnextdoor.enchantments.enchantments.exclusive.boots.SlowFalling;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.DragonScales;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.FireResistance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Flaming;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Haste;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Healing;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.HeroOfTheVillage;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.IncreasedHealth;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.chestplate.Strength;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Boosters;
import com.thefishnextdoor.enchantments.enchantments.exclusive.elytra.Momentum;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.ConduitPower;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Gills;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.NightVision;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Sustenance;
import com.thefishnextdoor.enchantments.enchantments.exclusive.helmet.Worm;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.DolphinsGrace;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.Heavy;
import com.thefishnextdoor.enchantments.enchantments.exclusive.leggings.Swiftness;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Crippling;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Debilitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Disorienting;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Glass;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Levitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Obscure;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Starving;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Venom;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Volley;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Withering;

public class CustomEnchantment {
    public static Enchantment DESTRUCTIVE;
    public static Enchantment TILLING;
    public static Enchantment REPLANTING;
    public static Enchantment EXCAVATING;
    public static Enchantment SMELTING;
    public static Enchantment TELEKINESIS;
    public static Enchantment UNBREAKABLE;
    public static Enchantment SUSTENANCE;
    public static Enchantment WORM;
    public static Enchantment CRUSH;
    public static Enchantment LIFE_STEAL;
    public static Enchantment FLING;
    public static Enchantment ENLIGHTENMENT;
    public static Enchantment RANGE;
    public static Enchantment PRECISION;
    public static Enchantment VENOM;
    public static Enchantment WITHERING;
    public static Enchantment BLOOD_TIPPED;
    public static Enchantment VOLLEY;
    public static Enchantment GLASS;
    public static Enchantment LEVITATING;
    public static Enchantment RADIANCE;
    public static Enchantment OBSCURE;
    public static Enchantment DISORIENTING;
    public static Enchantment DEBILITATING;
    public static Enchantment STARVING;
    public static Enchantment CRIPPLING;
    public static Enchantment SWIFTNESS;
    public static Enchantment LEAPING;
    public static Enchantment SLOW_FALLING;
    public static Enchantment DRAGON_SCALES;
    public static Enchantment HEALING;
    public static Enchantment INVISIBILITY;
    public static Enchantment MOMENTUM;
    public static Enchantment BOOSTERS;
    public static Enchantment REFLECTION;
    public static Enchantment DEATHWISH;
    public static Enchantment FIRE_RESISTANCE;
    public static Enchantment GILLS;
    public static Enchantment STRENGTH;
    public static Enchantment HASTE;
    public static Enchantment INCREASED_HEALTH;
    public static Enchantment NIGHT_VISION;
    public static Enchantment DOLPHINS_GRACE;
    public static Enchantment CONDUIT_POWER;
    public static Enchantment HERO_OF_THE_VILLAGE;
    public static Enchantment CURSE_OF_MINING_FATIGUE;
    public static Enchantment CURSE_OF_SLOWNESS;
    public static Enchantment CURSE_OF_WEAKNESS;
    public static Enchantment CURSE_OF_LEVITATING;
    public static Enchantment CURSE_OF_AQUAPHOBIA;
    public static Enchantment CURSE_OF_RADIANCE;
    public static Enchantment ANCHOR;
    public static Enchantment SPURS;
    public static Enchantment AQUA_ASPECT;
    public static Enchantment BOUNCE;
    public static Enchantment PROJECTILE_RESISTANCE;
    public static Enchantment HEAVY;
    public static Enchantment FLAMING;

    private static ArrayList<Enchantment> customEnchantments = new ArrayList<>();

    public static ArrayList<Enchantment> all() {
        return new ArrayList<>(customEnchantments);
    }

    public static void registerCustomEnchantment(Enchantment enchant) {
        customEnchantments.add(enchant);
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchant); 
        } 
        catch (Exception e) {
            Plugin.LOGGER.warning("Failed to register " + enchant.getKey().getKey() + ": " + e.getMessage());
        }
    }

    static void init(Plugin plugin) {
        DESTRUCTIVE = new Destructive(new NamespacedKey(plugin, "destructive"));
        TILLING = new Tilling(new NamespacedKey(plugin, "tilling"));
        REPLANTING = new Replanting(new NamespacedKey(plugin, "replanting"));
        EXCAVATING = new Excavating(new NamespacedKey(plugin, "excavating"));
        SMELTING = new Smelting(new NamespacedKey(plugin, "smelting"));
        TELEKINESIS = new Telekinesis(new NamespacedKey(plugin, "telekinesis"));
        UNBREAKABLE = new Unbreakable(new NamespacedKey(plugin, "unbreakable"));
        SUSTENANCE = new Sustenance(new NamespacedKey(plugin, "sustenance"));
        WORM = new Worm(new NamespacedKey(plugin, "worm"));
        CRUSH = new Crush(new NamespacedKey(plugin, "crush"));
        LIFE_STEAL = new LifeSteal(new NamespacedKey(plugin, "life_steal"));
        FLING = new Fling(new NamespacedKey(plugin, "fling"));
        ENLIGHTENMENT = new Enlightenment(new NamespacedKey(plugin, "enlightenment"));
        RANGE = new Range(new NamespacedKey(plugin, "range"));
        PRECISION = new Precision(new NamespacedKey(plugin, "precision"));
        VENOM = new Venom(new NamespacedKey(plugin, "venom"));
        WITHERING = new Withering(new NamespacedKey(plugin, "withering"));
        BLOOD_TIPPED = new BloodTipped(new NamespacedKey(plugin, "blood_tipped"));
        VOLLEY = new Volley(new NamespacedKey(plugin, "volley"));
        GLASS = new Glass(new NamespacedKey(plugin, "glass"));
        LEVITATING = new Levitating(new NamespacedKey(plugin, "levitating"));
        RADIANCE = new Radiance(new NamespacedKey(plugin, "radiance"));
        OBSCURE = new Obscure(new NamespacedKey(plugin, "obscure"));
        DISORIENTING = new Disorienting(new NamespacedKey(plugin, "disorienting"));
        DEBILITATING = new Debilitating(new NamespacedKey(plugin, "debilitating"));
        STARVING = new Starving(new NamespacedKey(plugin, "starving"));
        CRIPPLING = new Crippling(new NamespacedKey(plugin, "crippling"));
        SWIFTNESS = new Swiftness(new NamespacedKey(plugin, "swiftness"));
        LEAPING = new Leaping(new NamespacedKey(plugin, "leaping"));
        SLOW_FALLING = new SlowFalling(new NamespacedKey(plugin, "slow_falling"));
        DRAGON_SCALES = new DragonScales(new NamespacedKey(plugin, "dragon_scales"));
        HEALING = new Healing(new NamespacedKey(plugin, "healing"));
        INVISIBILITY = new Invisibility(new NamespacedKey(plugin, "invisibility"));
        MOMENTUM = new Momentum(new NamespacedKey(plugin, "momentum"));
        BOOSTERS = new Boosters(new NamespacedKey(plugin, "boosters"));
        REFLECTION = new Reflection(new NamespacedKey(plugin, "reflection"));
        DEATHWISH = new DeathWish(new NamespacedKey(plugin, "death_wish"));
        FIRE_RESISTANCE = new FireResistance(new NamespacedKey(plugin, "fire_resistance"));
        GILLS = new Gills(new NamespacedKey(plugin, "gills"));
        STRENGTH = new Strength(new NamespacedKey(plugin, "strength"));
        HASTE = new Haste(new NamespacedKey(plugin, "haste"));
        INCREASED_HEALTH = new IncreasedHealth(new NamespacedKey(plugin, "increased_health"));
        NIGHT_VISION = new NightVision(new NamespacedKey(plugin, "night_vision"));
        DOLPHINS_GRACE = new DolphinsGrace(new NamespacedKey(plugin, "dolphins_grace"));
        CONDUIT_POWER = new ConduitPower(new NamespacedKey(plugin, "conduit_power"));
        HERO_OF_THE_VILLAGE = new HeroOfTheVillage(new NamespacedKey(plugin, "hero_of_the_village"));
        CURSE_OF_MINING_FATIGUE = new CurseOfMiningFatigue(new NamespacedKey(plugin, "curse_of_mining_fatigue"));
        CURSE_OF_SLOWNESS = new CurseOfSlowness(new NamespacedKey(plugin, "curse_of_slowness"));
        CURSE_OF_WEAKNESS = new CurseOfWeakness(new NamespacedKey(plugin, "curse_of_weakness"));
        CURSE_OF_LEVITATING = new CurseOfLevitating(new NamespacedKey(plugin, "curse_of_levitating"));
        CURSE_OF_AQUAPHOBIA = new CurseOfAquaphobia(new NamespacedKey(plugin, "curse_of_aquaphobia"));
        CURSE_OF_RADIANCE = new CurseOfRadiance(new NamespacedKey(plugin, "curse_of_radiance"));
        ANCHOR = new Anchor(new NamespacedKey(plugin, "anchor"));
        SPURS = new Spurs(new NamespacedKey(plugin, "spurs"));
        AQUA_ASPECT = new AquaAspect(new NamespacedKey(plugin, "aqua_aspect"));
        BOUNCE = new Bounce(new NamespacedKey(plugin, "bounce"));
        PROJECTILE_RESISTANCE = new ProjectileResistance(new NamespacedKey(plugin, "projectile_resistance"));
        HEAVY = new Heavy(new NamespacedKey(plugin, "heavy"));
        FLAMING = new Flaming(new NamespacedKey(plugin, "flaming"));
    }

    static void registerAll() {
        registerCustomEnchantment(DESTRUCTIVE);
        registerCustomEnchantment(TILLING);
        registerCustomEnchantment(REPLANTING);
        registerCustomEnchantment(EXCAVATING);
        registerCustomEnchantment(SMELTING);
        registerCustomEnchantment(TELEKINESIS);
        registerCustomEnchantment(UNBREAKABLE);
        registerCustomEnchantment(SUSTENANCE);
        registerCustomEnchantment(WORM);
        registerCustomEnchantment(CRUSH);
        registerCustomEnchantment(LIFE_STEAL);
        registerCustomEnchantment(FLING);
        registerCustomEnchantment(ENLIGHTENMENT);
        registerCustomEnchantment(RANGE);
        registerCustomEnchantment(PRECISION);
        registerCustomEnchantment(VENOM);
        registerCustomEnchantment(WITHERING);
        registerCustomEnchantment(BLOOD_TIPPED);
        registerCustomEnchantment(VOLLEY);
        registerCustomEnchantment(GLASS);
        registerCustomEnchantment(LEVITATING);
        registerCustomEnchantment(RADIANCE);
        registerCustomEnchantment(OBSCURE);
        registerCustomEnchantment(DISORIENTING);
        registerCustomEnchantment(DEBILITATING);
        registerCustomEnchantment(STARVING);
        registerCustomEnchantment(CRIPPLING);
        registerCustomEnchantment(SWIFTNESS);
        registerCustomEnchantment(LEAPING);
        registerCustomEnchantment(SLOW_FALLING);
        registerCustomEnchantment(DRAGON_SCALES);
        registerCustomEnchantment(HEALING);
        registerCustomEnchantment(INVISIBILITY);
        registerCustomEnchantment(MOMENTUM);
        registerCustomEnchantment(BOOSTERS);
        registerCustomEnchantment(REFLECTION);
        registerCustomEnchantment(DEATHWISH);
        registerCustomEnchantment(FIRE_RESISTANCE);
        registerCustomEnchantment(GILLS);
        registerCustomEnchantment(STRENGTH);
        registerCustomEnchantment(HASTE);
        registerCustomEnchantment(INCREASED_HEALTH);
        registerCustomEnchantment(NIGHT_VISION);
        registerCustomEnchantment(DOLPHINS_GRACE);
        registerCustomEnchantment(CONDUIT_POWER);
        registerCustomEnchantment(HERO_OF_THE_VILLAGE);
        registerCustomEnchantment(CURSE_OF_MINING_FATIGUE);
        registerCustomEnchantment(CURSE_OF_SLOWNESS);
        registerCustomEnchantment(CURSE_OF_WEAKNESS);
        registerCustomEnchantment(CURSE_OF_LEVITATING);
        registerCustomEnchantment(CURSE_OF_AQUAPHOBIA);
        registerCustomEnchantment(CURSE_OF_RADIANCE);
        registerCustomEnchantment(ANCHOR);
        registerCustomEnchantment(SPURS);
        registerCustomEnchantment(AQUA_ASPECT);
        registerCustomEnchantment(BOUNCE);
        registerCustomEnchantment(PROJECTILE_RESISTANCE);
        registerCustomEnchantment(HEAVY);
        registerCustomEnchantment(FLAMING);
    }
}