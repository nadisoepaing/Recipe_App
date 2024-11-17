package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.entity.Recipe;

public interface RecipeService {
	Recipe saveRecipe(Recipe recipe);
	List<Recipe> getAllRecipes();
	Optional<Recipe> getRecipeByName(String name);
	Optional<Recipe> getRecipeById(Long recipeId);
	void deleteRecipeById(Long recipeId); 

}
