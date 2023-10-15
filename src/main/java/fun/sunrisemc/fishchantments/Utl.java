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

        public static boolean canModify(Player player, Block block) {
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
            return isMeleeWeapon(material) || isRangedWeapon(material);
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

        public static boolean isRangedWeapon(Material material) {
            return material == Material.BOW || material == Material.CROSSBOW || material == Material.TRIDENT;
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

    public static class Ench {

        public static boolean hasEnchant(ItemStack item, Enchantment enchant) {
            return getEnchantLevel(item, enchant) > 0;
        }

        public static int getEnchantLevel(ItemStack item, Enchantment enchant) {
            if (item == null) return 0;
            if (item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            if (!meta.hasStoredEnchant(enchant)) return 0;
            return meta.getStoredEnchantLevel(enchant);
            }
            else {
            ItemMeta meta = item.getItemMeta();
            if (meta == null) return 0;
            if (!meta.hasEnchant(enchant)) return 0;
            return meta.getEnchantLevel(enchant);
            }
        }

        public static ArrayList<Enchantment> getEnchantments(ItemStack item) {
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

        public static int numeralToNumber(String numeral) {
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
    }

    public static boolean chance(double probability) {
        double randomValue = new Random().nextDouble() * 100.0;
        return randomValue <= probability;
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
