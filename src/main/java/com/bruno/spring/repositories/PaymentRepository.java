package com.bruno.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.spring.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
