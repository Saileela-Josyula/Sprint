package com.cg.datajpa.mts.controller;

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
     public ResponseEntity<HttpStatus> initiateProcess(@RequestBody Courier courier) {
    	 
		courierDao.addCourierInfo(courier);
		try {
    		 custService.initiateProcess(courier.getCourierid());
    	 }
    	 catch(CourierNotFoundException ex) {
    		 
    	 } 
    	 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
     }
     
     @GetMapping(value="/courier/{courierid}",produces="application/json")
     public ResponseEntity<CourierStatus> checkOnlineTrackingStatus(@PathVariable("courierid") int courierid)
     {
    	 CourierStatus cs=null;
		try {
			cs = custService.checkOnlineTrackingStatus(courierid);
		} catch (CourierNotFoundException e) {
			// TODO Auto-generated catch block
		}
    	 return new ResponseEntity<CourierStatus>(cs,HttpStatus.OK);
     }
     @Transactional
     @PostMapping(value="/complaint",consumes="application/json")
     public ResponseEntity<HttpStatus> registerComplaint(@RequestBody Complaint complaint){
    	 custService.registerComplaint(complaint);
    	 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
     }
     
     @GetMapping(value="/payment/{processPaymentByCash}",consumes="application/json")
     public ResponseEntity<HttpStatus> makePayment(@PathVariable("processPaymentByCash") String payment){
    	 if(payment.equals("card") || payment.equals("cash")) {
    		 custService.makePayment(payment);
        	 return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
    	 }
    	 else {
    		 return new ResponseEntity<HttpStatus>(HttpStatus.BAD_GATEWAY);
    	 }
    	
     }
}