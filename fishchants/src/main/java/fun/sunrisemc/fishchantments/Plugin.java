package fun.sunrisemc.fishchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fun.sunrisemc.fishchantments.CommandHandler.FishchantmentCommandData;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Accurate;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Blindness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Confusion;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Glow;
import fun.sunrisemc.fishchantments.EnchantDefinitions.GrassSeeds;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Helium;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Hunger;
import fun.sunrisemc.fishchantments.EnchantDefinitions.LifeSteal;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Poison;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Range;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Slowness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Unbreakable;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Weakness;
import fun.sunrisemc.fishchantments.EnchantDefinitions.Wither;

public class Plugin extends JavaPlugin {
  private static final boolean NUMERALS = true;
  private static final Logger LOGGER = Logger.getLogger("Fishchantments");
  private CommandHandler commandHandler;

  public final NamespacedKey GRASS_SEEDS_KEY = new NamespacedKey(this, "grass_seeds_fishchantment");
  public final NamespacedKey UNBREAKABLE_KEY = new NamespacedKey(this, "unbreakable_fishchantment");
  public final NamespacedKey LIFE_STEAL_KEY = new NamespacedKey(this, "life_steal_fishchantment");
  public final NamespacedKey RANGE_KEY = new NamespacedKey(this, "range_fishchantment");
  public final NamespacedKey ACCURATE_KEY = new NamespacedKey(this, "accurate_fishchantment");
  public final NamespacedKey POISON_KEY = new NamespacedKey(this, "poison_fishchantment");
  public final NamespacedKey WITHER_KEY = new NamespacedKey(this, "wither_fishchantment");
  public final NamespacedKey HELIUM_KEY = new NamespacedKey(this, "helium_fishchantment");
  public final NamespacedKey GLOW_KEY = new NamespacedKey(this, "glow_fishchantment");
  public final NamespacedKey BLINDNESS_KEY = new NamespacedKey(this, "blindness_fishchantment");
  public final NamespacedKey CONFUSION_KEY = new NamespacedKey(this, "confusion_fishchantment");
  public final NamespacedKey WEAKNESS_KEY = new NamespacedKey(this, "weakness_fishchantment");
  public final NamespacedKey HUNGER_KEY = new NamespacedKey(this, "hunger_fishchantment");
  public final NamespacedKey SLOWNESS_KEY  = new NamespacedKey(this, "slowness_fishchantment");

  public final Enchantment GRASS_SEEDS = new GrassSeeds(GRASS_SEEDS_KEY);
  public final Enchantment UNBREAKABLE = new Unbreakable(UNBREAKABLE_KEY);
  public final Enchantment LIFE_STEAL = new LifeSteal(LIFE_STEAL_KEY);
  public final Enchantment RANGE = new Range(RANGE_KEY);
  public final Enchantment ACCURATE = new Accurate(ACCURATE_KEY);
  public final Enchantment POISON = new Poison(POISON_KEY);
  public final Enchantment WITHER = new Wither(WITHER_KEY);
  public final Enchantment HELIUM = new Helium(HELIUM_KEY);
  public final Enchantment GLOW = new Glow(GLOW_KEY);
  public final Enchantment BLINDNESS = new Blindness(BLINDNESS_KEY);
  public final Enchantment CONFUSION = new Confusion(CONFUSION_KEY);
  public final Enchantment WEAKNESS = new Weakness(WEAKNESS_KEY);
  public final Enchantment HUNGER = new Hunger(HUNGER_KEY);
  public final Enchantment SLOWNESS = new Slowness(SLOWNESS_KEY);

  public void onEnable() {
    register(GRASS_SEEDS);
    register(UNBREAKABLE);
    register(LIFE_STEAL);
    register(RANGE);
    register(ACCURATE);
    register(POISON);
    register(WITHER);
    register(HELIUM);
    register(GLOW);
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

  public static ItemStack addEnchant(ItemStack item, FishchantmentCommandData data, Integer level) {
    if (hasEnchant(item, data.getEnchantment())) removeEnchant(item, data);
    if (level < 1) return item;
    if (level > 10) level = 10;
    item.addEnchantment(data.getEnchantment(), level);
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    if (lore == null) lore = new ArrayList<String>();
    if (level == 1) lore.add(0, data.getLore());
    else {
        String levelString = NUMERALS ? numberToNumeral(level) : level.toString();
        lore.add(0, data.getLore() + " " + levelString);
    }
    meta.setLore(lore);
    item.setItemMeta(meta);
    return item;
  }

  public static ItemStack removeEnchant(ItemStack item, FishchantmentCommandData data) {
    if (!hasEnchant(item, data.getEnchantment())) return item;
    item.removeEnchantment(data.getEnchantment());
    ItemMeta meta = item.getItemMeta();
    List<String> lore = meta.getLore();
    if (lore == null) return item;
    List<String> newLore = new ArrayList<>();
    for (String line : lore) {
        if (!line.contains(data.getLore())) newLore.add(line);
    }
    meta.setLore(newLore);
    item.setItemMeta(meta);
    return item;
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

  static int numeralToNumber(String numeral) {
    switch (numeral.toUpperCase()) {
      case "X": return 10;
      case "IX": return 9;
      case "VIII": return 8;
      case "VII": return 7;
      case "VI": return 6;
      case "V": return 5;
      case "IV": return 4;
      case "III": return 3;
      case "II": return 2;
      case "I": return 1;
      default: return 0;
    }
  }

  public static String numberToNumeral(int number) {
    switch (number) {
      case 10: return "X";
      case 9: return "IX";
      case 8: return "VIII";
      case 7: return "VII";
      case 6: return "VI";
      case 5: return "V";
      case 4: return "IV";
      case 3: return "III";
      case 2: return "II";
      case 1: return "I";
      default: return "";
    }
  }

  public static ItemStack getItemInHand(Player player) {
    PlayerInventory inv = player.getInventory();
    ItemStack mainHand = inv.getItemInMainHand();
    ItemStack offHand = inv.getItemInOffHand();
    if (mainHand.getType() != Material.AIR) return mainHand;
    if (offHand.getType() != Material.AIR) return offHand;
    return mainHand;
  }
}
