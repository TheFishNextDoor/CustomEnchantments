package fun.sunrisemc.fishchantments;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.CommandHandler.FishchantmentCommandData;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glowing;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Destructive;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Tilling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Replanting;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Fling;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Food;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Worm;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Crush;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;

public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("Fishchantments");
  private CommandHandler commandHandler;

  public final NamespacedKey DESTRUCTIVE_KEY = new NamespacedKey(this, "destructive_fishchantment");
  public final NamespacedKey TILLING_KEY = new NamespacedKey(this, "tilling_fishchantment");
  public final NamespacedKey REPLANTING_KEY = new NamespacedKey(this, "replanting_fishchantment");
  public final NamespacedKey UNBREAKABLE_KEY = new NamespacedKey(this, "unbreakable_fishchantment");
  public final NamespacedKey FOOD_KEY = new NamespacedKey(this, "food_fishchantment");
  public final NamespacedKey WORM_KEY = new NamespacedKey(this, "worm_fishchantment");
  public final NamespacedKey CRUSH_KEY = new NamespacedKey(this, "CRUSH_fishchantment");
  public final NamespacedKey LIFE_STEAL_KEY = new NamespacedKey(this, "life_steal_fishchantment");
  public final NamespacedKey FLING_KEY = new NamespacedKey(this, "fling_fishchantment");
  public final NamespacedKey RANGE_KEY = new NamespacedKey(this, "range_fishchantment");
  public final NamespacedKey ACCURATE_KEY = new NamespacedKey(this, "accurate_fishchantment");
  public final NamespacedKey POISON_KEY = new NamespacedKey(this, "poison_fishchantment");
  public final NamespacedKey WITHER_KEY = new NamespacedKey(this, "wither_fishchantment");
  public final NamespacedKey HELIUM_KEY = new NamespacedKey(this, "helium_fishchantment");
  public final NamespacedKey GLOWING_KEY = new NamespacedKey(this, "glowing_fishchantment");
  public final NamespacedKey BLINDNESS_KEY = new NamespacedKey(this, "blindness_fishchantment");
  public final NamespacedKey CONFUSION_KEY = new NamespacedKey(this, "confusion_fishchantment");
  public final NamespacedKey WEAKNESS_KEY = new NamespacedKey(this, "weakness_fishchantment");
  public final NamespacedKey HUNGER_KEY = new NamespacedKey(this, "hunger_fishchantment");
  public final NamespacedKey SLOWNESS_KEY  = new NamespacedKey(this, "slowness_fishchantment");

  public final Enchantment DESTRUCTIVE = new Destructive(DESTRUCTIVE_KEY);
  public final Enchantment TILLING = new Tilling(TILLING_KEY);
  public final Enchantment REPLANTING = new Replanting(REPLANTING_KEY);
  public final Enchantment UNBREAKABLE = new Unbreakable(UNBREAKABLE_KEY);
  public final Enchantment FOOD = new Food(FOOD_KEY);
  public final Enchantment WORM = new Worm(WORM_KEY);
  public final Enchantment CRUSH = new Crush(CRUSH_KEY);
  public final Enchantment LIFE_STEAL = new LifeSteal(LIFE_STEAL_KEY);
  public final Enchantment FLING = new Fling(FLING_KEY);
  public final Enchantment RANGE = new Range(RANGE_KEY);
  public final Enchantment ACCURATE = new Accurate(ACCURATE_KEY);
  public final Enchantment POISON = new Poison(POISON_KEY);
  public final Enchantment WITHER = new Wither(WITHER_KEY);
  public final Enchantment HELIUM = new Helium(HELIUM_KEY);
  public final Enchantment GLOWING = new Glowing(GLOWING_KEY);
  public final Enchantment BLINDNESS = new Blindness(BLINDNESS_KEY);
  public final Enchantment CONFUSION = new Confusion(CONFUSION_KEY);
  public final Enchantment WEAKNESS = new Weakness(WEAKNESS_KEY);
  public final Enchantment HUNGER = new Hunger(HUNGER_KEY);
  public final Enchantment SLOWNESS = new Slowness(SLOWNESS_KEY);

  public void onEnable() {
    register(DESTRUCTIVE);
    register(TILLING);
    register(REPLANTING);
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

    commandHandler = new CommandHandler(this);
    getCommand("fenchant").setExecutor(commandHandler);
    commandHandler.registerEnchants();

    getServer().getPluginManager().registerEvents(new EventListener(this), this);

    LOGGER.info("Fishchantments enabled");
  }

  private void register(Enchantment enchant) {
    try {
      Field f = Enchantment.class.getDeclaredField("acceptingNew");
      f.setAccessible(true);
      f.set(null, true);
      Enchantment.registerEnchantment(enchant); 
    } 
    catch (Exception e) {
      System.out.println("Failed to load enchant " + enchant.getName() + ": " + e.getMessage());
    }
  }

  public void onDisable() {
    LOGGER.info("Fishchants disabled");
  }

  public boolean hasEnchant(ItemStack item) {
    Iterator<FishchantmentCommandData> dataIter = commandHandler.getAllData().iterator();
    while(dataIter.hasNext()) {
      if (hasEnchant(item, dataIter.next().getEnchantment())) return true;
    }
    return false;
  }

  public static boolean hasEnchant(ItemStack item, Enchantment enchant) {
    return getEnchantLevel(item, enchant) > 0;
  }

  public static int getEnchantLevel(ItemStack item, Enchantment enchant) {
    if (item == null) return 0;
    ItemMeta meta = item.getItemMeta();
    if (meta == null) return 0;
    if (!meta.hasEnchant(enchant)) return 0;
    return meta.getEnchantLevel(enchant);
  }

  public static ItemStack getItemInHand(Player player) {
    PlayerInventory inv = player.getInventory();
    ItemStack mainHand = inv.getItemInMainHand();
    ItemStack offHand = inv.getItemInOffHand();
    if (mainHand.getType() != Material.AIR) return mainHand;
    if (offHand.getType() != Material.AIR) return offHand;
    return mainHand;
  }

  public static boolean playerCanModify(Player player, Block block) {
    BlockBreakEvent event = new BlockBreakEvent(block, player);
    Bukkit.getServer().getPluginManager().callEvent(event);
    return !event.isCancelled();
  }
}
