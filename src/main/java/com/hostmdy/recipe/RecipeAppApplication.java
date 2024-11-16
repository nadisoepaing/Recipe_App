package com.hostmdy.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostmdy.recipe.entity.Category;
import com.hostmdy.recipe.repository.CategoryRepository;

@SpringBootApplication
public class RecipeAppApplication implements CommandLineRunner{

	@Autowired
	public CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RecipeAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Category c1 = new Category();
//		c1.setName("Itelian");
//		c1.setDescription("This is category for Itelian food");
//		
//		Category c2 = new Category();
//		c2.setName("Korea");
//		c2.setDescription("This is category for Korean food");
//		
//		categoryRepository.save(c1);
//		categoryRepository.save(c2);
//		System.out.println(" categories are created ");
	}

}
