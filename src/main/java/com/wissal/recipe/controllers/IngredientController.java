package com.wissal.recipe.controllers;

import com.wissal.recipe.commands.IngredientCommand;
import com.wissal.recipe.model.UnitOfMeasure;
import com.wissal.recipe.services.IngredientService;
import com.wissal.recipe.services.RecipeService;
import com.wissal.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService){
        this.recipeService=recipeService;
        this.ingredientService=ingredientService;
        this.unitOfMeasureService=unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("getting ingredient list for recipe id : "+recipeId);

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,@PathVariable String id,Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(id)));
        return "recipe/ingredient/show";
    }

    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,@PathVariable String id,Model model){
        model.addAttribute("ingriedient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(id)));

        model.addAttribute("uom", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand,Model model){
        IngredientCommand savedCommand=ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("saved recipe id="+savedCommand.getRecipeId());
        log.debug("saved ingredient id:"+savedCommand.getId());

        return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }
}
