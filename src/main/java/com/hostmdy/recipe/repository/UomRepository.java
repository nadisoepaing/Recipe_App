package com.hostmdy.recipe.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.entity.Uom;


public interface UomRepository extends CrudRepository<Uom, Long>{

	Optional<Uom> findByName(String name);
}
