package com.bruno.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Category;
import com.bruno.spring.repositories.CategoryRepository;
import com.bruno.spring.services.exceptions.DataIntegrityException;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category find(Long id) {
		Optional<Category> obj = categoryRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepo.save(obj);
	}
	
	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoryRepo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			categoryRepo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			 throw new DataIntegrityException("Can't delete this Category! there still products associted with this category");
		}
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
