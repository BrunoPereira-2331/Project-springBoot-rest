package com.bruno.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.spring.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Transactional
	@Query("SELECT obj FROM City obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	//@param vincula o parametro stateId da query ao stateId abaixo
	public List<City> findCities(@Param("stateId") Long stateId);
}
