package com.cg.datajpa.mts.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImp implements IPaymentService {

	@Override
	public String processPaymentByCash() {
		System.out.println("Payment by cash method generated");
		return "Payment by cash method generated";

	}

	@Override
	public String processPaymentByCard() {
		System.out.println("Payment by card method generated");
		return "Payment by card method generated";

	}

}
