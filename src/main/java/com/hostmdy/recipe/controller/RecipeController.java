package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.entity.Category;
import com.hostmdy.recipe.entity.Direction;
import com.hostmdy.recipe.entity.Ingredient;
import com.hostmdy.recipe.entity.Note;
import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.service.CategoryService;
import com.hostmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;
	private final CategoryService categoryService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
	}

	@GetMapping("/all")
	public String all(Model model) {
		List<Recipe> recipes = recipeService.getAllRecipes();
		model.addAttribute("recipes", recipes);
		return "index";
	}

	@GetMapping("/show/{recipeId}")
	public String details(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);
		if (recipeOpt.isEmpty()) { // actually it must goes to error page thou
			throw new RuntimeException("recipe not found");
		}
		Recipe recipe = recipeOpt.get();
		List<Ingredient> ingredients = recipe.getIngredients().stream()
				.sorted((i1,i2)-> i1.getSequence() > i2.getSequence() ? 1 : -1)
				.toList();
		List<Direction> directions = recipe.getDirections().stream()
				.sorted((d1,d2)-> d1.getSequence() > d2.getSequence() ? 1 : -1)
				.toList();

		model.addAttribute("recipe",recipe);
		model.addAttribute("directions",directions);
		model.addAttribute("ingredients",ingredients);
		return "recipe/recipe-details";
	}

	@GetMapping("/create")
	public String form(Model model) {
		Recipe recipe = new Recipe();
		Note note = new Note();
		recipe.setNote(note);
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", categories);
		model.addAttribute("relatedCategoryNames","sample");
		return "recipe/add-recipe";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Recipe recipe, @RequestParam String[] categoryNames) {
		for (final String categoryName : categoryNames) {
			Optional<Category> categoryOptional = categoryService.getCategoryByName(categoryName);
			if (categoryOptional.isEmpty()) {
				throw new RuntimeException("category with name " + categoryName + " is not found");
			}
			recipe.setTotalTime(recipe.getPrepTime()+recipe.getPrepTime());
			recipe.getCategories().add(categoryOptional.get());
		}
		
		recipeService.saveRecipe(recipe);
		return "redirect:/recipe/all";
	}
	
	@GetMapping("/update/{recipeId}")
	public String updateFrom(@PathVariable Long recipeId, Model model ) {
		Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is empty");
		}
		List<Category> categories = categoryService.getAllCategories();
		Recipe recipe = recipeOptional.get();
		List<String> relatedCategoryNames = recipe.getCategories().stream()
				.map(c -> c.getName()).toList();
		model.addAttribute("recipe",recipe);
		model.addAttribute("categories", categories);
		model.addAttribute("relatedCategoryNames",relatedCategoryNames);
		return "recipe/add-recipe";
	}
	
	@GetMapping("/delete/{recipeId}")
	public String delete(@PathVariable Long recipeId) {
		recipeService.deleteRecipeById(recipeId);
		return "redirect:/recipe/all";
	}
	
	@GetMapping("/{recipeId}/ingredients")
	public String allIngredients(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		List<Ingredient> ingredients = recipeOptional.get().getIngredients().stream()
				.sorted((i1,i2)-> i1.getSequence()> i2.getSequence()? 1:-1)
				.toList();
		model.addAttribute("ingredients",ingredients);
		return "ingredient/ingredient-list";
	}
	
	@GetMapping("/{recipeId}/directions")
	public String allDirection(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		List<Direction> directions = recipeOptional.get().getDirections().stream()
				.sorted((d1,d2)-> d1.getSequence()> d2.getSequence()? 1:-1)
				.toList();
		model.addAttribute("directions",directions);
		return "direction/direction-list";
	}

}
