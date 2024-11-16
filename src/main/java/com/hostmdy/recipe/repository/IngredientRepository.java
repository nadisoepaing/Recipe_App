package com.hostmdy.recipe.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

}
