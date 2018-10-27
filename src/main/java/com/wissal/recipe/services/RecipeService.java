package com.wissal.recipe.services;

import com.wissal.recipe.commands.RecipeCommand;
import com.wissal.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
