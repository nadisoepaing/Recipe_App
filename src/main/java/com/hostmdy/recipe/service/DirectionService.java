package com.hostmdy.recipe.service;

import java.util.Optional;

import com.hostmdy.recipe.entity.Direction;

public interface DirectionService {

	Direction saveDirection(Direction direction);
	
	Direction createDirection(Direction direction, Long recipeId);
	
	void deleteDriectionbyId(Long directionId);
	
	Optional<Direction> getDirectionById(Long directionId);
}
