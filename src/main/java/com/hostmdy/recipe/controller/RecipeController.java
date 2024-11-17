package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		List<Recipe> recipes = recipeService.getAllRecipes(); 
		model.addAttribute("recipes", recipes);
		return "index";
	}
	
	@GetMapping("/show/{recipeId}")
	public String details(@PathVariable Long recipeId,Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);
		if (recipeOpt.isEmpty()) { // actually it must goes to error page thou
			throw new RuntimeException("recipe not found");
		}
		model.addAttribute("recipe", recipeOpt.get());
		return "recipe/recipe-details";
	}
	
}
