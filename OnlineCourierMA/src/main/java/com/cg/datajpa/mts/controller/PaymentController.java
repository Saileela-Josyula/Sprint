package com.cg.datajpa.mts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.datajpa.mts.service.PaymentsServiceImp;
import com.cg.datajpa.mts.service.IPaymentService;

 @RestController
 @RequestMapping("/payment")



public class PaymentController{

        @Autowired
        PaymentsServiceImp payService;
        
        public void setPaymentService(PaymentsServiceImp payService) {
        	this.payService=payService;
        }
       @GetMapping(value="/cash",produces="application/json")
        public ResponseEntity processPaymentByCash(){
        	
        	payService.processPaymentByCash();
        	return new ResponseEntity<>(HttpStatus.OK);
        	
        }
        @GetMapping(value="/card",produces="application/json")
        public  ResponseEntity processPaymentByCard(){
        	payService.processPaymentByCard();
        	return new ResponseEntity<>(HttpStatus.OK);
        	}
 	 
}