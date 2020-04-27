package com.bruno.spring.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.spring.domain.Adress;
import com.bruno.spring.domain.City;
import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.enums.ClientType;
import com.bruno.spring.domain.enums.Profile;
import com.bruno.spring.dto.ClientDTO;
import com.bruno.spring.dto.ClientNewDTO;
import com.bruno.spring.repositories.AdressRepository;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.security.UserSS;
import com.bruno.spring.services.exceptions.AuthorizationException;
import com.bruno.spring.services.exceptions.DataIntegrityException;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private AdressRepository adressRepo;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imgService;

	@Value("${img.prefix.client.profile}")
	private String imgPrefix;
	
	@Value("${img.profile.size}")
	private int size;
	

	public Client find(Long id) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acess denied");
		}

		Optional<Client> obj = clientRepo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! id: " + id + ", type: " + Client.class.getName()));
	}

	@Transactional
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
			throw new DataIntegrityException("Can't delete this client! there are orders associeted with this client");
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
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null, null);
	}

	public Client fromDto(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),
				ClientType.toEnum(objDto.getClientType()), bCrypt.encode(objDto.getPassword()));
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

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		// pega o usuario logado
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acess denied");
		}
		BufferedImage jpgImg = imgService.getJpgImageFromFile(multipartFile);
		jpgImg = imgService.cropSquare(jpgImg);
		jpgImg = imgService.resize(jpgImg, size);
		
		String fileName = imgPrefix + user.getId() + ".jpg";
		return s3Service.uploadFile(imgService.getInputStream(jpgImg, "jpg"), fileName, "image");
	}
}