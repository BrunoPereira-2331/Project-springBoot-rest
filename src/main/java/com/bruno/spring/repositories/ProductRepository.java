package com.bruno.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.spring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
