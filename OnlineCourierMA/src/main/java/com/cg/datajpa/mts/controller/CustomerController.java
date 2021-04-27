package com.cg.datajpa.mts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.service.CustomerServiceImp;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerServiceImp custService;

	public void setCustomerService(CustomerServiceImp custService) {
		this.custService = custService;
	}

	@Autowired
	CourierDAOImp courierDao;

	public void setCourierDao(CourierDAOImp courierDao) {
		this.courierDao = courierDao;
	}

	/*
	 * Method:initiateProcess Add courier to database and set courier status as
	 * initiated
	 * 
	 * @Transactional
	 * 
	 * @PostMapping CreatedBy:Pawan Kumar BM CreatedDate:23 April 2021
	 */
	@Transactional
	@PostMapping(value = "/addcourier", consumes = "application/json")
	public ResponseEntity<String> initiateProcess(@RequestBody Courier courier) {

		boolean status = custService.initiateProcess(courier);
		if (status)
			return new ResponseEntity<>("Courier added succesfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("There was an error,please try again", HttpStatus.NOT_ACCEPTABLE);
	}

	/*
	 * Method:checkOnlineTrackingStatus Check status of courier using courier id
	 * 
	 * @GetMapping CreatedBy:Pawan Kumar BM CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/courier/{courierid}", produces = "application/json")
	public ResponseEntity<Optional<CourierStatus>> checkOnlineTrackingStatus(@PathVariable("courierid") int courierid) {
		Optional<CourierStatus> courierStatus = null;
		try {
			courierStatus = Optional.ofNullable(custService.checkOnlineTrackingStatus(courierid));
		} catch (CourierNotFoundException e) {
		}
		if (courierStatus.isPresent())
			return new ResponseEntity<>(courierStatus, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	 * Method:registerComplaint Register new complaint by user
	 * 
	 * @Transactional
	 * 
	 * @PostMapping CreatedBy:Pawan Kumar BM CreatedDate:23 April 2021
	 */
	@Transactional
	@PostMapping(value = "/complaint", consumes = "application/json")
	public ResponseEntity<String> registerComplaint(@RequestBody Complaint complaint) {
		boolean status=custService.registerComplaint(complaint);
		if(status)
		       return new ResponseEntity<>("Complaint is added Successfully!!!",HttpStatus.OK);
		else
			return new ResponseEntity<>("There was an error, please try again",HttpStatus.NOT_ACCEPTABLE);
	}

	/*
	 * Method:makePayment Process payment by cash|card
	 * 
	 * @GetMapping CreatedBy:Ede Chandini CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/payment/{method}", consumes = "application/json")
	public ResponseEntity<String> makePayment(@PathVariable("method") String payment) {
		if (payment.equals("card") || payment.equals("cash")) {
			custService.makePayment(payment);
			return new ResponseEntity<>("Thank you for using our service!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Only cash/card methods allowed", HttpStatus.BAD_GATEWAY);
		}

	}
}