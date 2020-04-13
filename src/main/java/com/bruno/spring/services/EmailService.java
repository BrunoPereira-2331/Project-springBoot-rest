package com.bruno.spring.services;

import org.springframework.mail.SimpleMailMessage;

import com.bruno.spring.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
