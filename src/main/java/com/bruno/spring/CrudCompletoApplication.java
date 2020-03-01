package com.bruno.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.spring.domain.Category;
import com.bruno.spring.repositories.CategoryRepository;

@SpringBootApplication
public class CrudCompletoApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository catRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CrudCompletoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
