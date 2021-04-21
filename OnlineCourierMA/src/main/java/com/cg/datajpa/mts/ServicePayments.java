package com.cg.datajpa.mts;

import com.cg.datajpa.mts.service.PaymentsServiceImp;

public class ServicePayments {
	public static void main(String[] args) {
		PaymentsServiceImp paymentService=new PaymentsServiceImp();
		paymentService.processPaymentByCash();
		paymentService.processPaymentByCard();
		
	}
}
