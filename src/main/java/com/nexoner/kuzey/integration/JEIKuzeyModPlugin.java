package com.nexoner.kuzey.integration;

import com.nexoner.kuzey.KuzeyMod;
import com.nexoner.kuzey.block.ModBlocks;
import com.nexoner.kuzey.recipe.KuzeyiumPurificationChamberRecipe;
import com.nexoner.kuzey.recipe.KuzeyiumWorkstationRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Objects;

@JeiPlugin
public class JEIKuzeyModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(KuzeyMod.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new KuzeyiumPurificationChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new KuzeyiumWorkstationRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        registration.addRecipes(new RecipeType<>(KuzeyiumPurificationChamberRecipeCategory.UID, KuzeyiumPurificationChamberRecipe.class), rm.getAllRecipesFor(KuzeyiumPurificationChamberRecipe.Type.INSTANCE));
        registration.addRecipes(new RecipeType<>(KuzeyiumWorkstationRecipeCategory.UID, KuzeyiumWorkstationRecipe.class), rm.getAllRecipesFor(KuzeyiumWorkstationRecipe.Type.INSTANCE));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUZEYIUM_PURIFICATION_CHAMBER.get()), KuzeyiumPurificationChamberRecipeCategory.KUZEYIUM_PURIFICATION);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUZEYIUM_WORKSTATION.get()), KuzeyiumWorkstationRecipeCategory.KUZEYIUM_SMITHING);
    }
}