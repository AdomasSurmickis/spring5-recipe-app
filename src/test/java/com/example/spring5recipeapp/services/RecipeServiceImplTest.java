package com.example.spring5recipeapp.services;

import com.example.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.example.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.example.spring5recipeapp.domain.Recipe;
import com.example.spring5recipeapp.exceptions.NotFoundException;
import com.example.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    private RecipeCommandToRecipe recipeCommandToRecipe;
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);

    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();
    }


    @Test
    void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById((anyLong()))).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned);

        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() {
        Long idToDelete = Long.valueOf(2L);
        recipeService.deleteById(idToDelete);

        verify(recipeRepository).deleteById(anyLong());
    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        assertThrows(NotFoundException.class, () -> recipeService.findById(1L));


    }

}