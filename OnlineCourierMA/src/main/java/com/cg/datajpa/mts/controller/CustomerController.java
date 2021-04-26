package com.cg.datajpa.mts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class CustomerController 
{
     @Autowired
     CustomerServiceImp custService;
     
     public void setCustomerService(CustomerServiceImp custService)
     {
    	 this.custService=custService;
     }
     @Autowired
     CourierDAOImp courierDao;
    
	public void setCourierDao(CourierDAOImp courierDao) {
		this.courierDao = courierDao;
	}
	@Transactional
	@PostMapping(value="/addcourier",consumes="application/json")
     public ResponseEntity<String> initiateProcess(@RequestBody Courier courier) {
    
		boolean status=custService.initiateProcess(courier);
		if(status)
			return new ResponseEntity<String>("",HttpStatus.OK);
		else
			return new ResponseEntity<String>("There was an error,please try again",HttpStatus.NOT_ACCEPTABLE);
     }
     
     @GetMapping(value="/courier/{courierid}",produces="application/json")
     public ResponseEntity<Optional<CourierStatus>> checkOnlineTrackingStatus(@PathVariable("courierid") int courierid)
     {
    	 Optional<CourierStatus> cs=null;
		try {
			cs = Optional.ofNullable(custService.checkOnlineTrackingStatus(courierid));
		} catch (CourierNotFoundException e) {
			// TODO Auto-generated catch block
		}
		if(cs.isPresent())
			return new ResponseEntity<Optional<CourierStatus>>(cs,HttpStatus.OK);
		else
			return new ResponseEntity<Optional<CourierStatus>>(HttpStatus.NO_CONTENT);
     }
     @Transactional
     @PostMapping(value="/complaint",consumes="application/json")
     public ResponseEntity<HttpStatus> registerComplaint(@RequestBody Complaint complaint){
    	 custService.registerComplaint(complaint);
    	 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
     }
     
     @GetMapping(value="/payment/{method}",consumes="application/json")
     public ResponseEntity<String> makePayment(@PathVariable("method") String payment){
    	 if(payment.equals("card") || payment.equals("cash")) {
    		 custService.makePayment(payment);
        	 return new ResponseEntity<String>("Thank you for using our service!",HttpStatus.OK); 
    	 }
    	 else {
    		 return new ResponseEntity<String>("Only cash/card methods allowed",HttpStatus.BAD_GATEWAY);
    	 }
    	
     }
}