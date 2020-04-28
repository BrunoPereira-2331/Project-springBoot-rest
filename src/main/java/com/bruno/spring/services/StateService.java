package com.bruno.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.State;
import com.bruno.spring.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepo;

	public List<State> findAll() {
		return stateRepo.findAllByOrderByName();
	}

}
