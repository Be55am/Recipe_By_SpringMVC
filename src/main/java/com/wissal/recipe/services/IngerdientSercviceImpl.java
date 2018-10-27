package com.wissal.recipe.services;

import com.wissal.recipe.commands.IngredientCommand;
import com.wissal.recipe.converters.IngredientCommandToIngredient;
import com.wissal.recipe.converters.IngredientToIngredientCommand;
import com.wissal.recipe.model.Ingredient;
import com.wissal.recipe.model.Recipe;
import com.wissal.recipe.repositories.RecipeRepository;
import com.wissal.recipe.repositories.UniteOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class IngerdientSercviceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UniteOfMeasureRepository uniteOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngerdientSercviceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,RecipeRepository recipeRepository,
                                  UniteOfMeasureRepository uniteOfMeasureRepository,
                                  IngredientCommandToIngredient ingredientCommandToIngredient){
        this.ingredientToIngredientCommand=ingredientToIngredientCommand;
        this.recipeRepository=recipeRepository;
        this.uniteOfMeasureRepository=uniteOfMeasureRepository;
        this.ingredientCommandToIngredient =ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            //todo impelement error handling
            log.error("recipe id not found . id="+recipeId);
        }

        Recipe recipe=recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional=recipe.getIngredients().stream().
                filter(ingredient -> ingredient.getId().equals(id)).
                map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if (!ingredientCommandOptional.isPresent()){
            //todo imp error handling
            log.error("Ingredient id is not found .id ="+id);
        }
        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()){
            log.error("Recipe not found for id:"+ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }else {
            Recipe recipe=recipeOptional.get();

            Optional<Ingredient> ingredientOptional=recipe.getIngredients().stream().
                    filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();
            if (ingredientOptional.isPresent()){
                Ingredient ingredientFound=ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setUom(uniteOfMeasureRepository.findById(ingredientCommand.getUom().getId()).
                        orElseThrow(() -> new RuntimeException("UOM not found !")));


            }else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }

            Recipe savedRecipe=recipeRepository.save(recipe);

            //todo check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream().
                    filter(recipeIngredients->recipeIngredients.getId().equals(ingredientCommand.getId())).findFirst().get());
        }








    }
}
