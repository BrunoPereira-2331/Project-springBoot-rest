package com.bruno.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Product;
import com.bruno.spring.repositories.ProductRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public Product find(Long id) {
		Optional<Product> obj = productRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto not found! Id: " + id + ", Type: " + Product.class.getName()));
	}

}
