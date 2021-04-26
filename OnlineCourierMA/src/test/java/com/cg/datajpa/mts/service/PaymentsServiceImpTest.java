package com.cg.datajpa.mts.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentsServiceImpTest {

	@Autowired
	PaymentsServiceImp ser;

	@Test
	void testProcessPaymentByCash() {
		assertEquals("Payment by cash method generated", ser.processPaymentByCash());
	}

	@Test
	void testProcessPaymentByCard() {
		assertEquals("Payment by card method generated", ser.processPaymentByCard());
	}

}