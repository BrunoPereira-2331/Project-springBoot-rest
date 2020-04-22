package com.bruno.spring.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Transactional(readOnly=true)
	public Page<Order> findByClient(Client client, Pageable pageRequest);
	
}
