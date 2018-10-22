package com.wissal.recipe.controllers;


import com.wissal.recipe.model.Category;
import com.wissal.recipe.model.UnitOfMeasure;
import com.wissal.recipe.repositories.CategoryRepository;
import com.wissal.recipe.repositories.UniteOfMeasureRepository;
import com.wissal.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
