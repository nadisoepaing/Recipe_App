package com.hostmdy.recipe.service;

import java.util.Optional;

import com.hostmdy.recipe.entity.Ingredient;

public interface IngredientService {

	Ingredient saveIngredient(Ingredient ingredient);
	
	Ingredient createIngredient(Ingredient ingredient,Long recipeId,Long uomId);
	
	void deleteIngredientById(Long ingredientId);
	
	Optional<Ingredient> getIngredientById(Long ingredient);
}
