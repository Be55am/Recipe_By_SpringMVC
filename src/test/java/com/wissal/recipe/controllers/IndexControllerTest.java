package com.wissal.recipe.controllers;

import com.wissal.recipe.model.Recipe;
import com.wissal.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import javax.management.Query;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller =new IndexController(recipeService);
    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //given
        Set<Recipe> recipes=new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

        //when
        String viewName=controller.getIndexPage(model);


        //then
        assertEquals("index",viewName);

        Mockito.verify(recipeService,Mockito.times(1)).getRecipes();
        Mockito.verify(model,Mockito.times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController=argumentCaptor.getValue();
        assertEquals(2,setInController.size());
    }
}