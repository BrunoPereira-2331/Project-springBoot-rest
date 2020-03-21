package com.bruno.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Client;
import com.bruno.spring.dto.ClientDTO;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.services.exceptions.DataIntegrityException;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	public Client find(Long id) {
		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", type: " + Client.class.getName()));
	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepo.save(newObj);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Long id) {
		find(id);
		try {
			clientRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't delete this client! there are orders associeted with this client");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepo.findAll(pageRequest);
	}

	public Client fromDto(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
}