package com.bruno.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Client;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	public Client find(Long id) {
		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id + ", type: " + Client.class.getName()));
	}

}
