package com.hostmdy.recipe.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.entity.Direction;
import com.hostmdy.recipe.service.DirectionService;
@Controller
@RequestMapping("/direction")
public class DirectionController {
	
	private final DirectionService directionService;
	
	
	public DirectionController(DirectionService directionService) {
		super();
		this.directionService = directionService;
	}

	@GetMapping("/new/{recipeId}")
	public String create(@PathVariable Long recipeId, Model model) {
		
		Direction direction = new Direction();
		model.addAttribute("direction",direction);
		model.addAttribute("recipeId",recipeId);
		return "direction/direction-form";
	}
	
	@PostMapping("/new")
	public String createNew(@ModelAttribute Direction direction,@RequestParam Long recipeId) {
		directionService.createDirection(direction,recipeId);
		return "redirect:/recipe/"+recipeId+"/directions";
	}
	
	@GetMapping("/{directionId}/update/{recipeId}")
	public String updateForm(@PathVariable Long directionId,@PathVariable Long recipeId, Model model) {
		Optional<Direction> directionOptional = directionService.getDirectionById(recipeId);
		if(directionOptional.isEmpty()) {
			throw new RuntimeException("direction not found..");
		}
		Direction direction = new Direction();
		model.addAttribute("direction",direction);
		model.addAttribute("recipeId",recipeId);
		return "direction/direction-form";
	}
	
	@GetMapping("/{directionId}/delete/{recipeId}")
	public String delete(@PathVariable Long directionId,@PathVariable Long recipeId) {
		directionService.deleteDriectionbyId(directionId);
		return "redirect:/recipe/"+recipeId+"/directions";
	}
	
}
