package fun.sunrisemc.fishchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.commands.Fenchant;
import fun.sunrisemc.fishchantments.enchantments.Curses.MiningFatigueCurse;
import fun.sunrisemc.fishchantments.enchantments.Curses.SlownessCurse;
import fun.sunrisemc.fishchantments.enchantments.Curses.WeaknessCurse;
import fun.sunrisemc.fishchantments.enchantments.Curses.LevitationCurse;
import fun.sunrisemc.fishchantments.enchantments.Curses.AquaphobiaCurse;
import fun.sunrisemc.fishchantments.enchantments.Curses.DeathWish;
import fun.sunrisemc.fishchantments.enchantments.Generic.Accurate;
import fun.sunrisemc.fishchantments.enchantments.Generic.Destructive;
import fun.sunrisemc.fishchantments.enchantments.Generic.Excavating;
import fun.sunrisemc.fishchantments.enchantments.Generic.Telekinesis;
import fun.sunrisemc.fishchantments.enchantments.Generic.Fling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Enlightenment;
import fun.sunrisemc.fishchantments.enchantments.Generic.Glowing;
import fun.sunrisemc.fishchantments.enchantments.Generic.Invisibility;
import fun.sunrisemc.fishchantments.enchantments.Generic.Momentum;
import fun.sunrisemc.fishchantments.enchantments.Generic.Boosters;
import fun.sunrisemc.fishchantments.enchantments.Generic.Reflection;
import fun.sunrisemc.fishchantments.enchantments.Generic.Range;
import fun.sunrisemc.fishchantments.enchantments.Generic.Replanting;
import fun.sunrisemc.fishchantments.enchantments.Generic.Tilling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Unbreakable;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Crush;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Jump;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.SlowFall;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.FireResistance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Haste;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.HealthBoost;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.HeroOfTheVillage;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Regeneration;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Resistance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Strength;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.ConduitPower;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Food;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.NightVision;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.WaterBreathing;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Worm;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.DolphinsGrace;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.Speed;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Blindness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Confusion;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Levitation;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Hunger;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.LifeSteal;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Poison;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Slowness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Weakness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Wither;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.BloodTipped;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Volley;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Glass;
import fun.sunrisemc.fishchantments.events.ProjectileHit;
import fun.sunrisemc.fishchantments.events.ItemDamage;
import fun.sunrisemc.fishchantments.events.Fall;
import fun.sunrisemc.fishchantments.events.AttackEntity;
import fun.sunrisemc.fishchantments.events.BlockDropItems;
import fun.sunrisemc.fishchantments.events.BreakBlock;
import fun.sunrisemc.fishchantments.events.ClickBlock;
import fun.sunrisemc.fishchantments.events.EntityDamage;
import fun.sunrisemc.fishchantments.events.FireProjectile;
import fun.sunrisemc.fishchantments.events.Glide;
import fun.sunrisemc.fishchantments.events.Grindstone;
import fun.sunrisemc.fishchantments.events.HungerChange;
import fun.sunrisemc.fishchantments.events.EntityDeath;
import fun.sunrisemc.fishchantments.events.PrepareAnvil;
import fun.sunrisemc.fishchantments.events.Suffocation;
import fun.sunrisemc.fishchantments.events.Till;
import net.md_5.bungee.api.ChatColor;

public class Plugin extends JavaPlugin {
    private static final Logger LOGGER = Logger.getLogger("Fishchantments");
    private static Settings settings = new Settings();
    private ArrayList<Enchantment> fishchantments = new ArrayList<>();
    
    public final Enchantment DESTRUCTIVE = new Destructive(new NamespacedKey(this, "destructive_fishchantment"));
    public final Enchantment TILLING = new Tilling(new NamespacedKey(this, "tilling_fishchantment"));
    public final Enchantment REPLANTING = new Replanting(new NamespacedKey(this, "replanting_fishchantment"));
    public final Enchantment EXCAVATING = new Excavating(new NamespacedKey(this, "excavating_fishchantment"));
    public final Enchantment TELEKINESIS = new Telekinesis(new NamespacedKey(this, "telekinesis_fishchantment"));
    public final Enchantment UNBREAKABLE = new Unbreakable(new NamespacedKey(this, "unbreakable_fishchantment"));
    public final Enchantment FOOD = new Food(new NamespacedKey(this, "food_fishchantment"));
    public final Enchantment WORM = new Worm(new NamespacedKey(this, "worm_fishchantment"));
    public final Enchantment CRUSH = new Crush(new NamespacedKey(this, "CRUSH_fishchantment"));
    public final Enchantment LIFE_STEAL = new LifeSteal(new NamespacedKey(this, "life_steal_fishchantment"));
    public final Enchantment FLING = new Fling(new NamespacedKey(this, "fling_fishchantment"));
    public final Enchantment ENLIGHTENMENT = new Enlightenment(new NamespacedKey(this, "enlightenment_fishchantment"));
    public final Enchantment RANGE = new Range(new NamespacedKey(this, "range_fishchantment"));
    public final Enchantment ACCURATE = new Accurate(new NamespacedKey(this, "accurate_fishchantment"));
    public final Enchantment POISON = new Poison(new NamespacedKey(this, "poison_fishchantment"));
    public final Enchantment WITHER = new Wither(new NamespacedKey(this, "wither_fishchantment"));
    public final Enchantment BLOODTIPPED = new BloodTipped(new NamespacedKey(this, "blood_tipped_fishchantment"));
    public final Enchantment VOLLEY = new Volley(new NamespacedKey(this, "volley_fishchantment"));
    public final Enchantment GLASS = new Glass(new NamespacedKey(this, "glass_fishchantment"));
    public final Enchantment LEVITATION = new Levitation(new NamespacedKey(this, "helium_fishchantment"));
    public final Enchantment GLOWING = new Glowing(new NamespacedKey(this, "glowing_fishchantment"));
    public final Enchantment BLINDNESS = new Blindness(new NamespacedKey(this, "blindness_fishchantment"));
    public final Enchantment CONFUSION = new Confusion(new NamespacedKey(this, "confusion_fishchantment"));
    public final Enchantment WEAKNESS = new Weakness(new NamespacedKey(this, "weakness_fishchantment"));
    public final Enchantment HUNGER = new Hunger(new NamespacedKey(this, "hunger_fishchantment"));
    public final Enchantment SLOWNESS = new Slowness(new NamespacedKey(this, "slowness_fishchantment"));
    public final Enchantment SPEED = new Speed(new NamespacedKey(this, "speed_fishchantment"));
    public final Enchantment JUMP = new Jump(new NamespacedKey(this, "jump_fishchantment"));
    public final Enchantment SLOW_FALL = new SlowFall(new NamespacedKey(this, "slow_fall_fishchantment"));
    public final Enchantment RESISTANCE = new Resistance(new NamespacedKey(this, "resistance_fishchantment"));
    public final Enchantment REGENERATION = new Regeneration(new NamespacedKey(this, "regeneration_fishchantment"));
    public final Enchantment INVISIBILITY = new Invisibility(new NamespacedKey(this, "invisibility_fishchantment"));
    public final Enchantment MOMENTUM = new Momentum(new NamespacedKey(this, "momentum_fishchantment"));
    public final Enchantment BOOSTERS = new Boosters(new NamespacedKey(this, "boosters_fishchantment"));
    public final Enchantment REFLECTION = new Reflection(new NamespacedKey(this, "reflection_fishchantment"));
    public final Enchantment DEATHWISH = new DeathWish(new NamespacedKey(this, "death_wish_fishchantment"));
    public final Enchantment FIRE_RESISTANCE = new FireResistance(new NamespacedKey(this, "fire_resistance_fishchantment"));
    public final Enchantment WATER_BREATHING = new WaterBreathing(new NamespacedKey(this, "water_breathing_fishchantment"));
    public final Enchantment STRENGTH = new Strength(new NamespacedKey(this, "strength_fishchantment"));
    public final Enchantment HASTE = new Haste(new NamespacedKey(this, "haste_fishchantment"));
    public final Enchantment HEALTH_BOOST = new HealthBoost(new NamespacedKey(this, "health_boost_fishchantment"));
    public final Enchantment NIGHT_VISION = new NightVision(new NamespacedKey(this, "night_vision_fishchantment"));
    public final Enchantment DOLPHINS_GRACE = new DolphinsGrace(new NamespacedKey(this, "dolphins_grace_fishchantment"));
    public final Enchantment CONDUIT_POWER = new ConduitPower(new NamespacedKey(this, "conduit_power_fishchantment"));
    public final Enchantment HERO_OF_THE_VILLAGE = new HeroOfTheVillage(new NamespacedKey(this, "hero_of_the_village_fishchantment"));
    public final Enchantment MINING_FATIGUE_CURSE = new MiningFatigueCurse(new NamespacedKey(this, "mining_fatigue_curse_fishchantment"));
    public final Enchantment SLOWNESS_CURSE = new SlownessCurse(new NamespacedKey(this, "slowness_curse_fishchantment"));
    public final Enchantment WEAKNESS_CURSE = new WeaknessCurse(new NamespacedKey(this, "weakness_curse_fishchantment"));
    public final Enchantment LEVITATIONCURSE = new LevitationCurse(new NamespacedKey(this, "levitation_curse_fishchantment"));
    public final Enchantment AQUAPHOBIACURSE = new AquaphobiaCurse(new NamespacedKey(this, "aquaphobia_curse_fishchantment"));
    
    public void onEnable() {
        register(DESTRUCTIVE);
        register(TILLING);
        register(REPLANTING);
        register(EXCAVATING);
        register(TELEKINESIS);
        register(UNBREAKABLE);
        register(FOOD);
        register(WORM);
        register(CRUSH);
        register(LIFE_STEAL);
        register(FLING);
        register(ENLIGHTENMENT);
        register(RANGE);
        register(ACCURATE);
        register(POISON);
        register(WITHER);
        register(BLOODTIPPED);
        register(VOLLEY);
        register(GLASS);
        register(LEVITATION);
        register(GLOWING);
        register(BLINDNESS);
        register(CONFUSION);
        register(WEAKNESS);
        register(HUNGER);
        register(SLOWNESS);
        register(SPEED);
        register(JUMP);
        register(SLOW_FALL);
        register(RESISTANCE);
        register(REGENERATION);
        register(INVISIBILITY);
        register(MOMENTUM);
        register(BOOSTERS);
        register(REFLECTION);
        register(DEATHWISH);
        register(FIRE_RESISTANCE);
        register(WATER_BREATHING);
        register(STRENGTH);
        register(HASTE);
        register(HEALTH_BOOST);
        register(NIGHT_VISION);
        register(DOLPHINS_GRACE);
        register(CONDUIT_POWER);
        register(HERO_OF_THE_VILLAGE);
        register(MINING_FATIGUE_CURSE);
        register(SLOWNESS_CURSE);
        register(WEAKNESS_CURSE);
        register(LEVITATIONCURSE);
        register(AQUAPHOBIACURSE);
        getCommand("fenchant").setExecutor(new Fenchant(this));
        getServer().getPluginManager().registerEvents(new ProjectileHit(this), this);
        getServer().getPluginManager().registerEvents(new AttackEntity(this), this);
        getServer().getPluginManager().registerEvents(new BlockDropItems(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(this), this);
        getServer().getPluginManager().registerEvents(new FireProjectile(this), this);
        getServer().getPluginManager().registerEvents(new Glide(this), this);
        getServer().getPluginManager().registerEvents(new Till(this), this);
        getServer().getPluginManager().registerEvents(new HungerChange(this), this);
        getServer().getPluginManager().registerEvents(new Fall(this), this);
        getServer().getPluginManager().registerEvents(new ClickBlock(this), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(this), this);
        getServer().getPluginManager().registerEvents(new Suffocation(this), this);
        getServer().getPluginManager().registerEvents(new PrepareAnvil(this), this);
        getServer().getPluginManager().registerEvents(new Grindstone(this), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
        startTimer(this);
        LOGGER.info("Fishchantments enabled");
    }
    
    public void onDisable() {
        LOGGER.info("Fishchantments disabled");
    }
    
    public static Settings getSettings() {
        return settings;
    }
    
    public static void reload() {
        settings = new Settings();
    }
    
    public static void fixEnchant(ItemStack item, Enchantment enchantment, Integer level) {
        if (item == null) return;
        if (level < 1) return;
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else item.addUnsafeEnchantment(enchantment, level);
    }
    
    @SuppressWarnings("deprecation")
    public static String lore(Enchantment enchantment, Integer level) {
        if (level < 0) return null;
        String lore = enchantment.isCursed() ? ChatColor.RED + enchantment.getName() : ChatColor.GRAY + enchantment.getName();
        if (level == 1) return lore;
        else return lore + " " + (getSettings().NUMERALS ? Utl.Nchnt.numeral(level) : level.toString());
    }

    public boolean hasFishchantments(ItemStack item) {
        return getFishchantments(item).size() > 0;
    }
    
    public ArrayList<Enchantment> getFishchantments() {
        return new ArrayList<>(fishchantments);
    }
    
    public ArrayList<Enchantment> getFishchantments(ItemStack item) {
        ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
        if (item == null) return foundFishchantments;
        if (!item.hasItemMeta()) return foundFishchantments;
        Iterator<Enchantment> enchantments = Utl.Nchnt.enchantments(item).iterator();
        while (enchantments.hasNext()) {
            Enchantment enchantment = enchantments.next();
            if (isFishchantment(enchantment)) foundFishchantments.add(enchantment);
        }
        return foundFishchantments;
    }
    
    public boolean isFishchantment(Enchantment enchantment) {
        if (enchantment == null) return false;
        Iterator<Enchantment> fishchantments = getFishchantments().iterator();
        while (fishchantments.hasNext()) {
            Enchantment fishchantment = fishchantments.next();
            if (Utl.Nchnt.same(fishchantment, enchantment)) return true;
        }
        return false;
    }
    
    public boolean hasConflictingFishchantments(ItemStack item, Enchantment enchantment) {
        Iterator<Enchantment> iter = Utl.Nchnt.enchantments(item).iterator();
        while (iter.hasNext()) {
            Enchantment ienchantment = iter.next();
            if (Utl.Nchnt.same(ienchantment, enchantment)) continue;
            if (enchantment.conflictsWith(ienchantment) || ienchantment.conflictsWith(enchantment)) return true;
        }
        return false;
    }
    
    public boolean canMerge(ItemStack itemA, ItemStack itemB) {
        if (itemA == null || itemB == null) return false;
        if (itemA.getAmount() != 1 || itemB.getAmount() != 1) return false;
        if (!(itemA.getType() == itemB.getType() || itemB.getType() == Material.ENCHANTED_BOOK)) return false;
        if (itemA.getMaxStackSize() != 1) return false;  
        return true;
    }
    
    public boolean addEnchant(ItemStack item, Enchantment enchantment, Integer level, boolean force, boolean combine) {
        // Verify
        if (item == null) return false;
        if (level < 1) return false;
        if (level > 255) level = 255;
        int currentLevel = Utl.Nchnt.level(item, enchantment);
        if (!force) {
            if (level < currentLevel) return false;
            if (!enchantment.canEnchantItem(item)) return false;
            if (hasConflictingFishchantments(item, enchantment)) return false;
        }
        
        // Add Enchantment
        if (combine && level == currentLevel && currentLevel < enchantment.getMaxLevel()) level++; // Combine enchantments
        removeEnchant(item, enchantment); // Remove old lore
        if (getSettings().UNBREAKABLE_REMOVES_ENCHANTMENTS && Utl.Nchnt.same(enchantment, UNBREAKABLE)) { // Remove overriden enchantments
            removeEnchant(item, Enchantment.DURABILITY);
            removeEnchant(item, Enchantment.MENDING);
        }
        if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(enchantment, level, true);
            item.setItemMeta(meta);
        }
        else item.addUnsafeEnchantment(enchantment, level);
        
        // Add Lore
        if (!isFishchantment(enchantment)) return true;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) lore = new ArrayList<String>();
        lore.add(0, lore(enchantment, level));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return true;
    }
    
    public boolean removeEnchant(ItemStack item, Enchantment enchantment) {
        if (item == null) return false;
        final boolean HASENCHANT = Utl.Nchnt.has(item, enchantment);
        if (HASENCHANT) {
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                meta.removeStoredEnchant(enchantment);
                item.setItemMeta(meta);
            }
            else item.removeEnchantment(enchantment);
        }
        
        // Remove Lore
        if (!isFishchantment(enchantment)) return HASENCHANT;
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) return HASENCHANT;
        List<String> newLore = new ArrayList<>();
        String enchantLore = lore(enchantment, 1);
        for (String line : lore) {
            if (!line.contains(enchantLore)) newLore.add(line);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        return HASENCHANT;
    }
    
    public ItemStack enchantedBook(Enchantment enchantment, int level) {
        ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
        addEnchant(enchantedBook, enchantment, level, true, false);
        return enchantedBook;
    }

    public void breakBlock(Player player, Block block) {
        breakBlock(player, block, null);
    }
    
    public void breakBlock(Player player, Block block, ItemStack item) {
        if (!Utl.PrmChkr.canBreak(player, block)) return;
        blockDrops(player, block, item);
        block.setType(Material.AIR);
    }

    public void blockDrops(Player player, Block block) {
        blockDrops(player, block, null);
    }
    
    public void blockDrops(Player player, Block block, ItemStack item) {
        Collection<ItemStack> drops; 
        if (item == null) drops = block.getDrops();
        else drops = block.getDrops(item);
        if (drops.isEmpty()) return;
        Telekinesis.onBlockDropItems(this, player, drops);
        if (drops.isEmpty()) return;
        World world = block.getWorld();
        for (ItemStack drop : drops) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
    }

    public void verify(ItemStack item) {
        if (item == null) return;
        Iterator<Enchantment> iter = getFishchantments().iterator();
        while (iter.hasNext()) {
            Utl.Nchnt.level(item, iter.next());
        }
    }
    
    private void register(Enchantment enchant) {
        fishchantments.add(enchant);
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchant); 
        } 
        catch (Exception e) {
            LOGGER.warning("Failed to load enchant " + enchant.toString() + ": " + e.getMessage());
        }
    }
    
    private void startTimer(final Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                Player player;
                while (players.hasNext()) {
                    player = players.next();
                    ItemStack helmet = player.getInventory().getHelmet();
                    ItemStack chestplate = player.getInventory().getChestplate();
                    ItemStack leggings = player.getInventory().getLeggings();
                    ItemStack boots = player.getInventory().getBoots();
                    Glowing.onTimer(plugin, player);
                    Invisibility.onTimer(plugin, player);
                    MiningFatigueCurse.onTimer(plugin, player);
                    SlownessCurse.onTimer(plugin, player);
                    WeaknessCurse.onTimer(plugin, player);
                    LevitationCurse.onTimer(plugin, player);
                    WaterBreathing.onTimer(plugin, player, helmet);
                    NightVision.onTimer(plugin, player, helmet);
                    ConduitPower.onTimer(plugin, player, helmet);
                    Resistance.onTimer(plugin, player, chestplate);
                    FireResistance.onTimer(plugin, player, chestplate);
                    Regeneration.onTimer(plugin, player, chestplate);
                    HealthBoost.onTimer(plugin, player, chestplate);
                    Strength.onTimer(plugin, player, chestplate);
                    Haste.onTimer(plugin, player, chestplate);
                    HeroOfTheVillage.onTimer(plugin, player, chestplate);
                    DolphinsGrace.onTimer(plugin, player, leggings);
                    Speed.onTimer(plugin, player, leggings); 
                    Jump.onTimer(plugin, player, boots); 
                    SlowFall.onTimer(plugin, player, boots);
                }
            }
        }, getSettings().ARMOR_EFFECTS_PERIOD, getSettings().ARMOR_EFFECTS_PERIOD);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                Player player;
                while (players.hasNext()) {
                    player = players.next();
                    AquaphobiaCurse.onTimer(plugin, player);
                }
            }
        }, 40, 40);
    }
}
