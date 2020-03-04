package com.bruno.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.spring.domain.Adress;
import com.bruno.spring.domain.Category;
import com.bruno.spring.domain.City;
import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.Product;
import com.bruno.spring.domain.State;
import com.bruno.spring.domain.enums.ClientType;
import com.bruno.spring.repositories.AdressRepository;
import com.bruno.spring.repositories.CategoryRepository;
import com.bruno.spring.repositories.CityRepository;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.repositories.ProductRepository;
import com.bruno.spring.repositories.StateRepository;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private AdressRepository adressRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().add(p2);
		
		p1.getCategories().add(cat1);
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().add(cat1);
		
		categoryRepo.saveAll(Arrays.asList(cat1, cat2));
		productRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		
		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);
		
		state1.getCities().add(city1);
		state2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepo.saveAll(Arrays.asList(state1, state2));
		cityRepo.saveAll(Arrays.asList(city1, city2, city3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		
		cli1.getPhoneNumber().addAll(Arrays.asList("21334261", "99328452"));
		
		Adress adress1 = new Adress(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, city1);
		Adress adress2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);
		
		cli1.getAdresses().addAll(Arrays.asList(adress1, adress2));
		
		clientRepo.save(cli1);
		adressRepo.saveAll(Arrays.asList(adress1, adress2));
		
		
	}

}