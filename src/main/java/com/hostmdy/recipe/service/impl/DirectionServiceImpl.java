package com.hostmdy.recipe.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.entity.Direction;
import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.repository.DirectionRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.DirectionService;

@Service
public class DirectionServiceImpl implements DirectionService{

	private final RecipeRepository recipeRepository;
	private final DirectionRepository directionRepository;
	
	
	public DirectionServiceImpl(RecipeRepository recipeRepository, DirectionRepository directionRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.directionRepository = directionRepository;
	}

	@Override
	public Direction saveDirection(Direction direction) {
		// TODO Auto-generated method stub
		return directionRepository.save(direction);
	}

	@Override
	public Direction createDirection(Direction direction, Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		Recipe recipe = recipeOptional.get();
		direction.setRecipe(recipe);
		if(direction.getId() == null) {
			recipe.getDirections().add(direction);
		}
		return saveDirection(direction);
	}

	@Override
	public void deleteDriectionbyId(Long directionId) {
		// TODO Auto-generated method stub
		directionRepository.deleteById(directionId);
		
	}

	@Override
	public Optional<Direction> getDirectionById(Long directionId) {
		// TODO Auto-generated method stub
		return directionRepository.findById(directionId);
	}

}
