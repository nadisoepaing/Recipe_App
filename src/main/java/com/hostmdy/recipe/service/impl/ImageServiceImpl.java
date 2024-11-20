package com.hostmdy.recipe.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	private final RecipeRepository recipeRepository;
	
	
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}


	@Override
	public void saveImageToDB(MultipartFile recipeImage, Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		Recipe recipe = recipeOptional.get();
		
		try {
			byte[] imageBytes = recipeImage.getBytes();
			recipe.setImage(imageBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recipeRepository.save(recipe);
	}

}
