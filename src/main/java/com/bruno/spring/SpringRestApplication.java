package com.bruno.spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bruno.spring.domain.Adress;
import com.bruno.spring.domain.Category;
import com.bruno.spring.domain.City;
import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.Order;
import com.bruno.spring.domain.OrderItem;
import com.bruno.spring.domain.Payment;
import com.bruno.spring.domain.PaymentCreditCard;
import com.bruno.spring.domain.PaymentSlip;
import com.bruno.spring.domain.Product;
import com.bruno.spring.domain.State;
import com.bruno.spring.domain.enums.ClientType;
import com.bruno.spring.domain.enums.PaymentState;
import com.bruno.spring.repositories.AdressRepository;
import com.bruno.spring.repositories.CategoryRepository;
import com.bruno.spring.repositories.CityRepository;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.repositories.OrderItemRepository;
import com.bruno.spring.repositories.OrderRepository;
import com.bruno.spring.repositories.PaymentRepository;
import com.bruno.spring.repositories.ProductRepository;
import com.bruno.spring.repositories.StateRepository;

@SpringBootApplication
public class SpringRestApplication implements CommandLineRunner {

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

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
			
		Product product1 = new Product(null, "Computador", 2000.00);
		Product product2 = new Product(null, "Impressora", 800.00);
		Product product3 = new Product(null, "Mouse", 80.00);
		Product product4 = new Product(null, "Mesa de escritório", 300.00);
		Product product5 = new Product(null, "Toalha", 50.00);
		Product product6 = new Product(null, "Colcha", 200.00);
		Product product7 = new Product(null, "TV true color", 1200.00);
		Product product8 = new Product(null, "Roçadeira", 800.00);
		Product product9 = new Product(null, "Abajour", 100.00);
		Product product10 = new Product(null, "Pendente", 180.00);
		Product product11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		cat2.getProducts().addAll(Arrays.asList(product2, product4));
		cat3.getProducts().addAll(Arrays.asList(product5, product6));
		cat4.getProducts().addAll(Arrays.asList(product1, product2, product3));
		cat5.getProducts().addAll(Arrays.asList(product8));
		cat6.getProducts().addAll(Arrays.asList(product9, product10));
		cat7.getProducts().addAll(Arrays.asList(product11));
		
		product1.getCategories().addAll(Arrays.asList(cat1, cat4));
		product2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		product3.getCategories().addAll(Arrays.asList(cat1, cat4));
		product4.getCategories().addAll(Arrays.asList(cat2));   
		product5.getCategories().addAll(Arrays.asList(cat3));   
		product6.getCategories().addAll(Arrays.asList(cat3));   
		product7.getCategories().addAll(Arrays.asList(cat4));   
		product8.getCategories().addAll(Arrays.asList(cat5));   
		product9.getCategories().addAll(Arrays.asList(cat6));   
	    product10.getCategories().addAll(Arrays.asList(cat6));   
        product11.getCategories().addAll(Arrays.asList(cat7));   

		categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepo.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11));

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, adress1);
		Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, adress2);

		Payment pay1 = new PaymentCreditCard(null, PaymentState.SETTLED, order1, 6);
		order1.setPayment(pay1);

		Payment pay2 = new PaymentSlip(null, PaymentState.PENDING, order2, sdf.parse("20/10/2020 00:00"), null);
		order2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(order1, order2));

		orderRepo.saveAll(Arrays.asList(order1, order2));
		paymentRepo.saveAll(Arrays.asList(pay1, pay2));

		OrderItem orderItem1 = new OrderItem(order1, product1, 0.0, 1, product1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, product3, 0.0, 2, product3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, product2, 100.0, 1, product2.getPrice());

		order1.getItems().addAll((Arrays.asList(orderItem1, orderItem2)));
		order2.getItems().addAll((Arrays.asList(orderItem3)));

		product1.getItems().addAll(Arrays.asList(orderItem1));
		product2.getItems().addAll(Arrays.asList(orderItem3));
		product3.getItems().addAll(Arrays.asList(orderItem2));

		orderItemRepo.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
	}

}
