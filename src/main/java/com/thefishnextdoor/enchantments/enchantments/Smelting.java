package com.thefishnextdoor.enchantments.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import com.thefishnextdoor.enchantments.CustomEnchantment;
import com.thefishnextdoor.enchantments.util.EnchantUtil;
import com.thefishnextdoor.enchantments.util.InventoryUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class Smelting extends CustomEnchantment {

    public static HashMap<Material, Material> smeltingMap = generateSmeltingMap();

    public Smelting(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return "Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (item == null) return false;
        return InventoryUtil.isTool(item.getType());
    }
    
    public static void modifyDrops(Player player, List<Item> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.SMELTING)) return;
        for (Item drop : drops) {
            Smelting.smelt(drop);
        }
    }

    public static void onBlockDropItems(Player player, Collection<ItemStack> drops) {
        if (!EnchantUtil.holding(player, CustomEnchantment.SMELTING)) return;
        for (ItemStack drop : drops) {
            Smelting.smelt(drop);
        }
    }

    private static void smelt(Item item) {
        ItemStack itemStack = item.getItemStack();
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted == null) return;
        itemStack.setType(smelted);
        item.setItemStack(itemStack);
    }

    private static void smelt(ItemStack itemStack) {
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted == null) return;
        itemStack.setType(smelted);
    }

    private static Material smelted(Material material) {
        return smeltingMap.get(material);
    }

    private static HashMap<Material, Material> generateSmeltingMap() {
        HashMap<Material, Material> smeltingMap = new HashMap<>();
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            if (!(recipe instanceof FurnaceRecipe)) continue;
            FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;
            RecipeChoice inputChoice = furnaceRecipe.getInputChoice();
            Material resultMaterial = furnaceRecipe.getResult().getType();
            if (inputChoice instanceof MaterialChoice) {
                MaterialChoice materialChoice = (MaterialChoice) inputChoice;
                for (Material material : materialChoice.getChoices()) {
                    smeltingMap.put(material, resultMaterial);
                }
            } else if (inputChoice instanceof ExactChoice) {
                ExactChoice exactChoice = (ExactChoice) inputChoice;
                for (ItemStack itemStack : exactChoice.getChoices()) {
                    smeltingMap.put(itemStack.getType(), resultMaterial);
                }
            }
        }
        return smeltingMap;
    }
}
