package com.cg.datajpa.mts.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
	/*
	 * Method:initiateShipmentTransaction update status of a courier using courier id
	 * 
	 * @Transactional
	 * 
	 * @PutMapping CreatedBy:Deepti Pavanika CreatedDate:23 April 2021
	 */
	@Transactional
	@PutMapping(value = "/initiate", consumes = "application/json")
	public ResponseEntity<String> initiateShipmentTransaction(@RequestBody int courierid) {
		Courier courier=null;
		try {
			courier = courierDao.getCourierInfo(courierid);
		}
		catch(CourierNotFoundException e) {}
		if(courier!=null) {
			shipmentService.initiateShipmentTransaction(courierid);
			 return new ResponseEntity<>("Shipment is initiated!!!",HttpStatus.OK);
		}else
			return new ResponseEntity<>("There was an error, please try again",HttpStatus.NOT_ACCEPTABLE);
	}
	/*
	 * Method:checkShipmentStatus check status of a courier using courier id
	 * 
	 *
	 * 
	 * @GetMapping CreatedBy:Deepti Pavanika CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/courier/{courierid}", produces = "application/json")
	public ResponseEntity<Optional<CourierStatus>> checkShipmentStatus(@PathVariable("courierid") int courierid) {
		Optional<CourierStatus> status = null;
		try {
			Courier courier = courierDao.getCourierInfo(courierid);
			status = Optional.ofNullable(shipmentService.checkShipmentStatus(courier));
		} catch (CourierNotFoundException ex) {
		}
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	/*
	 * Method:closeShipmentTransaction update status of a courier using courier id
	 * 
	 * @Transactional
	 * 
	 * @PutMapping CreatedBy:Deepti Pavanika CreatedDate:23 April 2021
	 */
	@Transactional
	@PutMapping(value = "/close", consumes = "application/json")
	public ResponseEntity<String	> closeShipmentTransaction(@RequestBody int courierid) {
		Courier courier=null;
		try {
			courier = courierDao.getCourierInfo(courierid);
		}
		catch(CourierNotFoundException e) {}
		if(courier!=null) {
			shipmentService.closeShipmentTransaction(courierid);
			 return new ResponseEntity<>("Shipment is Closed Successfully!!!",HttpStatus.OK);
		}else
			return new ResponseEntity<>("There was an error, please try again",HttpStatus.NOT_ACCEPTABLE);
	}
	/*
	 * Method:rejectShipmentTransaction update status of a courier using courier id
	 * 
	 * @Transactional
	 * 
	 * @PutMapping CreatedBy:Deepti Pavanika CreatedDate:23 April 2021
	 */
	@Transactional
	@PutMapping(value = "/reject", consumes = "application/json")
	public ResponseEntity<String> rejectShipmentTransaction(@RequestBody int courierid) {
		Courier courier=null;
		try {
			courier = courierDao.getCourierInfo(courierid);
		}
		catch(CourierNotFoundException e) {}
		if(courier!=null) {
			shipmentService.rejectShipmentTransaction(courierid);
			  return new ResponseEntity<>("Shipment is Rejected Successfully!!1",HttpStatus.OK);
		}else
			return new ResponseEntity<>("There was an error, please try again",HttpStatus.NOT_ACCEPTABLE); 
	}
}