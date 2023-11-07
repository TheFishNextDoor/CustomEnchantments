package fun.sunrisemc.fishchantments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Utl {

    public static class PrmChkr {
        private static ArrayList<BlockBreakEvent> checking = new ArrayList<>();

        public static boolean canBreak(Player player, Block block) {
            BlockBreakEvent event = new BlockBreakEvent(block, player);
            checking.add(event); 
            Bukkit.getServer().getPluginManager().callEvent(event);
            checking.remove(event);
            return !event.isCancelled();
        }

        public static boolean isReal(BlockBreakEvent event) {
            return !checking.contains(event);
        }
    }

    public static class Mat {

        public static boolean isEnchantable(Material material) {
            return isTool(material) || isWeapon(material)  || isArmor(material);
        }

        public static boolean isTool(Material material) {
            return isPickaxe(material) || isShovel(material) || isAxe(material) || isHoe(material);
        }

        public static boolean isPickaxe(Material material) {
            return material.name().endsWith("_PICKAXE"); 
        }

        public static boolean isShovel(Material material) {
            return material.name().endsWith("_SHOVEL"); 
        }

        public static boolean isHoe(Material material) {
            return material.name().endsWith("_HOE"); 
        }

        public static boolean isWeapon(Material material) {
            return isMeleeWeapon(material) || isRanged(material);
        }

        public static boolean isMeleeWeapon(Material material) {
            return isSword(material) || isAxe(material) || material == Material.TRIDENT;
        }

        public static boolean isSword(Material material) {
            return material.name().endsWith("_SWORD");
        }

        public static boolean isAxe(Material material) {
            return material.name().endsWith("_AXE");
        }

        public static boolean isRanged(Material material) {
            return material == Material.BOW || material == Material.CROSSBOW || material == Material.TRIDENT || material == Material.FISHING_ROD || material == Material.EGG || material == Material.SNOWBALL || material == Material.ENDER_PEARL || material == Material.SPLASH_POTION || material == Material.LINGERING_POTION;
        }

        public static boolean isArmor(Material material) {
            return isHelmet(material) || isChestplate(material) || isLeggings(material) || isBoots(material);
        }

        public static boolean isHelmet(Material material) {
            return material.name().endsWith("_HELMET");
        }

        public static boolean isChestplate(Material material) {
            return material.name().endsWith("_CHESTPLATE") || material == Material.ELYTRA;
        }

        public static boolean isLeggings(Material material) {
            return material.name().endsWith("_LEGGINGS");
        }

        public static boolean isBoots(Material material) {
            return material.name().endsWith("_BOOTS");
        }

    }

    public static class Nchnt {

        public static boolean has(ItemStack item, Enchantment enchant) {
            return level(item, enchant) > 0;
        }

        public static int level(ItemStack item, Enchantment enchant) {
            int level = 0;
            if (item == null) return level;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) return level;
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta bookMeta = (EnchantmentStorageMeta) meta;
                if (bookMeta.hasStoredEnchant(enchant)) level = bookMeta.getStoredEnchantLevel(enchant);
            }
            else if (meta.hasEnchant(enchant)) level = meta.getEnchantLevel(enchant);
            else if (meta.hasLore()) {
                String enchantLore = Plugin.lore(enchant, 1);
                ArrayList<String> itemLore = (ArrayList<String>) meta.getLore();
                Iterator<String> iter = itemLore.iterator();
                while (iter.hasNext()) {
                    String line = iter.next();
                    if (line.equals(enchantLore)) level = 1;
                    else if (line.startsWith(enchantLore)) level = number(line.replaceFirst(enchantLore, "").trim());
                    if (level != 0) {
                        Plugin.fixEnchant(item, enchant, level);
                        break;
                    }
                }
            }
            return level;
        }

        public static boolean holding(Player player, Enchantment enchant) {
            return handLevel(player, enchant) > 0;
        }

        public static int handLevel(Player player, Enchantment enchant) {
            return level(getItemInUse(player), enchant);
        }

        public static boolean holdingRanged(Player player, Enchantment enchant) {
            return rangedLevel(player, enchant) > 0;
        }

        public static int rangedLevel(Player player, Enchantment enchant) {
            return level(Utl.getRangedItemInUse(player), enchant);
        }

        public static int weaponLevel(Player player, Enchantment enchant, boolean ranged) {
            if (ranged) return rangedLevel(player, enchant);
            else return handLevel(player, enchant);
        }

        public static boolean holdingHoe(Player player, Enchantment enchant) {
            return hoeLevel(player, enchant) > 0;
        }

        public static int hoeLevel(Player player, Enchantment enchant) {
            return level(Utl.getHoeInUse(player), enchant);
        }

        public static boolean wearing(Player player, Enchantment enchant) {
            return armorLevel(player, enchant) > 0;
        }

        public static int armorLevel(Player player, Enchantment enchant) {
            int helmetLevel = level(player.getInventory().getHelmet(), enchant);
            int chestplateLevel = level(player.getInventory().getChestplate(), enchant);
            int leggingsLevel = level(player.getInventory().getLeggings(), enchant);
            int bootsLevel = level(player.getInventory().getBoots(), enchant);
            return Math.max(Math.max(helmetLevel, chestplateLevel), Math.max(leggingsLevel, bootsLevel));
        }

        @SuppressWarnings("deprecation")
        public static boolean same(Enchantment enchant1, Enchantment enchant2) {
            System.out.println(enchant1.getKey().getKey() + " " + enchant2.getKey().getKey());
            if (enchant1.getKey().getKey().equals(enchant2.getKey().getKey())) return true;
            String name1 = enchant1.getName(); String name2 = enchant2.getName();
            if (name1 == null || name2 == null) return false;
            if (name1.equalsIgnoreCase(name2)) return true;
            return false;
        }

        public static ArrayList<Enchantment> enchantments(ItemStack item) {
            ArrayList<Enchantment> enchantments = new ArrayList<Enchantment>();
            if (item == null) return enchantments;
            if (!item.hasItemMeta()) return enchantments;
            Iterator<Enchantment> iter;
            if (item.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                if (!meta.hasStoredEnchants()) return enchantments;
                iter = meta.getStoredEnchants().keySet().iterator();
            }
            else {
                ItemMeta meta = item.getItemMeta();
                if (!meta.hasEnchants()) return enchantments;
                iter = meta.getEnchants().keySet().iterator();
            }
            while (iter.hasNext()) {
                enchantments.add(iter.next());
            }
            return enchantments;
        }

        public static boolean sameEnchants(ItemStack itemA, ItemStack itemB) {
            if (itemA == null || itemB == null) return false;
            if (itemA.getType() != itemB.getType()) return false;
            if (itemA.getType() == Material.ENCHANTED_BOOK) {
                EnchantmentStorageMeta metaA = (EnchantmentStorageMeta) itemA.getItemMeta();
                EnchantmentStorageMeta metaB = (EnchantmentStorageMeta) itemB.getItemMeta();
                if (metaA.getStoredEnchants().size() != metaB.getStoredEnchants().size()) return false;
                Iterator<Enchantment> iter = metaA.getStoredEnchants().keySet().iterator();
                while (iter.hasNext()) {
                    Enchantment enchantment = iter.next();
                    if (!metaB.hasStoredEnchant(enchantment)) return false;
                    if (metaA.getStoredEnchantLevel(enchantment) != metaB.getStoredEnchantLevel(enchantment)) return false;
                }
            }
            else {
                if (itemA.getEnchantments().size() != itemB.getEnchantments().size()) return false;
                Iterator<Enchantment> iter = itemA.getEnchantments().keySet().iterator();
                while (iter.hasNext()) {
                    Enchantment enchantment = iter.next();
                    if (!itemB.containsEnchantment(enchantment)) return false;
                    if (itemA.getEnchantmentLevel(enchantment) != itemB.getEnchantmentLevel(enchantment)) return false;
                }
            }
            return true;
        }

        public static String numeral(int number) {
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

        public static int number(String numeral) {
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
                default: {
                    try {
                        return Integer.parseInt(numeral);
                    }
                    catch (NumberFormatException e) {
                        return 0;
                    }
                }
            }
        }
    }

    public static boolean chance(double percent) {
        double randomValue = new Random().nextDouble() * 100.0;
        return randomValue <= percent;
    }

    public static ItemStack getItemInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (mainHand.getType() != Material.AIR) return mainHand;
        if (offHand.getType() != Material.AIR) return offHand;
        return mainHand;
    }

    public static ItemStack getRangedItemInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (Mat.isRanged(mainHand.getType())) return mainHand;
        if (Mat.isHoe(mainHand.getType())) return null;
        if (Mat.isRanged(offHand.getType())) return offHand;
        return null;
    }

    public static ItemStack getHoeInUse(Player player) {
        PlayerInventory inv = player.getInventory();
        ItemStack mainHand = inv.getItemInMainHand();
        ItemStack offHand = inv.getItemInOffHand();
        if (Mat.isHoe(mainHand.getType())) return mainHand;
        if (Mat.isRanged(mainHand.getType())) return null;
        if (Mat.isHoe(offHand.getType())) return offHand;
        return null;
    }
}
