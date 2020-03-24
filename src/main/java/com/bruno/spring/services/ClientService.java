package com.bruno.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Adress;
import com.bruno.spring.domain.City;
import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.enums.ClientType;
import com.bruno.spring.dto.ClientDTO;
import com.bruno.spring.dto.ClientNewDTO;
import com.bruno.spring.repositories.AdressRepository;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.services.exceptions.DataIntegrityException;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private AdressRepository adressRepo;

	public Client find(Long id) {
		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", type: " + Client.class.getName()));
	}

	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepo.save(obj);
		adressRepo.saveAll(obj.getAdresses());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			clientRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Can't delete this client! there are orders associeted with this client");
		}
	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepo.findAll(pageRequest);
	}
	
	public Client fromDto(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	public Client fromDto(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
				ClientType.toEnum(objDto.getClientType()));
		City city = new City(objDto.getCityId(), null, null);
		Adress adress = new Adress(null, objDto.getLogradouro(), objDto.getNumber(), objDto.getComplement(),
				objDto.getNeighborhood(), objDto.getPostalCode(), cli, city);
		cli.getAdresses().add(adress);
		cli.getPhoneNumber().add(objDto.getPhone1());
		if (objDto.getPhone2() != null) {
			cli.getPhoneNumber().add(objDto.getPhone2());
		}
		if (objDto.getPhone3() != null) {
			cli.getPhoneNumber().add(objDto.getPhone3());
		}
		return cli;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}