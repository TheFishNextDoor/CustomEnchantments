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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.commands.Fenchant;
import fun.sunrisemc.fishchantments.enchantments.specialties.Elytra.Momentum;
import fun.sunrisemc.fishchantments.enchantments.specialties.Elytra.Boosters;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfMiningFatigue;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfSlowness;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfWeakness;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfLevitating;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfAquaphobia;
import fun.sunrisemc.fishchantments.enchantments.Curses.CurseOfRadiance;
import fun.sunrisemc.fishchantments.enchantments.Generic.Precision;
import fun.sunrisemc.fishchantments.enchantments.Generic.AquaAspect;
import fun.sunrisemc.fishchantments.enchantments.Generic.Destructive;
import fun.sunrisemc.fishchantments.enchantments.Generic.Excavating;
import fun.sunrisemc.fishchantments.enchantments.Generic.Telekinesis;
import fun.sunrisemc.fishchantments.enchantments.Generic.Fling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Enlightenment;
import fun.sunrisemc.fishchantments.enchantments.Generic.Radiance;
import fun.sunrisemc.fishchantments.enchantments.Generic.Invisibility;
import fun.sunrisemc.fishchantments.enchantments.Generic.Reflection;
import fun.sunrisemc.fishchantments.enchantments.Generic.Range;
import fun.sunrisemc.fishchantments.enchantments.Generic.Replanting;
import fun.sunrisemc.fishchantments.enchantments.Generic.Spurs;
import fun.sunrisemc.fishchantments.enchantments.Generic.Tilling;
import fun.sunrisemc.fishchantments.enchantments.Generic.Unbreakable;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Crush;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Leaping;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.Anchor;
import fun.sunrisemc.fishchantments.enchantments.specialties.Boot.SlowFalling;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.FireResistance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Haste;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.IncreasedHealth;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.HeroOfTheVillage;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Healing;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.DeathWish;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.DragonScales;
import fun.sunrisemc.fishchantments.enchantments.specialties.Chestplate.Strength;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.ConduitPower;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Sustenance;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.NightVision;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Gills;
import fun.sunrisemc.fishchantments.enchantments.specialties.Helmet.Worm;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.DolphinsGrace;
import fun.sunrisemc.fishchantments.enchantments.specialties.Legging.Swiftness;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Obscure;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Disorienting;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Levitating;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Starving;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.LifeSteal;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Venom;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Crippling;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Debilitating;
import fun.sunrisemc.fishchantments.enchantments.specialties.Weapon.Withering;
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
import fun.sunrisemc.fishchantments.events.Move;
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
    
    public final Enchantment DESTRUCTIVE = new Destructive(new NamespacedKey(this, "destructive"));
    public final Enchantment TILLING = new Tilling(new NamespacedKey(this, "tilling"));
    public final Enchantment REPLANTING = new Replanting(new NamespacedKey(this, "replanting"));
    public final Enchantment EXCAVATING = new Excavating(new NamespacedKey(this, "excavating"));
    public final Enchantment TELEKINESIS = new Telekinesis(new NamespacedKey(this, "telekinesis"));
    public final Enchantment UNBREAKABLE = new Unbreakable(new NamespacedKey(this, "unbreakable"));
    public final Enchantment SUSTENANCE = new Sustenance(new NamespacedKey(this, "sustenance"));
    public final Enchantment WORM = new Worm(new NamespacedKey(this, "worm"));
    public final Enchantment CRUSH = new Crush(new NamespacedKey(this, "crush"));
    public final Enchantment LIFE_STEAL = new LifeSteal(new NamespacedKey(this, "life_steal"));
    public final Enchantment FLING = new Fling(new NamespacedKey(this, "fling"));
    public final Enchantment ENLIGHTENMENT = new Enlightenment(new NamespacedKey(this, "enlightenment"));
    public final Enchantment RANGE = new Range(new NamespacedKey(this, "range"));
    public final Enchantment PRECISION = new Precision(new NamespacedKey(this, "precision"));
    public final Enchantment POISON = new Venom(new NamespacedKey(this, "venom"));
    public final Enchantment WITHER = new Withering(new NamespacedKey(this, "withering"));
    public final Enchantment BLOODTIPPED = new BloodTipped(new NamespacedKey(this, "blood_tipped"));
    public final Enchantment VOLLEY = new Volley(new NamespacedKey(this, "volley"));
    public final Enchantment GLASS = new Glass(new NamespacedKey(this, "glass"));
    public final Enchantment LEVITATING = new Levitating(new NamespacedKey(this, "levitating"));
    public final Enchantment RADIANCE = new Radiance(new NamespacedKey(this, "radiance"));
    public final Enchantment OBSCURE = new Obscure(new NamespacedKey(this, "obscure"));
    public final Enchantment DISORIENTING = new Disorienting(new NamespacedKey(this, "disorienting"));
    public final Enchantment DEBILITATING = new Debilitating(new NamespacedKey(this, "debilitating"));
    public final Enchantment STARVING = new Starving(new NamespacedKey(this, "starving"));
    public final Enchantment CRIPPLING = new Crippling(new NamespacedKey(this, "crippling"));
    public final Enchantment SWIFTNESS = new Swiftness(new NamespacedKey(this, "swiftness"));
    public final Enchantment LEAPING = new Leaping(new NamespacedKey(this, "leaping"));
    public final Enchantment SLOW_FALLING = new SlowFalling(new NamespacedKey(this, "slow_falling"));
    public final Enchantment DRAGON_SCALES = new DragonScales(new NamespacedKey(this, "dragon_scales"));
    public final Enchantment HEALING = new Healing(new NamespacedKey(this, "healing"));
    public final Enchantment INVISIBILITY = new Invisibility(new NamespacedKey(this, "invisibility"));
    public final Enchantment MOMENTUM = new Momentum(new NamespacedKey(this, "momentum"));
    public final Enchantment BOOSTERS = new Boosters(new NamespacedKey(this, "boosters"));
    public final Enchantment REFLECTION = new Reflection(new NamespacedKey(this, "reflection"));
    public final Enchantment DEATHWISH = new DeathWish(new NamespacedKey(this, "death_wish"));
    public final Enchantment FIRE_RESISTANCE = new FireResistance(new NamespacedKey(this, "fire_resistance"));
    public final Enchantment WATER_BREATHING = new Gills(new NamespacedKey(this, "gills"));
    public final Enchantment STRENGTH = new Strength(new NamespacedKey(this, "strength"));
    public final Enchantment HASTE = new Haste(new NamespacedKey(this, "haste"));
    public final Enchantment INCREASED_HEALTH = new IncreasedHealth(new NamespacedKey(this, "increased_health"));
    public final Enchantment NIGHT_VISION = new NightVision(new NamespacedKey(this, "night_vision"));
    public final Enchantment DOLPHINS_GRACE = new DolphinsGrace(new NamespacedKey(this, "dolphins_grace"));
    public final Enchantment CONDUIT_POWER = new ConduitPower(new NamespacedKey(this, "conduit_power"));
    public final Enchantment HERO_OF_THE_VILLAGE = new HeroOfTheVillage(new NamespacedKey(this, "hero_of_the_village"));
    public final Enchantment CURSE_OF_MINING_FATIGUE = new CurseOfMiningFatigue(new NamespacedKey(this, "curse_of_mining_fatigue"));
    public final Enchantment CURSE_OF_SLOWNESS = new CurseOfSlowness(new NamespacedKey(this, "curse_of_slowness"));
    public final Enchantment CURSE_OF_WEAKNESS = new CurseOfWeakness(new NamespacedKey(this, "curse_of_weakness"));
    public final Enchantment CURSE_OF_LEVITATING = new CurseOfLevitating(new NamespacedKey(this, "curse_of_levitating"));
    public final Enchantment CURSE_OF_AQUAPHOBIA = new CurseOfAquaphobia(new NamespacedKey(this, "curse_of_aquaphobia"));
    public final Enchantment CURSE_OF_RADIANCE = new CurseOfRadiance(new NamespacedKey(this, "curse_of_radiance"));
    public final Enchantment ANCHOR = new Anchor(new NamespacedKey(this, "anchor"));
    public final Enchantment SPURS = new Spurs(new NamespacedKey(this, "spurs"));
    public final Enchantment AQUA_ASPECT = new AquaAspect(new NamespacedKey(this, "aqua_aspect"));
    
    public void onEnable() {
        register(DESTRUCTIVE);
        register(TILLING);
        register(REPLANTING);
        register(EXCAVATING);
        register(TELEKINESIS);
        register(UNBREAKABLE);
        register(SUSTENANCE);
        register(WORM);
        register(CRUSH);
        register(LIFE_STEAL);
        register(FLING);
        register(ENLIGHTENMENT);
        register(RANGE);
        register(PRECISION);
        register(POISON);
        register(WITHER);
        register(BLOODTIPPED);
        register(VOLLEY);
        register(GLASS);
        register(LEVITATING);
        register(RADIANCE);
        register(OBSCURE);
        register(DISORIENTING);
        register(DEBILITATING);
        register(STARVING);
        register(CRIPPLING);
        register(SWIFTNESS);
        register(LEAPING);
        register(SLOW_FALLING);
        register(DRAGON_SCALES);
        register(HEALING);
        register(INVISIBILITY);
        register(MOMENTUM);
        register(BOOSTERS);
        register(REFLECTION);
        register(DEATHWISH);
        register(FIRE_RESISTANCE);
        register(WATER_BREATHING);
        register(STRENGTH);
        register(HASTE);
        register(INCREASED_HEALTH);
        register(NIGHT_VISION);
        register(DOLPHINS_GRACE);
        register(CONDUIT_POWER);
        register(HERO_OF_THE_VILLAGE);
        register(CURSE_OF_MINING_FATIGUE);
        register(CURSE_OF_SLOWNESS);
        register(CURSE_OF_WEAKNESS);
        register(CURSE_OF_LEVITATING);
        register(CURSE_OF_AQUAPHOBIA);
        register(CURSE_OF_RADIANCE);
        register(ANCHOR);
        register(SPURS);
        register(AQUA_ASPECT);
        getCommand("fenchant").setExecutor(new Fenchant(this));
        getServer().getPluginManager().registerEvents(new ProjectileHit(this), this);
        getServer().getPluginManager().registerEvents(new AttackEntity(this), this);
        getServer().getPluginManager().registerEvents(new BlockDropItems(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(this), this);
        getServer().getPluginManager().registerEvents(new ItemDamage(this), this);
        getServer().getPluginManager().registerEvents(new FireProjectile(this), this);
        getServer().getPluginManager().registerEvents(new Move(this), this);
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
            if (!(enchantment.canEnchantItem(item) || item.getType() == Material.ENCHANTED_BOOK)) return false;
            if (hasConflictingFishchantments(item, enchantment)) return false;
        }
        
        // Add Enchantment
        if (combine && level == currentLevel && currentLevel < enchantment.getMaxLevel()) level++;
        removeEnchant(item, enchantment); // Remove old lore
        if (getSettings().REMOVE_OVERRIDDEN_ENCHANTMENTS && Utl.Nchnt.same(enchantment, UNBREAKABLE)) {
            removeEnchant(item, Enchantment.DURABILITY);
            removeEnchant(item, Enchantment.MENDING);
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof Damageable) {
                Damageable damageable = (Damageable) meta;
                damageable.setDamage(0);
                item.setItemMeta(meta);
            }
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
                    CurseOfRadiance.onTimer(plugin, player);
                    Invisibility.onTimer(plugin, player);
                    CurseOfMiningFatigue.onTimer(plugin, player);
                    CurseOfSlowness.onTimer(plugin, player);
                    CurseOfWeakness.onTimer(plugin, player);
                    CurseOfLevitating.onTimer(plugin, player);
                    Gills.onTimer(plugin, player, helmet);
                    NightVision.onTimer(plugin, player, helmet);
                    ConduitPower.onTimer(plugin, player, helmet);
                    DragonScales.onTimer(plugin, player, chestplate);
                    FireResistance.onTimer(plugin, player, chestplate);
                    Healing.onTimer(plugin, player, chestplate);
                    IncreasedHealth.onTimer(plugin, player, chestplate);
                    Strength.onTimer(plugin, player, chestplate);
                    Haste.onTimer(plugin, player, chestplate);
                    HeroOfTheVillage.onTimer(plugin, player, chestplate);
                    DolphinsGrace.onTimer(plugin, player, leggings);
                    Swiftness.onTimer(plugin, player, leggings); 
                    Leaping.onTimer(plugin, player, boots); 
                    SlowFalling.onTimer(plugin, player, boots);
                    Anchor.onTimer(plugin, player, boots);
                    Spurs.onTimer(plugin, player, boots);
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
                    CurseOfAquaphobia.onTimer(plugin, player);
                }
            }
        }, 40, 40);
    }
}
