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

import com.hostmdy.recipe.entity.Ingredient;
import com.hostmdy.recipe.entity.Uom;
import com.hostmdy.recipe.service.IngredientService;
import com.hostmdy.recipe.service.UomService;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
	
	private final IngredientService ingredientService;
	private final UomService uomService;

	public IngredientController(IngredientService ingredientService, UomService uomService) {
		super();
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}
	
	@GetMapping("/new/{recipeId}")
	public String create(@PathVariable Long recipeId, Model model) {
		List<Uom> uoms = uomService.getAllUoms();
		Ingredient ingredient = new Ingredient();
		model.addAttribute("uoms",uoms);
		model.addAttribute("ingredient",ingredient);
		model.addAttribute("recipeId",recipeId);
		return "ingredient/ingredient-form";
	}
	
	@PostMapping("/new")
	public String createNew(@ModelAttribute Ingredient ingredient,@RequestParam Long recipeId,@RequestParam Long uomId) {
		
		Ingredient savedIngredient = ingredientService.createIngredient(ingredient, recipeId, uomId);
		System.out.println("### ingredient with id = "+savedIngredient.getId()+" is created ###");
		
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}
	
	@GetMapping("/{ingredientId}/update/{recipeId}")
	public String updateForm(@PathVariable Long ingredientId,@PathVariable Long recipeId, Model model) {
		Optional<Ingredient> ingredientOptional = ingredientService.getIngredientById(ingredientId);
		if(ingredientOptional.isEmpty()) {
			throw new RuntimeException("ingredient not found..");
		}
		List<Uom> uoms = uomService.getAllUoms();
		Ingredient ingredient = new Ingredient();
		model.addAttribute("uoms",uoms);
		model.addAttribute("ingredient",ingredient);
		model.addAttribute("recipeId",recipeId);
		return "ingredient/ingredient-form";
	}
	
	@GetMapping("/{ingredientId}/delete/{recipeId}")
	public String delete(@PathVariable Long ingredientId,@PathVariable Long recipeId) {
		ingredientService.deleteIngredientById(ingredientId);
		return "redirect:/recipe/"+recipeId+"/ingredients";
	}
	
}
