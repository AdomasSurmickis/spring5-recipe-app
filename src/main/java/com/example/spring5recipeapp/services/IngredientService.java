package com.example.spring5recipeapp.services;


import com.example.spring5recipeapp.commands.IngredientCommand;
import com.example.spring5recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientService   {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);


}
