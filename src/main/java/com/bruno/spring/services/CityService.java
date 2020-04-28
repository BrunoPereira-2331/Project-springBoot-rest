package com.bruno.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.City;
import com.bruno.spring.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;

	public List<City> findByState(Long stateId) {
		return cityRepo.findCities(stateId);
	}
}
