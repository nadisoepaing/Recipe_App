package com.hostmdy.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.recipe.entity.Category;
import com.hostmdy.recipe.entity.Difficulty;
import com.hostmdy.recipe.entity.Direction;
import com.hostmdy.recipe.entity.Ingredient;
import com.hostmdy.recipe.entity.Note;
import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.entity.Uom;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;

@Component
public class RecipeDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	public RecipeRepository recipeRepository;

	@Autowired
	public UomRepository uomRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("### ContextRefreshedEvent is triggered ###");
		recipeRepository.saveAll(getRecipes());

	}

	private List<Recipe> getRecipes() {

		Optional<Category> koreanOpt = categoryRepository.findByName("Korean");
		if (koreanOpt.isEmpty()) {
			throw new RuntimeException("category with name = Korean is not found");
		}
		Category korean = koreanOpt.get();

		Optional<Category> japaneseOpt = categoryRepository.findByName("Japanese");
		if (japaneseOpt.isEmpty()) {
			throw new RuntimeException("category with name = Japanese is not found");
		}
		Category japanese = japaneseOpt.get();

		// cup
		Optional<Uom> cupOptional = uomRepository.findByName("cup");
		if (cupOptional.isEmpty()) {
			throw new RuntimeException("unit with name = cup is not found");
		}
		Uom cup = cupOptional.get();

		// piece
		Optional<Uom> pieceOptional = uomRepository.findByName("piece");
		if (pieceOptional.isEmpty()) {
			throw new RuntimeException("unit with name = piece is not found");
		}
		Uom piece = pieceOptional.get();

		// pound
		Optional<Uom> poundOptional = uomRepository.findByName("pound");
		if (poundOptional.isEmpty()) {
			throw new RuntimeException("unit with name = pound is not found");
		}
		Uom pound = poundOptional.get();

		// teaspoon
		Optional<Uom> teaspoonoOptional = uomRepository.findByName("teaspoon");
		if (teaspoonoOptional.isEmpty()) {
			throw new RuntimeException("unit with name = teaspoon is not found");
		}
		Uom teaspoon = teaspoonoOptional.get();

		// tablespoon
		Optional<Uom> tablespoonoOptional = uomRepository.findByName("tablespoon");
		if (tablespoonoOptional.isEmpty()) {
			throw new RuntimeException("unit with name = tablespoon is not found");
		}
		Uom tablespoon = tablespoonoOptional.get();

		Recipe recipe = new Recipe();
		recipe.setName("Easy Korean Beef Bowl");
		recipe.setDifficulty(Difficulty.NORMAL);
		recipe.setDescription("This delicious Korean beef bowl is quick and easy to make with ground beef. It's seasoned with fresh ginger, garlic, soy, sesame seeds, and crushed red pepper, but these ingredients can easily be adjusted to suit your taste. Serve over warm rice or spiralized vegetables.\n");
		recipe.setRatings(4.7);
		recipe.setPrepTime(10);
		recipe.setCookTime(15);
		recipe.setTotalTime(25);
		recipe.setServings(4);
		recipe.setSource("https://www.allrecipes.com/recipe/268091/easy-korean-ground-beef-bowl/");
		
		//Recipe - Note (one way)
		Note note = new Note();
		note.setDescription("Nutrition Facts (per serving)\n"
				+ "574 Calories\n"
				+ "19g Fat\n"
				+ "70g Carbs\n"
				+ "29g Protein");
		recipe.setNote(note);
		
		//Recipe - Category
		recipe.getCategories().add(japanese);
		recipe.getCategories().add(korean);
		japanese.getRecipes().add(recipe);
		korean.getRecipes().add(recipe);
		
		//ingredients - recipe // ingredient - uom
		Ingredient beefIngredient = new Ingredient("lean ground beef", new BigDecimal(1), 1, recipe, pound);
		Ingredient garlicIngredient = new Ingredient("clove garlic", new BigDecimal(5), 2, recipe, piece);
		Ingredient gingerIngredient = new Ingredient("freshly grated ginger", new BigDecimal(1), 3, recipe, tablespoon);
		Ingredient sesameoilIngredient = new Ingredient("toased sesame oil", new BigDecimal(2), 4, recipe, teaspoon);
		Ingredient soysauceIngredient = new Ingredient("reduced sodium soy sauce", new BigDecimal(0.5), 5, recipe, cup); 
		Ingredient brownsugarIngredient = new Ingredient("light brown sugar", new BigDecimal(1/3),6, recipe, cup);
		Ingredient redpepperIngredient = new Ingredient("crushed red pepper", new BigDecimal(1/4), 7, recipe, teaspoon);
		Ingredient onionsIngredient = new Ingredient("green onions, chopped, divided", new BigDecimal(6), 8, recipe, piece);
		Ingredient brownriceIngredient = new Ingredient("hot cooked brown rice", new BigDecimal(4), 9, recipe, cup);
		Ingredient sesameseedIngredient = new Ingredient("toasted seasame seed", new BigDecimal(1), 10, recipe, tablespoon);
		
		recipe.getIngredients().add(beefIngredient);
		recipe.getIngredients().add(garlicIngredient);
		recipe.getIngredients().add(gingerIngredient);
		recipe.getIngredients().add(sesameoilIngredient);
		recipe.getIngredients().add(soysauceIngredient);
		recipe.getIngredients().add(brownsugarIngredient);
		recipe.getIngredients().add(redpepperIngredient);
		recipe.getIngredients().add(onionsIngredient);
		recipe.getIngredients().add(brownriceIngredient);
		recipe.getIngredients().add(sesameseedIngredient);
		
		piece.getIngredients().add(garlicIngredient);
		piece.getIngredients().add(onionsIngredient);
		pound.getIngredients().add(beefIngredient);
		teaspoon.getIngredients().add(redpepperIngredient);
		teaspoon.getIngredients().add(sesameoilIngredient);
		tablespoon.getIngredients().add(gingerIngredient);
		tablespoon.getIngredients().add(gingerIngredient);
		cup.getIngredients().add(soysauceIngredient);
		cup.getIngredients().add(brownsugarIngredient);
		cup.getIngredients().add(brownriceIngredient);
		
		//direction - recipe
		Direction step1 = new Direction("Gather all ingredients.", 1, recipe);
		Direction step2 = new Direction("Heat a large skillet over medium-high heat. Add beef and cook, stirring and crumbling into small pieces until browned, 5 to 7 minutes. Drain excess grease.", 2, recipe);
		Direction step3 = new Direction("Stir in garlic, ginger, and sesame oil and cook until fragrant, about 2 minutes.", 3, recipe);
		Direction step4 = new Direction("Stir in soy sauce, brown sugar, and red pepper. Cook until beef absorbs some sauce, about 7 minutes.", 4, recipe);
		Direction step5 = new Direction("Add 1/2 of chopped green onions.", 5, recipe);
		Direction step6 = new Direction("Serve over hot cooked rice; garnish with sesame seeds and remaining green onions.", 6, recipe);
		
		recipe.getDirectons().add(step1);
		recipe.getDirectons().add(step2);
		recipe.getDirectons().add(step3);
		recipe.getDirectons().add(step4);
		recipe.getDirectons().add(step5);
		recipe.getDirectons().add(step6);
		
		
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe);
		return recipes;
		
	}
}
