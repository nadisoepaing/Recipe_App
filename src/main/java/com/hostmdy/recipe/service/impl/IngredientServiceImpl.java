package com.hostmdy.recipe.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.entity.Ingredient;
import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.entity.Uom;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

	
	private final IngredientRepository ingredientRepository;
	private final RecipeRepository recipeRepository;
	private final UomRepository uomRepository;
	
	
	
	public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository,
			UomRepository uomRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ingredientRepository.save(ingredient);
	}

	@Override
	public Ingredient createIngredient(Ingredient ingredient, Long recipeId, Long uomId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		Recipe recipe = recipeOptional.get();
		
		Optional<Uom> uomOptional = uomRepository.findById(uomId);
		if(uomOptional.isEmpty()) {
			throw new RuntimeException("Uom is not found");
		}
		Uom uom = uomOptional.get(); 
		
		// recipe - ingredient
		ingredient.setRecipe(recipe);
		recipe.getIngredients().add(ingredient);
		recipeRepository.save(recipe);
		
		// uom - ingredient
		ingredient.setRecipe(recipe);
		uom.getIngredients().add(ingredient);
		uomRepository.save(uom);
		
		return saveIngredient(ingredient);
	}

	@Override
	public void deleteIngredientById(Long ingredientId) {
		// TODO Auto-generated method stub
		ingredientRepository.deleteById(ingredientId);
	}

	@Override
	public Optional<Ingredient> getIngredientById(Long ingredient) {
		// TODO Auto-generated method stub
		return ingredientRepository.findById(ingredient);
	}

}
