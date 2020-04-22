package com.bruno.spring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Client;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Client client = clientRepo.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email not found!");
		}
		
		String newPass = newPassword();
		client.setPassword(bCrypt.encode(newPass));
		clientRepo.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
		
	}
	
	private String newPassword() {
		char[] vect = new char[10];
		for (int i = 0; i < 10; i++) {
			vect[i] = randomChar();
		}
		return new String(vect);
	}
	
	private char randomChar() {
		int opt = rand.nextInt(3);
		//gera um digito
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}
		//gera uma letra maiuscula
		else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		}
		//gera uma letra minuscula
		else {
			return (char) (rand.nextInt(26) + 97);
		}
		
	}
}
