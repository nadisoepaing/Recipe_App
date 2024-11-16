package com.hostmdy.recipe.bootstrap;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.recipe.entity.Category;
import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;

@Component
public class RecipeDataBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	public RecipeRepository recipeRepository;
	
	@Autowired
	public UomRepository uomRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private List<Recipe> getRecipes(){
		Optional<Category> koreanOpt = categoryRepository.findByName("Korean");
		if(koreanOpt.isEmpty()) {
			throw new RuntimeException("category with name = Korean is not found");
	}
	Category korean = koreanOpt.get();
	
	Optional<Category> japaneseOpt = categoryRepository.findByName("Japanese");
	if(japaneseOpt.isEmpty()) {
		throw new RuntimeException("category with name = Japanese is not found");
}
Category japanese = japaneseOpt.get();
return null;
	}
	}


