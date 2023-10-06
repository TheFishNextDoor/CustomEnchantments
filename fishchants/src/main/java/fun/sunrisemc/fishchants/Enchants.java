package fun.sunrisemc.fishchants;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Enchants {

    public static class GrassSeeds {

        final static String LORE = "Grass Seeds";

        public static void onArrowHitBlock(Player player, Arrow arrow, ItemStack bow, Block block) {
            if (hasEnchant(bow, LORE)) {
                player.sendMessage(LORE);
                block.setType(Material.GLOWSTONE);
            }
        }
    }

    public static boolean hasEnchant(ItemStack item, String lore) {
        return getEnchantLevel(item, lore) > 0;
    }

    public static int getEnchantLevel(ItemStack item, String lore) {
        return 1;
    }
}