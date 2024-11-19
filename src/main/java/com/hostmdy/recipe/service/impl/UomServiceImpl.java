package com.hostmdy.recipe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.entity.Uom;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.UomService;

@Service
public class UomServiceImpl implements UomService{
	
	private final UomRepository uomRepository;

	public UomServiceImpl(UomRepository uomRepository) {
		super();
		this.uomRepository = uomRepository;
	}

	@Override
	public List<Uom> getAllUoms() {
		// TODO Auto-generated method stub
		return (List<Uom>) uomRepository.findAll();
	}
	
	

}
