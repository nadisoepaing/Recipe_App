package com.hostmdy.recipe.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Lob
	private String description;
	
	private Double ratings;
	private Integer prepTime;
	private Integer cookTime;
	private Integer totalTime;
	private Integer servings;
	private String source;
	private Byte[] image;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name = "note_id")
	private Note note;
	
	@ManyToMany
	@JoinTable(name = "recipe_category",joinColumns = @JoinColumn(name = "recipe_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	public Recipe() {}

	
	

}
