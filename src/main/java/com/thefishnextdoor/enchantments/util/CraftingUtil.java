package com.thefishnextdoor.enchantments.util;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.ExactChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

public class CraftingUtil {

    private static HashMap<Material, Material> smeltingMap = generateSmeltingMap();

    public static void smelt(Item item) {
        ItemStack itemStack = item.getItemStack();
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted == null) return;
        itemStack.setType(smelted);
        item.setItemStack(itemStack);
    }

    public static void smelt(ItemStack itemStack) {
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted == null) return;
        itemStack.setType(smelted);
    }

    public static Material smelted(Material material) {
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
