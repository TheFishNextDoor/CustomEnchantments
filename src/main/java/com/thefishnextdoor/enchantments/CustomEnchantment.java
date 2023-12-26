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

public abstract class CustomEnchantment extends Enchantment {
    private static ArrayList<CustomEnchantment> customEnchantments = new ArrayList<>();
    private static HashMap<String, String> enchantmentDescriptions = new HashMap<>();
    public static CustomEnchantment AQUA_ASPECT;
    public static CustomEnchantment CURSE_OF_AQUAPHOBIA;
    public static CustomEnchantment CURSE_OF_LEVITATING;
    public static CustomEnchantment CURSE_OF_MINING_FATIGUE;
    public static CustomEnchantment CURSE_OF_RADIANCE;
    public static CustomEnchantment CURSE_OF_SLOWNESS;
    public static CustomEnchantment CURSE_OF_WEAKNESS;
    public static CustomEnchantment ENLIGHTENMENT;
    public static CustomEnchantment EXCAVATING;
    public static CustomEnchantment FLING;
    public static CustomEnchantment INVISIBILITY;
    public static CustomEnchantment PRECISION;
    public static CustomEnchantment RADIANCE;
    public static CustomEnchantment RANGE;
    public static CustomEnchantment REFLECTION;
    public static CustomEnchantment REPLANTING;
    public static CustomEnchantment SMELTING;
    public static CustomEnchantment SPURS;
    public static CustomEnchantment TELEKINESIS;
    public static CustomEnchantment TILLING;
    public static CustomEnchantment UNBREAKABLE;
    public static MutuallyExclusiveHelmetEnchantment CONDUIT_POWER;
    public static MutuallyExclusiveHelmetEnchantment GILLS;
    public static MutuallyExclusiveHelmetEnchantment NIGHT_VISION;
    public static MutuallyExclusiveHelmetEnchantment SUSTENANCE;
    public static MutuallyExclusiveHelmetEnchantment WORM;
    public static MutuallyExclusiveChestplateEnchantment DEATH_WISH;
    public static MutuallyExclusiveChestplateEnchantment DRAGON_SCALES;
    public static MutuallyExclusiveChestplateEnchantment FIRE_RESISTANCE;
    public static MutuallyExclusiveChestplateEnchantment FLAMING;
    public static MutuallyExclusiveChestplateEnchantment HASTE;
    public static MutuallyExclusiveChestplateEnchantment HEALING;
    public static MutuallyExclusiveChestplateEnchantment HERO_OF_THE_VILLAGE;
    public static MutuallyExclusiveChestplateEnchantment INCREASED_HEALTH;
    public static MutuallyExclusiveChestplateEnchantment PROJECTILE_RESISTANCE;
    public static MutuallyExclusiveChestplateEnchantment STRENGTH;
    public static MutuallyExclusiveElytraEnchantment BOOSTERS;
    public static MutuallyExclusiveElytraEnchantment MOMENTUM;
    public static MutuallyExclusiveLeggingsEnchantment DOLPHINS_GRACE;
    public static MutuallyExclusiveLeggingsEnchantment HEAVY;
    public static MutuallyExclusiveLeggingsEnchantment SWIFTNESS;
    public static MutuallyExclusiveWeaponEnchantment BLOOD_TIPPED;
    public static MutuallyExclusiveWeaponEnchantment CRIPPLING;
    public static MutuallyExclusiveWeaponEnchantment DEBILITATING;
    public static MutuallyExclusiveWeaponEnchantment DESTRUCTIVE;
    public static MutuallyExclusiveWeaponEnchantment DISORIENTING;
    public static MutuallyExclusiveWeaponEnchantment FIRE_BLAST;
    public static MutuallyExclusiveWeaponEnchantment GLASS;
    public static MutuallyExclusiveWeaponEnchantment LEVITATING;
    public static MutuallyExclusiveWeaponEnchantment LIFE_STEAL;
    public static MutuallyExclusiveWeaponEnchantment OBSCURE;
    public static MutuallyExclusiveWeaponEnchantment SALMON_SLINGER;
    public static MutuallyExclusiveWeaponEnchantment STARVING;
    public static MutuallyExclusiveWeaponEnchantment TELEPORT;
    public static MutuallyExclusiveWeaponEnchantment VENOM;
    public static MutuallyExclusiveWeaponEnchantment VOLLEY;
    public static MutuallyExclusiveWeaponEnchantment WITHERING;
    public static MutuallyExclusiveBootsEnchantment ANCHOR;
    public static MutuallyExclusiveBootsEnchantment BOUNCE;
    public static MutuallyExclusiveBootsEnchantment CRUSH;
    public static MutuallyExclusiveBootsEnchantment LEAPING;
    public static MutuallyExclusiveBootsEnchantment SLOW_FALLING;

    public CustomEnchantment(NamespacedKey key) {
        super(key);
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    public static abstract class MutuallyExclusiveWeaponEnchantment extends CustomEnchantment {
        public MutuallyExclusiveWeaponEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveWeaponEnchantment;
        }
    }

    public static abstract class MutuallyExclusiveHelmetEnchantment extends CustomEnchantment {
        public MutuallyExclusiveHelmetEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveHelmetEnchantment;
        }
    }

    public static abstract class MutuallyExclusiveChestplateEnchantment extends CustomEnchantment {
        public MutuallyExclusiveChestplateEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveChestplateEnchantment;
        }
    }

    public static abstract class MutuallyExclusiveElytraEnchantment extends CustomEnchantment {
        public MutuallyExclusiveElytraEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveElytraEnchantment;
        }
    }

    public static abstract class MutuallyExclusiveLeggingsEnchantment extends CustomEnchantment {
        public MutuallyExclusiveLeggingsEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveLeggingsEnchantment;
        }
    }

    public static abstract class MutuallyExclusiveBootsEnchantment extends CustomEnchantment {
        public MutuallyExclusiveBootsEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return other instanceof MutuallyExclusiveBootsEnchantment;
        }
    }

    static void init(Plugin plugin) {
        AQUA_ASPECT = new AquaAspect(new NamespacedKey(plugin, "aqua_aspect"));
        CURSE_OF_AQUAPHOBIA = new CurseOfAquaphobia(new NamespacedKey(plugin, "curse_of_aquaphobia"));
        CURSE_OF_LEVITATING = new CurseOfLevitating(new NamespacedKey(plugin, "curse_of_levitating"));
        CURSE_OF_MINING_FATIGUE = new CurseOfMiningFatigue(new NamespacedKey(plugin, "curse_of_mining_fatigue"));
        CURSE_OF_RADIANCE = new CurseOfRadiance(new NamespacedKey(plugin, "curse_of_radiance"));
        CURSE_OF_SLOWNESS = new CurseOfSlowness(new NamespacedKey(plugin, "curse_of_slowness"));
        CURSE_OF_WEAKNESS = new CurseOfWeakness(new NamespacedKey(plugin, "curse_of_weakness"));
        ENLIGHTENMENT = new Enlightenment(new NamespacedKey(plugin, "enlightenment"));
        EXCAVATING = new Excavating(new NamespacedKey(plugin, "excavating"));
        FLING = new Fling(new NamespacedKey(plugin, "fling"));
        INVISIBILITY = new Invisibility(new NamespacedKey(plugin, "invisibility"));
        PRECISION = new Precision(new NamespacedKey(plugin, "precision"));
        RADIANCE = new Radiance(new NamespacedKey(plugin, "radiance"));
        RANGE = new Range(new NamespacedKey(plugin, "range"));
        REFLECTION = new Reflection(new NamespacedKey(plugin, "reflection"));
        REPLANTING = new Replanting(new NamespacedKey(plugin, "replanting"));
        SMELTING = new Smelting(new NamespacedKey(plugin, "smelting"));
        SPURS = new Spurs(new NamespacedKey(plugin, "spurs"));
        TELEKINESIS = new Telekinesis(new NamespacedKey(plugin, "telekinesis"));
        TILLING = new Tilling(new NamespacedKey(plugin, "tilling"));
        UNBREAKABLE = new Unbreakable(new NamespacedKey(plugin, "unbreakable"));
        CONDUIT_POWER = new ConduitPower(new NamespacedKey(plugin, "conduit_power"));
        GILLS = new Gills(new NamespacedKey(plugin, "gills"));
        NIGHT_VISION = new NightVision(new NamespacedKey(plugin, "night_vision"));
        SUSTENANCE = new Sustenance(new NamespacedKey(plugin, "sustenance"));
        WORM = new Worm(new NamespacedKey(plugin, "worm"));
        DEATH_WISH = new DeathWish(new NamespacedKey(plugin, "death_wish"));
        DRAGON_SCALES = new DragonScales(new NamespacedKey(plugin, "dragon_scales"));
        FIRE_RESISTANCE = new FireResistance(new NamespacedKey(plugin, "fire_resistance"));
        FLAMING = new Flaming(new NamespacedKey(plugin, "flaming"));
        HASTE = new Haste(new NamespacedKey(plugin, "haste"));
        HEALING = new Healing(new NamespacedKey(plugin, "healing"));
        HERO_OF_THE_VILLAGE = new HeroOfTheVillage(new NamespacedKey(plugin, "hero_of_the_village"));
        INCREASED_HEALTH = new IncreasedHealth(new NamespacedKey(plugin, "increased_health"));
        PROJECTILE_RESISTANCE = new ProjectileResistance(new NamespacedKey(plugin, "projectile_resistance"));
        STRENGTH = new Strength(new NamespacedKey(plugin, "strength"));
        BOOSTERS = new Boosters(new NamespacedKey(plugin, "boosters"));
        MOMENTUM = new Momentum(new NamespacedKey(plugin, "momentum"));
        DOLPHINS_GRACE = new DolphinsGrace(new NamespacedKey(plugin, "dolphins_grace"));
        HEAVY = new Heavy(new NamespacedKey(plugin, "heavy"));
        SWIFTNESS = new Swiftness(new NamespacedKey(plugin, "swiftness"));
        BLOOD_TIPPED = new BloodTipped(new NamespacedKey(plugin, "blood_tipped"));
        CRIPPLING = new Crippling(new NamespacedKey(plugin, "crippling"));
        DEBILITATING = new Debilitating(new NamespacedKey(plugin, "debilitating"));
        DESTRUCTIVE = new Destructive(new NamespacedKey(plugin, "destructive"));
        DISORIENTING = new Disorienting(new NamespacedKey(plugin, "disorienting"));
        FIRE_BLAST = new FireBlast(new NamespacedKey(plugin, "fire_blast"));
        GLASS = new Glass(new NamespacedKey(plugin, "glass"));
        LEVITATING = new Levitating(new NamespacedKey(plugin, "levitating"));
        LIFE_STEAL = new LifeSteal(new NamespacedKey(plugin, "life_steal"));
        OBSCURE = new Obscure(new NamespacedKey(plugin, "obscure"));
        SALMON_SLINGER = new SalmonSlinger(new NamespacedKey(plugin, "salmon_slinger"));
        STARVING = new Starving(new NamespacedKey(plugin, "starving"));
        TELEPORT = new Teleport(new NamespacedKey(plugin, "teleport"));
        VENOM = new Venom(new NamespacedKey(plugin, "venom"));
        VOLLEY = new Volley(new NamespacedKey(plugin, "volley"));
        WITHERING = new Withering(new NamespacedKey(plugin, "withering"));
        ANCHOR = new Anchor(new NamespacedKey(plugin, "anchor"));
        BOUNCE = new Bounce(new NamespacedKey(plugin, "bounce"));
        CRUSH = new Crush(new NamespacedKey(plugin, "crush"));
        LEAPING = new Leaping(new NamespacedKey(plugin, "leaping"));
        SLOW_FALLING = new SlowFalling(new NamespacedKey(plugin, "slow_falling"));
    }

    static void registerAll() {
        register(AQUA_ASPECT, "Deal extra damage to enderman, blaze etc. Rare drop from drowned.");
        register(CURSE_OF_AQUAPHOBIA, "Wearer takes damage from water and rain. Rare drop from enderman.");
        register(CURSE_OF_LEVITATING, "Wearer levitates uncontrollably. Rare drop from shulkers.");
        register(CURSE_OF_MINING_FATIGUE, "Wearer has decreased mining speed. Rare drop from elder guardian.");
        register(CURSE_OF_RADIANCE, "Wearer will begin to glow. Rare drop from magma cube.");
        register(CURSE_OF_SLOWNESS, "Wearer has decreased movement speed. Rare drop from warden.");
        register(CURSE_OF_WEAKNESS, "Wearer has decreased melee damage. Rare drop from cave spider.");
        register(ENLIGHTENMENT, "Increased Xp drops from blocks and mobs. Rare drop from warden.");
        register(EXCAVATING, "Mine blocks in a 3x3 grid. Rare drop from charged creeper.");
        register(FLING, "Sends attacked entities upwards. Rare drop from creeper.");
        register(INVISIBILITY, "Wearer receives invisibility. Rare drop from witch.");
        register(PRECISION, "Fired projectiles have 100% accuracy. Rare drop from skeleton.");
        register(RADIANCE, "Attacked entities receive glowing. Rare drop from magma cube.");
        register(RANGE, "Fired projectiles have a higher velocity. Rare drop from skeleton.");
        register(REFLECTION, "Reflect arrows at a higher velocity. Rare drop from pillager.");
        register(REPLANTING, "Replants anything harvested automatically. Rare drop from pillager.");
        register(SMELTING, "Dropped items will be smelted if possible. Rare drop from blaze.");
        register(SPURS, "Mounted mobs receive speed and jump boost. Rare drop from pillager.");
        register(TELEKINESIS, "Dropped items are added to your inventory. Rare drop from enderman.");
        register(TILLING, "Hoe farmland 3x3. Rare drop from pillager.");
        register(UNBREAKABLE, "Item will not lose durability. Rare drop from wither.");
        register(CONDUIT_POWER, "See underwater, breathe underwater, mine faster underwater. Drop from elder guardian.");
        register(GILLS, "Wearer can breath underwater. Rare drop from drowned.");
        register(NIGHT_VISION, "Wearer has improved vision in the dark. Rare drop from spider.");
        register(SUSTENANCE, "Hunger bar replenishes over time. Rare drop from zombie.");
        register(WORM, "Prevents suffocation damage. Rare drop from silverfish.");
        register(DEATH_WISH, "Wearer takes increased damage and deals increased damage. Rare drop from wither skeleton.");
        register(DRAGON_SCALES, "Wearer has increased damage resistance. Rare drop from ender dragon.");
        register(FIRE_RESISTANCE, "Wearer is immune to fire damage. Rare drop from blaze.");
        register(FLAMING, "Attackers will be lit on fire. Rare drop from blaze.");
        register(HASTE, "Wearer has increased mining speed. Rare drop from piglin.");
        register(HEALING, "Wearer has faster health regeneration. Rare drop from zombified piglin.");
        register(HERO_OF_THE_VILLAGE, "Wearer receives reduced villager prices. Rare drop from pillager.");
        register(INCREASED_HEALTH, "Wearer has increased max health. Rare drop from ender dragon.");
        register(PROJECTILE_RESISTANCE, "Wearer is immune to projectile damage. Rare drop from wither.");
        register(STRENGTH, "Wearer has increased melee damage. Rare drop from zombified piglin.");
        register(BOOSTERS, "A small amount of constant thrust is added to the elytra. Rare drop from creeper.");
        register(MOMENTUM, "Wearer will gain speed faster when gliding. Rare drop from ender dragon.");
        register(DOLPHINS_GRACE, "Wearer can move quickly in water. Rare drop from guardian.");
        register(HEAVY, "Wearer receives no knockback. Rare drop from warden.");
        register(SWIFTNESS, "Wearer has increased movement speed. Rare drop from witch.");
        register(BLOOD_TIPPED, "Take slight damage when firing, applies your potion effects to the arrow. Rare drop from piglin.");
        register(CRIPPLING, "Attacked entities receive slowness. Rare drop from skeleton.");
        register(DEBILITATING, "Attacked entities receive weakness. Rare drop from witch.");
        register(DESTRUCTIVE, "Projectiles destroy blocks. Rare drop from creeper.");
        register(DISORIENTING, "Attacked entities receive confusion. Rare drop from spider.");
        register(FIRE_BLAST, "Launched projectiles are transformed into a blaze fire ball. Rare drop from blaze.");
        register(GLASS, "Increased damage, decreased durability. Rare drop from skeleton.");
        register(LEVITATING, "Launched projectiles are transformed into a shulker bullet. Rare drop from shulker.");
        register(LIFE_STEAL, "A portion of the damage you deal is added to your health. Rare drop from wither.");
        register(OBSCURE, "Attacked entities receive blindness. Rare drop from warden.");
        register(SALMON_SLINGER, "Launched projectiles are transformed into salmon. This enchantment is not dropped by any mob.");
        register(STARVING, "Attacked entities receive hunger. Rare drop from husk.");
        register(TELEPORT, "Teleport to the location your projectile hits. Rare drop from enderman.");
        register(VENOM, "Attacked entities receive poison. Rare drop from cave spider.");
        register(VOLLEY, "Fire multiple arrows at once. Rare drop from skeleton.");
        register(WITHERING, "Attacked entities receive wither. Rare drop from the wither.");
        register(ANCHOR, "Wearer sinks in water and has increased traction in water. Should be combined with depth strider for best effect. Rare drop from drowned.");
        register(BOUNCE, "Wearer bounces instead of taking fall damage. Rare drop from slime.");
        register(CRUSH, "Falling on an entity will damage it. Rare drop from ravagers.");
        register(LEAPING, "Wearer has increased jump height. Rare drop from slime.");
        register(SLOW_FALLING, "Wearer falls gracefully like a feather. Rare drop from ghast.");
    }    

    public static ArrayList<CustomEnchantment> all() {
        return new ArrayList<>(customEnchantments);
    }

    public static String desciption(Enchantment enchant) {
        return enchantmentDescriptions.get(EnchantUtil.name(enchant));
    }

    public static boolean hasCustomEnchantments(ItemStack item) {
        for (Enchantment enchantment : EnchantUtil.enchantments(item)) {
            if (enchantment instanceof CustomEnchantment) return true;
        }
        return false;
    }

    public static ArrayList<Enchantment> customEnchantments(ItemStack item) {
        ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
        for (Enchantment enchantment : EnchantUtil.enchantments(item)) {
            if (enchantment instanceof CustomEnchantment) {
                foundFishchantments.add(enchantment);
            }
        }
        return foundFishchantments;
    }

    private static void register(CustomEnchantment enchant, String info) {
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
}
