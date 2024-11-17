package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return recipeRepository.save(recipe);
	}

	@Override
	public List<Recipe> getAllRecipes() {
		// TODO Auto-generated method stub
		
		return (List<Recipe>) recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> getRecipeByName(String name) {
		// TODO Auto-generated method stub
		return recipeRepository.findByName(name);
	}

	@Override
	public Optional<Recipe> getRecipeById(Long recipeId) {
		// TODO Auto-generated method stub
		return recipeRepository.findById(recipeId);
	}

	@Override
	public void deleteRecipeById(Long recipeId) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(recipeId);
	}

}
