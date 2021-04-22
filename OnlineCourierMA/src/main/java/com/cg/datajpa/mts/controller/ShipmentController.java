package com.cg.datajpa.mts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.service.ShipmentServiceImp;

@RestController
@RequestMapping("/courier")
public class ShipmentController {
	@Autowired
	ShipmentServiceImp shipmentService;
	@Autowired
	CourierDAOImp courierDao;
	
	public void setCourierDao(CourierDAOImp courierDao) {
		this.courierDao = courierDao;
	}

	public void setShipmentService(ShipmentServiceImp shipmentService) {
		this.shipmentService = shipmentService;
	}
	
	@PutMapping(value="/initiate",consumes = "application/json")
	public ResponseEntity<HttpStatus> initiateShipmentTransaction(@RequestBody Courier courier) {
		shipmentService.initiateShipmentTransaction(courier);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping(value="/courier/{courierid}",produces="application/json")
	public ResponseEntity<Optional<CourierStatus>> checkShipmentStatus(@PathVariable("courierid") int courierid) {
		Optional<CourierStatus> s =null;
		try {
		Courier c = courierDao.getCourierInfo(courierid);
		s= Optional.ofNullable(shipmentService.checkShipmentStatus(c));
		}
		catch(CourierNotFoundException ex) {	
		}
		if(s!=null)
			return new ResponseEntity<Optional<CourierStatus>>(s,HttpStatus.OK);
		else
			return new ResponseEntity<Optional<CourierStatus>>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value="/close",consumes = "application/json")
	public ResponseEntity<HttpStatus> closeShipmentTransaction(@RequestBody Courier courier) {
		shipmentService.closeShipmentTransaction(courier);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@PutMapping(value="/reject",consumes = "application/json")
	public ResponseEntity<HttpStatus> rejectShipmentTransaction(@RequestBody Courier courier) {
		shipmentService.rejectShipmentTransaction(courier);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	
	
	

}