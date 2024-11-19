package com.hostmdy.recipe.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "text")
	private String description;
	
	private BigDecimal amount;
	
	private Integer sequence;
	
	private LocalDateTime createAt;
	private LocalDateTime updatedAt;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uom_id")
	private Uom uom;
	
	public Ingredient() {}

	public Ingredient(String description, BigDecimal amount, Integer sequence, Recipe recipe, Uom uom) {
		super();
		this.description = description;
		this.amount = amount;
		this.sequence = sequence;
		this.recipe = recipe;
		this.uom = uom;
	}

	@PrePersist
	private void onCreate() {
		this.createAt = LocalDateTime.now();
	}

	@PreUpdate
	private void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
