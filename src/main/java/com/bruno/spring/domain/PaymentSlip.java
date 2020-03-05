package com.bruno.spring.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.bruno.spring.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentSlip extends Payment {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expirationDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;

	public PaymentSlip() {
	}

	public PaymentSlip(Long id, PaymentState state, Order order, Date expirationDate, Date paymentDate) {
		super(id, state, order);
		this.expirationDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
