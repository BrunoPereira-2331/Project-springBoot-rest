package com.bruno.spring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bruno.spring.domain.Client;
import com.bruno.spring.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado! Cod: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}

	protected String htmlFromTemplateOrder(Order obj) {
		Context context = new Context();
		context.setVariable("order", obj);
		return templateEngine.process("email/orderConfirmation", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Order obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromOrder(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}

	}

	protected MimeMessage prepareMimeMessageFromOrder(Order obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Order confirmed! cod: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrder(obj), true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendEmail(sm);
		
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New password request");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New Password: " + newPass);
		return sm;
	}

}
