package com.hostmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.entity.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>{

	Optional<Category> findByName(String name);
}
