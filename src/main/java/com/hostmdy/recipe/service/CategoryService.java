package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.entity.Category;

public interface CategoryService {

	Category saveCategory(Category category);
	
	List<Category> getAllCategories();
	
	Optional<Category> getCategoryByName(String name);
}
