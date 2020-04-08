package com.bruno.spring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Order;
import com.bruno.spring.domain.OrderItem;
import com.bruno.spring.domain.PaymentSlip;
import com.bruno.spring.domain.enums.PaymentState;
import com.bruno.spring.repositories.OrderItemRepository;
import com.bruno.spring.repositories.OrderRepository;
import com.bruno.spring.repositories.PaymentRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order find(Long id) {
		Optional<Order> obj = orderRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id:" + id + ", Type: " + Order.class.getName()));
	}

	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentSlip) {
			PaymentSlip pay = (PaymentSlip) obj.getPayment();
			slipService.fillPaymentSlip(pay, obj.getInstant());
		}
		obj = orderRepo.save(obj);
		paymentRepo.save(obj.getPayment());
		for (OrderItem x : obj.getItems()) {
			x.setDescount(0.0);
			x.setPrice(productService.find(x.getProduct().getId()).getPrice());
			x.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		return obj;
	}
	
}
