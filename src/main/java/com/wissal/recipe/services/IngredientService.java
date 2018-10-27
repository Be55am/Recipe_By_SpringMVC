package com.wissal.recipe.services;

import com.wissal.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
