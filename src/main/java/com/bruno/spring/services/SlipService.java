package com.bruno.spring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.bruno.spring.domain.PaymentSlip;

@Service
public class SlipService {
	
	public void fillPaymentSlip(PaymentSlip pay, Date orderInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pay.setExpirationDate(cal.getTime());
	}

}
