package com.bruno.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.spring.domain.Order;
import com.bruno.spring.repositories.OrderRepository;
import com.bruno.spring.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	public Order find(Long id) {
		Optional<Order> obj = orderRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id:" + id + ", Type: " + Order.class.getName()));
	}
	
}
