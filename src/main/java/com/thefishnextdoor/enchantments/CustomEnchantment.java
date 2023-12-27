package com.thefishnextdoor.enchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
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
import com.thefishnextdoor.enchantments.enchantments.exclusive.weapon.Harpoon;
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
import com.thefishnextdoor.enchantments.util.InventoryUtil;

public abstract class CustomEnchantment extends Enchantment {
    private static ArrayList<CustomEnchantment> customEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveWeaponEnchantment> mutuallyExclusiveWeaponEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveHelmetEnchantment> mutuallyExclusiveHelmetEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveChestplateEnchantment> mutuallyExclusiveChestplateEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveElytraEnchantment> mutuallyExclusiveElytraEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveLeggingsEnchantment> mutuallyExclusiveLeggingsEnchantments = new ArrayList<>();
    private static ArrayList<MutuallyExclusiveBootsEnchantment> mutuallyExclusiveBootsEnchantments = new ArrayList<>();
    private static HashMap<String, String> descriptions = new HashMap<>();
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
    public static MutuallyExclusiveWeaponEnchantment HARPOON;
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
        register(this);
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    public abstract String getDescription();

    public static abstract class MutuallyExclusiveWeaponEnchantment extends CustomEnchantment {
        public MutuallyExclusiveWeaponEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveWeaponEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.BREAKABLE;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isWeapon(item.getType());
        }
    }

    public static abstract class MutuallyExclusiveHelmetEnchantment extends CustomEnchantment {
        public MutuallyExclusiveHelmetEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveHelmetEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_HEAD;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isHelmet(item.getType());
        }
    }

    public static abstract class MutuallyExclusiveChestplateEnchantment extends CustomEnchantment {
        public MutuallyExclusiveChestplateEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveChestplateEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_TORSO;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isChestplate(item.getType());
        }
    }

    public static abstract class MutuallyExclusiveElytraEnchantment extends CustomEnchantment {
        public MutuallyExclusiveElytraEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveElytraEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_TORSO;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return item.getType() == Material.ELYTRA;
        }
    }

    public static abstract class MutuallyExclusiveLeggingsEnchantment extends CustomEnchantment {
        public MutuallyExclusiveLeggingsEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveLeggingsEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_LEGS;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isLeggings(item.getType());
        }
    }

    public static abstract class MutuallyExclusiveBootsEnchantment extends CustomEnchantment {
        public MutuallyExclusiveBootsEnchantment(NamespacedKey key) {
            super(key);
        }

        @Override
        public boolean conflictsWith(Enchantment other) {
            return isMutuallyExclusiveBootsEnchantment(other);
        }

        @Override
        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ARMOR_FEET;
        }

        @Override
        public boolean canEnchantItem(ItemStack item) {
            if (item == null) return false;
            return InventoryUtil.isBoots(item.getType());
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
        HARPOON = new Harpoon(new NamespacedKey(plugin, "harpoon"));
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

    public static ArrayList<CustomEnchantment> all() {
        return new ArrayList<>(customEnchantments);
    }

    public static boolean isCustomEnchantment(Enchantment enchantment) {
        //return enchantment instanceof CustomEnchantment; // Not reload safe
        for (CustomEnchantment customEnchantment : customEnchantments) {
            if (EnchantUtil.same(customEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveWeaponEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveWeaponEnchantment; // Not reload safe
        for (MutuallyExclusiveWeaponEnchantment mutuallyExclusiveWeaponEnchantment : mutuallyExclusiveWeaponEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveWeaponEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveHelmetEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveHelmetEnchantment; // Not reload safe
        for (MutuallyExclusiveHelmetEnchantment mutuallyExclusiveHelmetEnchantment : mutuallyExclusiveHelmetEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveHelmetEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveChestplateEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveChestplateEnchantment; // Not reload safe
        for (MutuallyExclusiveChestplateEnchantment mutuallyExclusiveChestplateEnchantment : mutuallyExclusiveChestplateEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveChestplateEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveElytraEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveElytraEnchantment; // Not reload safe
        for (MutuallyExclusiveElytraEnchantment mutuallyExclusiveElytraEnchantment : mutuallyExclusiveElytraEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveElytraEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveLeggingsEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveLeggingsEnchantment; // Not reload safe
        for (MutuallyExclusiveLeggingsEnchantment mutuallyExclusiveLeggingsEnchantment : mutuallyExclusiveLeggingsEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveLeggingsEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static boolean isMutuallyExclusiveBootsEnchantment(Enchantment enchantment) {
        //return enchantment instanceof MutuallyExclusiveBootsEnchantment; // Not reload safe
        for (MutuallyExclusiveBootsEnchantment mutuallyExclusiveBootsEnchantment : mutuallyExclusiveBootsEnchantments) {
            if (EnchantUtil.same(mutuallyExclusiveBootsEnchantment, enchantment)) return true;
        }
        return false;
    }

    public static String description(Enchantment enchantment) {
        return descriptions.get(EnchantUtil.name(enchantment));
    }

    public static boolean hasCustomEnchantments(ItemStack item) {
        for (Enchantment enchantment : EnchantUtil.enchantments(item)) {
            if (isCustomEnchantment(enchantment)) return true;
        }
        return false;
    }

    public static ArrayList<Enchantment> customEnchantments(ItemStack item) {
        ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
        for (Enchantment enchantment : EnchantUtil.enchantments(item)) {
            if (isCustomEnchantment(enchantment)) {
                foundFishchantments.add(enchantment);
            }
        }
        return foundFishchantments;
    }

    private static void register(CustomEnchantment enchant) {
        if (customEnchantments.contains(enchant)) return;
        customEnchantments.add(enchant);
        if (enchant instanceof MutuallyExclusiveWeaponEnchantment) {
            mutuallyExclusiveWeaponEnchantments.add((MutuallyExclusiveWeaponEnchantment) enchant);
        }
        else if (enchant instanceof MutuallyExclusiveHelmetEnchantment) {
            mutuallyExclusiveHelmetEnchantments.add((MutuallyExclusiveHelmetEnchantment) enchant);
        }
        else if (enchant instanceof MutuallyExclusiveChestplateEnchantment) {
            mutuallyExclusiveChestplateEnchantments.add((MutuallyExclusiveChestplateEnchantment) enchant);
        }
        else if (enchant instanceof MutuallyExclusiveElytraEnchantment) {
            mutuallyExclusiveElytraEnchantments.add((MutuallyExclusiveElytraEnchantment) enchant);
        }
        else if (enchant instanceof MutuallyExclusiveLeggingsEnchantment) {
            mutuallyExclusiveLeggingsEnchantments.add((MutuallyExclusiveLeggingsEnchantment) enchant);
        }
        else if (enchant instanceof MutuallyExclusiveBootsEnchantment) {
            mutuallyExclusiveBootsEnchantments.add((MutuallyExclusiveBootsEnchantment) enchant);
        }
        descriptions.put(EnchantUtil.name(enchant), enchant.getDescription());
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
