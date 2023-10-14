package fun.sunrisemc.fishchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.ConduitPower;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glowing;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Haste;
import fun.sunrisemc.fishchantments.EnchantDefinitions.HealthBoost;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Destructive;
import fun.sunrisemc.fishchantments.EnchantDefinitions.DolphinsGrace;
import fun.sunrisemc.fishchantments.EnchantDefinitions.FireResistance;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Tilling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Replanting;
import fun.sunrisemc.fishchantments.EnchantDefinitions.SlowFall;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.HeroOfTheVillage;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Invisibility;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.MiningFatigueCurse;
import fun.sunrisemc.fishchantments.EnchantDefinitions.NightVision;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Fling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.SlownessCurse;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Speed;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Strength;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.WaterBreathing;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Food;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Worm;
import net.md_5.bungee.api.ChatColor;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Crush;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.WeaknessCurse;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Jump;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Resistance;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Regeneration;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Excavating;;

public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("Fishchantments");
  private static final boolean NUMERALS = false;
  private ArrayList<Enchantment> fishchantments = new ArrayList<>();

  public final Enchantment DESTRUCTIVE = new Destructive(new NamespacedKey(this, "destructive_fishchantment"));
  public final Enchantment TILLING = new Tilling(new NamespacedKey(this, "tilling_fishchantment"));
  public final Enchantment REPLANTING = new Replanting(new NamespacedKey(this, "replanting_fishchantment"));
  public final Enchantment EXCAVATING = new Excavating(new NamespacedKey(this, "excavating_fishchantment"));
  public final Enchantment UNBREAKABLE = new Unbreakable(new NamespacedKey(this, "unbreakable_fishchantment"));
  public final Enchantment FOOD = new Food(new NamespacedKey(this, "food_fishchantment"));
  public final Enchantment WORM = new Worm(new NamespacedKey(this, "worm_fishchantment"));
  public final Enchantment CRUSH = new Crush(new NamespacedKey(this, "CRUSH_fishchantment"));
  public final Enchantment LIFE_STEAL = new LifeSteal(new NamespacedKey(this, "life_steal_fishchantment"));
  public final Enchantment FLING = new Fling(new NamespacedKey(this, "fling_fishchantment"));
  public final Enchantment RANGE = new Range(new NamespacedKey(this, "range_fishchantment"));
  public final Enchantment ACCURATE = new Accurate(new NamespacedKey(this, "accurate_fishchantment"));
  public final Enchantment POISON = new Poison(new NamespacedKey(this, "poison_fishchantment"));
  public final Enchantment WITHER = new Wither(new NamespacedKey(this, "wither_fishchantment"));
  public final Enchantment HELIUM = new Helium(new NamespacedKey(this, "helium_fishchantment"));
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

  public void onEnable() {
    register(DESTRUCTIVE);
    register(TILLING);
    register(REPLANTING);
    register(EXCAVATING);
    register(UNBREAKABLE);
    register(FOOD);
    register(WORM);
    register(CRUSH);
    register(LIFE_STEAL);
    register(FLING);
    register(RANGE);
    register(ACCURATE);
    register(POISON);
    register(WITHER);
    register(HELIUM);
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
    getCommand("fenchant").setExecutor(new CommandHandler(this));
    getServer().getPluginManager().registerEvents(new EventListener(this), this);
    startTimer(this);
    LOGGER.info("Fishchantments enabled");
  }

  public void onDisable() {
    LOGGER.info("Fishchants disabled");
  }

  public boolean hasFishchantment(ItemStack item) {
    return getFishchantments(item).size() > 0;
  }

  public ArrayList<Enchantment> getFishchantments() {
    return new ArrayList<>(fishchantments);
  }
  
  public ArrayList<Enchantment> getFishchantments(ItemStack item) {
    ArrayList<Enchantment> foundFishchantments = new ArrayList<>();
    if (item == null) return foundFishchantments;
    if (!item.hasItemMeta()) return foundFishchantments;
    Iterator<Enchantment> enchantments = Utl.Ench.getEnchantments(item).iterator();
    while (enchantments.hasNext()) {
      Enchantment enchantment = enchantments.next();
      if (isFishchantment(enchantment)) foundFishchantments.add(enchantment);
    }
    return foundFishchantments;
  }

  @SuppressWarnings("deprecation")
  public boolean isFishchantment(Enchantment enchantment) {
    if (enchantment == null) return false;
    Iterator<Enchantment> fishchantments = getFishchantments().iterator();
    while (fishchantments.hasNext()) {
      String name = fishchantments.next().getName();
      if (name.equals(enchantment.getName())) return true;
    }
    return false;
  }

  public boolean addEnchant(ItemStack item, Enchantment enchantment, Integer level, boolean force, boolean combine) {
    if (item == null) return false;
    if (level < 1) return false;
    if (!force && Utl.Ench.hasConflictingEnchantments(item, enchantment)) return false;
    int currentLevel = Utl.Ench.getEnchantLevel(item, enchantment);
    if (!force && level < currentLevel) return false;
    if (combine && level == currentLevel && currentLevel < enchantment.getMaxLevel()) level++;
    removeEnchant(item, enchantment);
    if (item.getType() == Material.ENCHANTED_BOOK) {
      EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
      meta.addStoredEnchant(enchantment, level, true);
      item.setItemMeta(meta);
    }
    else {
      try { item.addUnsafeEnchantment(enchantment, level); }
      catch (Exception e) { return false; }
    }

    // Lore
    if (!isFishchantment(enchantment)) return true;
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    if (lore == null) lore = new ArrayList<String>();
    lore.add(0, getLore(enchantment, level));
    meta.setLore(lore);
    item.setItemMeta(meta);
    return true;
  }

  public boolean removeEnchant(ItemStack item, Enchantment enchantment) {
    if (item == null) return false;
    final boolean HASENCHANT = Utl.Ench.hasEnchant(item, enchantment);
    if (HASENCHANT) {
      if (item.getType() == Material.ENCHANTED_BOOK) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.removeStoredEnchant(enchantment);
        item.setItemMeta(meta);
      }
      else item.removeEnchantment(enchantment);
    }

    // Lore
    if (!isFishchantment(enchantment)) return HASENCHANT;
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    if (lore == null) return HASENCHANT;
    List<String> newLore = new ArrayList<>();
    String enchantLore = getLore(enchantment, 1);
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

  @SuppressWarnings("deprecation")
  private static String getLore(Enchantment enchantment, Integer level) {
    if (level < 0) return null;
    String lore = enchantment.isCursed() ? ChatColor.RED + enchantment.getName() : ChatColor.GRAY + enchantment.getName();
    if (level == 1) return lore;
    else return lore + " " + (NUMERALS ? Utl.Ench.numberToNumeral(level) : level.toString());
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
          Speed.onTimer(plugin, player, leggings); 
          Jump.onTimer(plugin, player, boots); 
          SlowFall.onTimer(plugin, player, boots); 
          Resistance.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          Regeneration.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          Invisibility.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          Glowing.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          FireResistance.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          WaterBreathing.onTimer(plugin, player, helmet);
          Strength.onTimer(plugin, player, chestplate);
          Haste.onTimer(plugin, player, chestplate);
          HealthBoost.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          NightVision.onTimer(plugin, player, helmet);
          Helium.onTimer(plugin, player, boots);
          DolphinsGrace.onTimer(plugin, player, leggings);
          ConduitPower.onTimer(plugin, player, helmet);
          HeroOfTheVillage.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          MiningFatigueCurse.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          SlownessCurse.onTimer(plugin, player, helmet, chestplate, leggings, boots);
          WeaknessCurse.onTimer(plugin, player, helmet, chestplate, leggings, boots);
        }
      }
    }, 142L, 100L);
  }
}
