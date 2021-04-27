package com.cg.datajpa.mts.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImp implements IPaymentService {
	/*
	 * Method:processPaymentByCash Process payment by cash
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public String processPaymentByCash() {
		System.out.println("Payment by cash method generated");
		return "Payment by cash method generated";
	}

	/*
	 * Method:processPaymentByCard Process payment by card
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public String processPaymentByCard() {
		System.out.println("Payment by card method generated");
		return "Payment by card method generated";
	}

}
