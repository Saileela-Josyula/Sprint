package com.cg.datajpa.mts.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImp implements IPaymentService {

	@Override
	public void processPaymentByCash() {
		System.out.println("Payment is done by Cash");

	}

	@Override
	public void processPaymentByCard() {
		System.out.println("Payment is done by Card");

	}

}
