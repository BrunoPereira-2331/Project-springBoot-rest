package com.bruno.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Category;
import com.bruno.spring.dto.CategoryDTO;
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
	
	public List<Category> findAll() {
		return categoryRepo.findAll();
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
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepo.findAll(pageRequest);
	}
	
	public Category FromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
}
