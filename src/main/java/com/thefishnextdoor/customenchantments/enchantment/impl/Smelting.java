package com.thefishnextdoor.customenchantments.enchantment.impl;

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

import com.thefishnextdoor.customenchantments.enchantment.CustomEnchantment;
import com.thefishnextdoor.customenchantments.util.EnchantTools;
import com.thefishnextdoor.customenchantments.util.MaterialTools;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class Smelting extends CustomEnchantment {

    public static HashMap<Material, Material> smeltingMap = generateSmeltingMap();

    @Override
    public String getName() {
        return "Smelting";
    }

    @Override
    public String getDescription() {
        return "Dropped items will be smelted if possible. Rare drop from blaze.";
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
        if (item == null) {
            return false;
        }
        return MaterialTools.isTool(item.getType());
    }
    
    public static void modifyDrops(Player player, List<Item> drops) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.SMELTING)) {
            return;
        }
        for (Item drop : drops) {
            Smelting.smelt(drop);
        }
    }

    public static void onBlockDropItems(Player player, Collection<ItemStack> drops) {
        if (!EnchantTools.holdingMeleeWith(player, CustomEnchantment.SMELTING)) {
            return;
        }
        for (ItemStack drop : drops) {
            Smelting.smelt(drop);
        }
    }

    private static void smelt(Item item) {
        ItemStack itemStack = item.getItemStack();
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted != null) {
            itemStack.setType(smelted);
            item.setItemStack(itemStack);
        }
    }

    private static void smelt(ItemStack itemStack) {
        Material material = itemStack.getType();
        Material smelted = smelted(material);
        if (smelted != null) {
            itemStack.setType(smelted);
        }
    }

    private static Material smelted(Material material) {
        return smeltingMap.get(material);
    }

    private static HashMap<Material, Material> generateSmeltingMap() {
        HashMap<Material, Material> smeltingMap = new HashMap<>();
        Iterator<Recipe> recipes = Bukkit.recipeIterator();
        while (recipes.hasNext()) {
            Recipe recipe = recipes.next();
            if (!(recipe instanceof FurnaceRecipe)) {
                continue;
            }
            
            FurnaceRecipe furnaceRecipe = (FurnaceRecipe) recipe;
            RecipeChoice inputChoice = furnaceRecipe.getInputChoice();
            Material resultMaterial = furnaceRecipe.getResult().getType();
            if (inputChoice instanceof MaterialChoice) {
                MaterialChoice materialChoice = (MaterialChoice) inputChoice;
                for (Material material : materialChoice.getChoices()) {
                    smeltingMap.put(material, resultMaterial);
                }
            } 
            else if (inputChoice instanceof ExactChoice) {
                ExactChoice exactChoice = (ExactChoice) inputChoice;
                for (ItemStack itemStack : exactChoice.getChoices()) {
                    smeltingMap.put(itemStack.getType(), resultMaterial);
                }
            }
        }
        return smeltingMap;
    }
}
