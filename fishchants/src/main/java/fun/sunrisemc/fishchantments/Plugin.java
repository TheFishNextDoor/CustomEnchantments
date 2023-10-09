package fun.sunrisemc.fishchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
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
  private ArrayList<Enchantment> fishchantments = new ArrayList<>();

  public final Enchantment DESTRUCTIVE = new Destructive(new NamespacedKey(this, "destructive_fishchantment"));
  public final Enchantment TILLING = new Tilling(new NamespacedKey(this, "tilling_fishchantment"));
  public final Enchantment REPLANTING = new Replanting(new NamespacedKey(this, "replanting_fishchantment"));
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
    getCommand("fenchant").setExecutor(new CommandHandler(this));
    getServer().getPluginManager().registerEvents(new EventListener(this), this);
    LOGGER.info("Fishchantments enabled");
  }

  public void onDisable() {
    LOGGER.info("Fishchants disabled");
  }

  public ArrayList<Enchantment> getFishchantments() {
    return fishchantments;
  }

  public boolean hasFishchantment(ItemStack item) {
    Iterator<Enchantment> enchants = item.getItemMeta().getEnchants().keySet().iterator();
    while (enchants.hasNext()) {
      if (isFishchantment(enchants.next())) return true;
    }
    return false;
  }

  public boolean isFishchantment(Enchantment enchantment) {
    Iterator<Enchantment> iter = fishchantments.iterator();
    while (iter.hasNext()) {
      if (iter.next() == enchantment) return true;
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

  private void register(Enchantment enchant) {
    fishchantments.add(enchant);
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
}
