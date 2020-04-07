package com.bruno.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Category;
import com.bruno.spring.domain.Product;
import com.bruno.spring.repositories.CategoryRepository;
import com.bruno.spring.repositories.ProductRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	public Product find(Long id) {
		Optional<Product> obj = productRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto not found! Id: " + id + ", Type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = catRepo.findAllById(ids);
		return productRepo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}

}
