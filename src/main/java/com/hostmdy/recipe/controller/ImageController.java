package com.hostmdy.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.entity.Recipe;
import com.hostmdy.recipe.service.ImageService;
import com.hostmdy.recipe.service.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;
	
	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{recipeId}/upload")
	public String recipeImageForm(@PathVariable Long recipeId, Model model) {
		model.addAttribute("recipeId",recipeId);
		return "recipe/image-upload.html";
	}
	
	@PostMapping("/recipe/upload")
	public String recipeImageUpload(@RequestParam MultipartFile imagefile,@RequestParam Long recipeId) {
		imageService.saveImageToDB(imagefile, recipeId);
		return "redirect:/recipe/show/"+recipeId;
	}
	
	@GetMapping("/recipe/{recipeId}")
	public void showRecipeImage(@PathVariable Long recipeId,HttpServletResponse response) {
		Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
		if(recipeOptional.isEmpty()) {
			throw new RuntimeException("recipe is not found");
		}
		byte[] imageBytes = recipeOptional.get().getImage();
		
		InputStream iStream = new ByteArrayInputStream(imageBytes);
		response.setContentType("image/jpeg");
		try {
			IOUtils.copy(iStream, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
