package com.example.spring5recipeapp.services;

import com.example.spring5recipeapp.commands.RecipeCommand;
import com.example.spring5recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long valueOf);

    RecipeCommand findCommandById(Long id);
}
