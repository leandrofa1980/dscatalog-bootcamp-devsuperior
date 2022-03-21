package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFindException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) // readOnly melhora a performace do banco de dados, evitando fazer um locking
	public List<CategoryDTO> findAll(){
		List<Category> list =  repository.findAll();
		
		// Utilizando uma expressão lambda e tranformar para DTO
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
		//Uma forma de transformar uma lista de Category para CategoryDTO
		/*
		 * List<CategoryDTO> listDto = new ArrayList<>(); for(Category cat : list) {
		 * listDto.add(new CategoryDTO(cat)); }
		 */
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFindException("Entity not found"));
		return new CategoryDTO(entity);
	}
}
