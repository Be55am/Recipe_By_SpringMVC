package com.wissal.recipe.services;

import com.wissal.recipe.commands.RecipeCommand;
import com.wissal.recipe.converters.RecipeCommandToRecipe;
import com.wissal.recipe.converters.RecipeToRecipeCommand;
import com.wissal.recipe.model.Recipe;
import com.wissal.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository
            ,RecipeToRecipeCommand recipeToRecipeCommand,RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand=recipeToRecipeCommand;
        this.recipeCommandToRecipe=recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");

        Set<Recipe> recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
    public Recipe findById(Long l){
        Optional<Recipe> recipeOptional=recipeRepository.findById(l);
        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found !");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe=recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe=recipeRepository.save(detachedRecipe);
        log.debug("saved recipeId "+savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
