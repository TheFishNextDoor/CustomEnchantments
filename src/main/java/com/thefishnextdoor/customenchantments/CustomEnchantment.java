package com.thefishnextdoor.customenchantments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.customenchantments.enchantment.AquaAspect;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfAquaphobia;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfIronGrip;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfLevitating;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfMiningFatigue;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfRadiance;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfSlowness;
import com.thefishnextdoor.customenchantments.enchantment.CurseOfWeakness;
import com.thefishnextdoor.customenchantments.enchantment.Enlightenment;
import com.thefishnextdoor.customenchantments.enchantment.Excavating;
import com.thefishnextdoor.customenchantments.enchantment.Fling;
import com.thefishnextdoor.customenchantments.enchantment.Invisibility;
import com.thefishnextdoor.customenchantments.enchantment.Precision;
import com.thefishnextdoor.customenchantments.enchantment.Radiance;
import com.thefishnextdoor.customenchantments.enchantment.Range;
import com.thefishnextdoor.customenchantments.enchantment.Reflection;
import com.thefishnextdoor.customenchantments.enchantment.Replanting;
import com.thefishnextdoor.customenchantments.enchantment.Smelting;
import com.thefishnextdoor.customenchantments.enchantment.Spurs;
import com.thefishnextdoor.customenchantments.enchantment.Telekinesis;
import com.thefishnextdoor.customenchantments.enchantment.Tilling;
import com.thefishnextdoor.customenchantments.enchantment.TreeFeller;
import com.thefishnextdoor.customenchantments.enchantment.Unbreakable;
import com.thefishnextdoor.customenchantments.enchantment.Volley;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Anchor;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Bounce;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Crush;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.Leaping;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.boots.SlowFalling;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.DeathWish;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.DragonScales;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.FireResistance;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.Flaming;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.Haste;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.Healing;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.HeroOfTheVillage;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.IncreasedHealth;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.ProjectileResistance;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.chestplate.Strength;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.elytra.Boosters;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.elytra.Momentum;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.ConduitPower;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.Gills;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.NightVision;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.Sustenance;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.helmet.Worm;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.leggings.DolphinsGrace;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.leggings.Heavy;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.leggings.Swiftness;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.BloodTipped;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Crippling;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Debilitating;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Destructive;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Disorienting;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.FireBlast;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Glass;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Harpoon;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Levitating;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Obscure;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Seeking;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Starving;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Teleport;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Venom;
import com.thefishnextdoor.customenchantments.enchantment.exclusive.weapon.Withering;
import com.thefishnextdoor.customenchantments.util.EnchantTools;

public abstract class CustomEnchantment extends Enchantment {
    
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

    private static ArrayList<CustomEnchantment> customEnchantments = new ArrayList<>();
    private static HashSet<String> customEnchantmentLookup = new HashSet<>();
    private static HashMap<String, String> descriptions = new HashMap<>();

    private NamespacedKey key;

    public CustomEnchantment(NamespacedKey key) {
        super();
        this.key = key;
        if (!customEnchantmentLookup.contains(EnchantTools.name(this))) {
            customEnchantments.add(this);
            customEnchantmentLookup.add(EnchantTools.name(this));
        }
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public String getTranslationKey() {
        return getKey().getKey();
    }

    public abstract String getDescription();

    protected static void registerAll(Plugin plugin) {
        AQUA_ASPECT = NMS.registerEnchantment(new AquaAspect(new NamespacedKey(plugin, "aqua_aspect")));
        CURSE_OF_AQUAPHOBIA = NMS.registerEnchantment(new CurseOfAquaphobia(new NamespacedKey(plugin, "curse_of_aquaphobia")));
        CURSE_OF_IRON_GRIP = NMS.registerEnchantment(new CurseOfIronGrip(new NamespacedKey(plugin, "curse_of_iron_grip")));
        CURSE_OF_LEVITATING = NMS.registerEnchantment(new CurseOfLevitating(new NamespacedKey(plugin, "curse_of_levitating")));
        CURSE_OF_MINING_FATIGUE = NMS.registerEnchantment(new CurseOfMiningFatigue(new NamespacedKey(plugin, "curse_of_mining_fatigue")));
        CURSE_OF_RADIANCE = NMS.registerEnchantment(new CurseOfRadiance(new NamespacedKey(plugin, "curse_of_radiance")));
        CURSE_OF_SLOWNESS = NMS.registerEnchantment(new CurseOfSlowness(new NamespacedKey(plugin, "curse_of_slowness")));
        CURSE_OF_WEAKNESS = NMS.registerEnchantment(new CurseOfWeakness(new NamespacedKey(plugin, "curse_of_weakness")));
        ENLIGHTENMENT = NMS.registerEnchantment(new Enlightenment(new NamespacedKey(plugin, "enlightenment")));
        EXCAVATING = NMS.registerEnchantment(new Excavating(new NamespacedKey(plugin, "excavating")));
        FLING = NMS.registerEnchantment(new Fling(new NamespacedKey(plugin, "fling")));
        INVISIBILITY = NMS.registerEnchantment(new Invisibility(new NamespacedKey(plugin, "invisibility")));
        PRECISION = NMS.registerEnchantment(new Precision(new NamespacedKey(plugin, "precision")));
        RADIANCE = NMS.registerEnchantment(new Radiance(new NamespacedKey(plugin, "radiance")));
        RANGE = NMS.registerEnchantment(new Range(new NamespacedKey(plugin, "range")));
        REFLECTION = NMS.registerEnchantment(new Reflection(new NamespacedKey(plugin, "reflection")));
        REPLANTING = NMS.registerEnchantment(new Replanting(new NamespacedKey(plugin, "replanting")));
        SMELTING = NMS.registerEnchantment(new Smelting(new NamespacedKey(plugin, "smelting")));
        SPURS = NMS.registerEnchantment(new Spurs(new NamespacedKey(plugin, "spurs")));
        TELEKINESIS = NMS.registerEnchantment(new Telekinesis(new NamespacedKey(plugin, "telekinesis")));
        TILLING = NMS.registerEnchantment(new Tilling(new NamespacedKey(plugin, "tilling")));
        TREE_FELLER = NMS.registerEnchantment(new TreeFeller(new NamespacedKey(plugin, "tree_feller")));
        UNBREAKABLE = NMS.registerEnchantment(new Unbreakable(new NamespacedKey(plugin, "unbreakable")));
        CONDUIT_POWER = NMS.registerEnchantment(new ConduitPower(new NamespacedKey(plugin, "conduit_power")));
        GILLS = NMS.registerEnchantment(new Gills(new NamespacedKey(plugin, "gills")));
        NIGHT_VISION = NMS.registerEnchantment(new NightVision(new NamespacedKey(plugin, "night_vision")));
        SUSTENANCE = NMS.registerEnchantment(new Sustenance(new NamespacedKey(plugin, "sustenance")));
        WORM = NMS.registerEnchantment(new Worm(new NamespacedKey(plugin, "worm")));
        DEATH_WISH = NMS.registerEnchantment(new DeathWish(new NamespacedKey(plugin, "death_wish")));
        DRAGON_SCALES = NMS.registerEnchantment(new DragonScales(new NamespacedKey(plugin, "dragon_scales")));
        FIRE_RESISTANCE = NMS.registerEnchantment(new FireResistance(new NamespacedKey(plugin, "fire_resistance")));
        FLAMING = NMS.registerEnchantment(new Flaming(new NamespacedKey(plugin, "flaming")));
        HASTE = NMS.registerEnchantment(new Haste(new NamespacedKey(plugin, "haste")));
        HEALING = NMS.registerEnchantment(new Healing(new NamespacedKey(plugin, "healing")));
        HERO_OF_THE_VILLAGE = NMS.registerEnchantment(new HeroOfTheVillage(new NamespacedKey(plugin, "hero_of_the_village")));
        INCREASED_HEALTH = NMS.registerEnchantment(new IncreasedHealth(new NamespacedKey(plugin, "increased_health")));
        PROJECTILE_RESISTANCE = NMS.registerEnchantment(new ProjectileResistance(new NamespacedKey(plugin, "projectile_resistance")));
        STRENGTH = NMS.registerEnchantment(new Strength(new NamespacedKey(plugin, "strength")));
        BOOSTERS = NMS.registerEnchantment(new Boosters(new NamespacedKey(plugin, "boosters")));
        MOMENTUM = NMS.registerEnchantment(new Momentum(new NamespacedKey(plugin, "momentum")));
        DOLPHINS_GRACE = NMS.registerEnchantment(new DolphinsGrace(new NamespacedKey(plugin, "dolphins_grace")));
        HEAVY = NMS.registerEnchantment(new Heavy(new NamespacedKey(plugin, "heavy")));
        SWIFTNESS = NMS.registerEnchantment(new Swiftness(new NamespacedKey(plugin, "swiftness")));
        BLOOD_TIPPED = NMS.registerEnchantment(new BloodTipped(new NamespacedKey(plugin, "blood_tipped")));
        CRIPPLING = NMS.registerEnchantment(new Crippling(new NamespacedKey(plugin, "crippling")));
        DEBILITATING = NMS.registerEnchantment(new Debilitating(new NamespacedKey(plugin, "debilitating")));
        DESTRUCTIVE = NMS.registerEnchantment(new Destructive(new NamespacedKey(plugin, "destructive")));
        DISORIENTING = NMS.registerEnchantment(new Disorienting(new NamespacedKey(plugin, "disorienting")));
        FIRE_BLAST = NMS.registerEnchantment(new FireBlast(new NamespacedKey(plugin, "fire_blast")));
        GLASS = NMS.registerEnchantment(new Glass(new NamespacedKey(plugin, "glass")));
        HARPOON = NMS.registerEnchantment(new Harpoon(new NamespacedKey(plugin, "harpoon")));
        LEVITATING = NMS.registerEnchantment(new Levitating(new NamespacedKey(plugin, "levitating")));
        LIFE_STEAL = NMS.registerEnchantment(new LifeSteal(new NamespacedKey(plugin, "life_steal")));
        OBSCURE = NMS.registerEnchantment(new Obscure(new NamespacedKey(plugin, "obscure")));
        SALMON_SLINGER = NMS.registerEnchantment(new SalmonSlinger(new NamespacedKey(plugin, "salmon_slinger")));
        SEEKING = NMS.registerEnchantment(new Seeking(new NamespacedKey(plugin, "seeking")));
        STARVING = NMS.registerEnchantment(new Starving(new NamespacedKey(plugin, "starving")));
        TELEPORT = NMS.registerEnchantment(new Teleport(new NamespacedKey(plugin, "teleport")));
        VENOM = NMS.registerEnchantment(new Venom(new NamespacedKey(plugin, "venom")));
        WITHERING = NMS.registerEnchantment(new Withering(new NamespacedKey(plugin, "withering")));
        ANCHOR = NMS.registerEnchantment(new Anchor(new NamespacedKey(plugin, "anchor")));
        BOUNCE = NMS.registerEnchantment(new Bounce(new NamespacedKey(plugin, "bounce")));
        CRUSH = NMS.registerEnchantment(new Crush(new NamespacedKey(plugin, "crush")));
        LEAPING = NMS.registerEnchantment(new Leaping(new NamespacedKey(plugin, "leaping")));
        SLOW_FALLING = NMS.registerEnchantment(new SlowFalling(new NamespacedKey(plugin, "slow_falling")));
        VOLLEY = NMS.registerEnchantment(new Volley(new NamespacedKey(plugin, "volley")));
    }

    public static ArrayList<CustomEnchantment> all() {
        return new ArrayList<>(customEnchantments);
    }

    public static boolean isCustomEnchantment(Enchantment enchantment) {
        // return enchantment instanceof CustomEnchantment; // Not reload safe
        return customEnchantmentLookup.contains(EnchantTools.name(enchantment));
    }

    public static String description(Enchantment enchantment) {
        return descriptions.get(EnchantTools.name(enchantment));
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
}
