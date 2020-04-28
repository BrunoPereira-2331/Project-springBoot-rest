package com.bruno.spring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.bruno.spring.domain.enums.Profile;
import com.bruno.spring.repositories.AdressRepository;
import com.bruno.spring.repositories.CategoryRepository;
import com.bruno.spring.repositories.CityRepository;
import com.bruno.spring.repositories.ClientRepository;
import com.bruno.spring.repositories.OrderItemRepository;
import com.bruno.spring.repositories.OrderRepository;
import com.bruno.spring.repositories.PaymentRepository;
import com.bruno.spring.repositories.ProductRepository;
import com.bruno.spring.repositories.StateRepository;

@Service
public class DBService {

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

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public void instantiateDatabase() throws ParseException {

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
		Product product12 = new Product(null, "Product 12", 10.00);
		Product product13 = new Product(null, "Product 13", 10.00);
		Product product14 = new Product(null, "Product 14", 10.00);
		Product product15 = new Product(null, "Product 15", 10.00);
		Product product16 = new Product(null, "Product 16", 10.00);
		Product product17 = new Product(null, "Product 17", 10.00);
		Product product18 = new Product(null, "Product 18", 10.00);
		Product product19 = new Product(null, "Product 19", 10.00);
		Product product20 = new Product(null, "Product 20", 10.00);
		Product product21 = new Product(null, "Product 21", 10.00);
		Product product22 = new Product(null, "Product 22", 10.00);
		Product product23 = new Product(null, "Product 23", 10.00);
		Product product24 = new Product(null, "Product 24", 10.00);
		Product product25 = new Product(null, "Product 25", 10.00);
		Product product26 = new Product(null, "Product 26", 10.00);
		Product product27 = new Product(null, "Product 27", 10.00);
		Product product28 = new Product(null, "Product 28", 10.00);
		Product product29 = new Product(null, "Product 29", 10.00);
		Product product30 = new Product(null, "Product 30", 10.00);
		Product product31 = new Product(null, "Product 31", 10.00);
		Product product32 = new Product(null, "Product 32", 10.00);
		Product product33 = new Product(null, "Product 33", 10.00);
		Product product34 = new Product(null, "Product 34", 10.00);
		Product product35 = new Product(null, "Product 35", 10.00);
		Product product36 = new Product(null, "Product 36", 10.00);
		Product product37 = new Product(null, "Product 37", 10.00);
		Product product38 = new Product(null, "Product 38", 10.00);
		Product product39 = new Product(null, "Product 39", 10.00);
		Product product40 = new Product(null, "Product 40", 10.00);
		Product product41 = new Product(null, "Product 41", 10.00);
		Product product42 = new Product(null, "Product 42", 10.00);
		Product product43 = new Product(null, "Product 43", 10.00);
		Product product44 = new Product(null, "Product 44", 10.00);
		Product product45 = new Product(null, "Product 45", 10.00);
		Product product46 = new Product(null, "Product 46", 10.00);
		Product product47 = new Product(null, "Product 47", 10.00);
		Product product48 = new Product(null, "Product 48", 10.00);
		Product product49 = new Product(null, "Product 49", 10.00);
		Product product50 = new Product(null, "Product 50", 10.00);

		cat1.getProducts()
				.addAll(Arrays.asList(product12, product13, product14, product15, product16, product17, product18,
						product19, product20, product21, product22, product23, product24, product25, product26,
						product27, product28, product29, product30, product31, product32, product34, product35,
						product36, product37, product38, product39, product40, product41, product42, product43,
						product44, product45, product46, product47, product48, product49, product50));

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
		// products for test
		product12.getCategories().add(cat1);
		product13.getCategories().add(cat1);
		product14.getCategories().add(cat1);
		product15.getCategories().add(cat1);
		product16.getCategories().add(cat1);
		product17.getCategories().add(cat1);
		product18.getCategories().add(cat1);
		product19.getCategories().add(cat1);
		product20.getCategories().add(cat1);
		product21.getCategories().add(cat1);
		product22.getCategories().add(cat1);
		product23.getCategories().add(cat1);
		product24.getCategories().add(cat1);
		product25.getCategories().add(cat1);
		product26.getCategories().add(cat1);
		product27.getCategories().add(cat1);
		product28.getCategories().add(cat1);
		product29.getCategories().add(cat1);
		product30.getCategories().add(cat1);
		product31.getCategories().add(cat1);
		product32.getCategories().add(cat1);
		product33.getCategories().add(cat1);
		product34.getCategories().add(cat1);
		product35.getCategories().add(cat1);
		product36.getCategories().add(cat1);
		product37.getCategories().add(cat1);
		product38.getCategories().add(cat1);
		product39.getCategories().add(cat1);
		product40.getCategories().add(cat1);
		product41.getCategories().add(cat1);
		product42.getCategories().add(cat1);
		product43.getCategories().add(cat1);
		product44.getCategories().add(cat1);
		product45.getCategories().add(cat1);
		product46.getCategories().add(cat1);
		product47.getCategories().add(cat1);
		product48.getCategories().add(cat1);
		product49.getCategories().add(cat1);
		product50.getCategories().add(cat1);

		categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepo.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7,
				product8, product9, product10, product11));

		productRepo.saveAll(Arrays.asList(product12, product13, product14, product15, product16, product17, product18,
				product19, product20, product21, product22, product23, product24, product25, product26, product27,
				product28, product29, product30, product31, product32, product34, product35, product36, product37,
				product38, product39, product40, product41, product42, product43, product44, product45, product46,
				product47, product48, product49, product50));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().add(city1);
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepo.saveAll(Arrays.asList(state1, state2));
		cityRepo.saveAll(Arrays.asList(city1, city2, city3));

		Client cli1 = new Client(null, "Bruno Silva", "brunopereira.19@hotmail.com", "36378912377",
				ClientType.PESSOAFISICA, bcrypt.encode("123"));

		Client cli2 = new Client(null, "Maria Silva", "b.2331bruno@gmail.com", "30817400028", ClientType.PESSOAFISICA,
				bcrypt.encode("123"));
		cli2.addProfile(Profile.ADMIN);

		cli1.getPhoneNumber().addAll(Arrays.asList("91334261", "92328452"));
		cli2.getPhoneNumber().addAll(Arrays.asList("99586245", "95423251"));

		Adress adress1 = new Adress(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, city1);
		Adress adress2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, city2);
		Adress adress3 = new Adress(null, "Avenida Floriano", "2006", null, "Centro", "22467012", cli2, city2);

		cli1.getAdresses().addAll(Arrays.asList(adress1, adress2));
		cli2.getAdresses().addAll(Arrays.asList(adress3));

		clientRepo.saveAll(Arrays.asList(cli1, cli2));
		adressRepo.saveAll(Arrays.asList(adress1, adress2, adress3));

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
