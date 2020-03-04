package com.bruno.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.spring.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
