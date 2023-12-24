package com.thefishnextdoor.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.thefishnextdoor.enchantments.enchantments.AquaAspect;
import com.thefishnextdoor.enchantments.enchantments.CurseOfAquaphobia;
import com.thefishnextdoor.enchantments.enchantments.CurseOfLevitating;
import com.thefishnextdoor.enchantments.enchantments.CurseOfMiningFatigue;
import com.thefishnextdoor.enchantments.enchantments.CurseOfRadiance;
import com.thefishnextdoor.enchantments.enchantments.CurseOfSlowness;
import com.thefishnextdoor.enchantments.enchantments.CurseOfWeakness;
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
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Destructive;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Disorienting;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.FireBlast;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Glass;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Levitating;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.LifeSteal;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Obscure;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.SalmonSlinger;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Starving;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Teleport;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Venom;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Volley;
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Withering;
import com.thefishnextdoor.enchantments.util.EnchantUtil;

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
    public static Enchantment SALMON_SLINGER;
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
    public static Enchantment DEATH_WISH;
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
    public static Enchantment FIRE_BLAST;
    public static Enchantment TELEPORT;

    private static ArrayList<Enchantment> customEnchantments = new ArrayList<>();
    private static HashMap<String, String> enchantmentDescriptions = new HashMap<>();

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
        SALMON_SLINGER = new SalmonSlinger(new NamespacedKey(plugin, "salmon_slinger"));
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
        DEATH_WISH = new DeathWish(new NamespacedKey(plugin, "death_wish"));
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
        FIRE_BLAST = new FireBlast(new NamespacedKey(plugin, "fire_blast"));
        TELEPORT = new Teleport(new NamespacedKey(plugin, "teleport"));
    }

    static void registerAll() {
        registerCustomEnchantment(DESTRUCTIVE, "Projectiles destroy blocks. Rare drop from creeper.");
        registerCustomEnchantment(TILLING, "Hoe farmland 3x3. Rare drop from pillager.");
        registerCustomEnchantment(REPLANTING, "Replants anything harvested automatically. Rare drop from pillager.");
        registerCustomEnchantment(EXCAVATING, "Mine blocks in a 3x3 grid. Rare drop from charged creeper.");
        registerCustomEnchantment(SMELTING, "Dropped items will be smelted if possible. Rare drop from blaze.");
        registerCustomEnchantment(TELEKINESIS, "Dropped items are added to your inventory. Rare drop from enderman.");
        registerCustomEnchantment(UNBREAKABLE, "Item will not lose durability. Rare drop from wither.");
        registerCustomEnchantment(SUSTENANCE, "Hunger bar replenishes over time. Rare drop from zombie.");
        registerCustomEnchantment(WORM, "Prevents suffocation damage. Rare drop from silverfish.");
        registerCustomEnchantment(CRUSH, "Falling on an entity will damage it. Rare drop from ravagers.");
        registerCustomEnchantment(LIFE_STEAL, "A portion of the damage you deal is added to your health. Rare drop from wither.");
        registerCustomEnchantment(FLING, "Sends attacked entities upwards. Rare drop from creeper.");
        registerCustomEnchantment(ENLIGHTENMENT, "Increased Xp drops from blocks and mobs. Rare drop from warden.");
        registerCustomEnchantment(RANGE, "Fired projectiles have a higher velocity. Rare drop from skeleton.");
        registerCustomEnchantment(PRECISION, "Fired projectiles have 100% accuracy. Rare drop from skeleton.");
        registerCustomEnchantment(VENOM, "Attacked entities receive poison. Rare drop from cave spider.");
        registerCustomEnchantment(WITHERING, "Attacked entities receive wither. Rare drop from the wither.");
        registerCustomEnchantment(BLOOD_TIPPED, "Take slight damage when firing, applies your potion effects to the arrow. Rare drop from piglin.");
        registerCustomEnchantment(VOLLEY, "Fire multiple arrows at once. Rare drop from skeleton.");
        registerCustomEnchantment(GLASS, "Increased damage, decreased durability. Rare drop from skeleton.");
        registerCustomEnchantment(LEVITATING, "Launched projectiles are transformed into a shulker bullet. Rare drop from shulker.");
        registerCustomEnchantment(RADIANCE, "Attacked entities receive glowing. Rare drop from magma cube.");
        registerCustomEnchantment(OBSCURE, "Attacked entities receive blindness. Rare drop from warden.");
        registerCustomEnchantment(SALMON_SLINGER, "Launched projectiles are transformed into salmon. This enchantment is not dropped by any mob.");
        registerCustomEnchantment(DISORIENTING, "Attacked entities receive confusion. Rare drop from spider.");
        registerCustomEnchantment(DEBILITATING, "Attacked entities receive weakness. Rare drop from witch.");
        registerCustomEnchantment(STARVING, "Attacked entities receive hunger. Rare drop from husk.");
        registerCustomEnchantment(CRIPPLING, "Attacked entities receive slowness. Rare drop from skeleton.");
        registerCustomEnchantment(SWIFTNESS, "Wearer has increased movement speed. Rare drop from witch.");
        registerCustomEnchantment(LEAPING, "Wearer has increased jump height. Rare drop from slime.");
        registerCustomEnchantment(SLOW_FALLING, "Wearer falls gracefully like a feather. Rare drop from ghast.");
        registerCustomEnchantment(DRAGON_SCALES, "Wearer has increased damage resistance. Rare drop from ender dragon.");
        registerCustomEnchantment(HEALING, "Wearer has faster health regeneration. Rare drop from zombified piglin.");
        registerCustomEnchantment(INVISIBILITY, "Wearer receives invisibility. Rare drop from witch.");
        registerCustomEnchantment(MOMENTUM, "Wearer will gain speed faster when gliding. Rare drop from ender dragon.");
        registerCustomEnchantment(BOOSTERS, "A small amount of constant thrust is added to the elytra. Rare drop from creeper.");
        registerCustomEnchantment(REFLECTION, "Reflect arrows at a higher velocity. Rare drop from pillager.");
        registerCustomEnchantment(DEATH_WISH, "Wearer takes increased damage and deals increased damage. Rare drop from wither skeleton.");
        registerCustomEnchantment(FIRE_RESISTANCE, "Wearer is immune to fire damage. Rare drop from blaze.");
        registerCustomEnchantment(GILLS, "Wearer can breath underwater. Rare drop from drowned.");
        registerCustomEnchantment(STRENGTH, "Wearer has increased melee damage. Rare drop from zombified piglin.");
        registerCustomEnchantment(HASTE, "Wearer has increased mining speed. Rare drop from piglin.");
        registerCustomEnchantment(INCREASED_HEALTH, "Wearer has increased max health. Rare drop from ender dragon.");
        registerCustomEnchantment(NIGHT_VISION, "Wearer has improved vision in the dark. Rare drop from spider.");
        registerCustomEnchantment(DOLPHINS_GRACE, "Wearer can move quickly in water. Rare drop from guardian.");
        registerCustomEnchantment(CONDUIT_POWER, "See underwater, breathe underwater, mine faster underwater. Drop from elder guardian.");
        registerCustomEnchantment(HERO_OF_THE_VILLAGE, "Wearer receives reduced villager prices. Rare drop from pillager.");
        registerCustomEnchantment(CURSE_OF_MINING_FATIGUE, "Wearer has decreased mining speed. Rare drop from elder guardian.");
        registerCustomEnchantment(CURSE_OF_SLOWNESS, "Wearer has decreased movement speed. Rare drop from warden.");
        registerCustomEnchantment(CURSE_OF_WEAKNESS, "Wearer has decreased melee damage. Rare drop from cave spider.");
        registerCustomEnchantment(CURSE_OF_LEVITATING, "Wearer levitates uncontrollably. Rare drop from shulkers.");
        registerCustomEnchantment(CURSE_OF_AQUAPHOBIA, "Wearer takes damage from water and rain. Rare drop from enderman.");
        registerCustomEnchantment(CURSE_OF_RADIANCE, "Wearer will begin to glow. Rare drop from magma cube.");
        registerCustomEnchantment(ANCHOR, "Wearer sinks in water and has increased traction in water. Should be combined with depth strider for best effect. Rare drop from drowned.");
        registerCustomEnchantment(SPURS, "Mounted mobs receive speed and jump boost. Rare drop from pillager.");
        registerCustomEnchantment(AQUA_ASPECT, "Deal extra damage to enderman, blaze etc. Rare drop from drowned.");
        registerCustomEnchantment(BOUNCE, "Wearer bounces instead of taking fall damage. Rare drop from slime.");
        registerCustomEnchantment(PROJECTILE_RESISTANCE, "Wearer is immune to projectile damage. Rare drop from wither.");
        registerCustomEnchantment(HEAVY, "Wearer receives no knockback. Rare drop from warden.");
        registerCustomEnchantment(FLAMING, "Attackers will be lit on fire. Rare drop from blaze.");
        registerCustomEnchantment(FIRE_BLAST, "Launched projectiles are transformed into a blaze fire ball. Rare drop from blaze.");
        registerCustomEnchantment(TELEPORT, "Teleport to the location your projectile hits. Rare drop from enderman.");
    }

    public static ArrayList<Enchantment> all() {
        return new ArrayList<>(customEnchantments);
    }

    public static void registerCustomEnchantment(Enchantment enchant, String info) {
        if (customEnchantments.contains(enchant)) return;
        customEnchantments.add(enchant);
        enchantmentDescriptions.put(EnchantUtil.name(enchant), info);
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

    public static String desciption(Enchantment enchant) {
        return enchantmentDescriptions.get(enchant.getKey().getKey());
    }

    public static boolean hasCustomEnchantments(ItemStack item) {
        return customEnchantments(item).size() > 0;
    }

    public static ArrayList<Enchantment> customEnchantments(ItemStack item) {
        ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
        if (item == null) return foundFishchantments;
        if (!item.hasItemMeta()) return foundFishchantments;
        for (Enchantment enchantment : EnchantUtil.enchantments(item)) {
            if (isCustom(enchantment)) foundFishchantments.add(enchantment);
        }
        return foundFishchantments;
    }

    public static boolean isCustom(Enchantment enchantment) {
        if (enchantment == null) return false;
        for (Enchantment customEnchantment : customEnchantments) {
            if (EnchantUtil.same(customEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveWeapon(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.BLOOD_TIPPED)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.LIFE_STEAL)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.VENOM)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.WITHERING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.OBSCURE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.DISORIENTING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.DEBILITATING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.STARVING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.CRIPPLING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.GLASS)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.LEVITATING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.VOLLEY)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.FIRE_BLAST)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.TELEPORT)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.DESTRUCTIVE)) return true;
        return false;
    }

    public static boolean isMutuallyExclusiveHelmet(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.CONDUIT_POWER)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.SUSTENANCE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.WORM)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.GILLS)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.NIGHT_VISION)) return true;
        return false;
    }

    public static boolean isMutuallyExclusiveChestplate(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.DEATH_WISH)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.DRAGON_SCALES)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.HEALING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.FIRE_RESISTANCE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.STRENGTH)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.HASTE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.INCREASED_HEALTH)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.HERO_OF_THE_VILLAGE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.PROJECTILE_RESISTANCE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.FLAMING)) return true;
        return false;
    }

    public static boolean isMutuallyExclusiveElytra(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.BOOSTERS)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.MOMENTUM)) return true;
        return false;
    }

    public static boolean isMutuallyExclusiveLeggings(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.DOLPHINS_GRACE)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.SWIFTNESS)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.HEAVY)) return true;
        return false;
    }

    public static boolean isMutuallyExclusiveBoots(Enchantment enchantment) {
        if (EnchantUtil.same(enchantment, CustomEnchantment.ANCHOR)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.CRUSH)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.LEAPING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.SLOW_FALLING)) return true;
        if (EnchantUtil.same(enchantment, CustomEnchantment.BOUNCE)) return true;
        return false;
    }
}
