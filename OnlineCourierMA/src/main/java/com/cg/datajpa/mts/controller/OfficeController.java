package com.cg.datajpa.mts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.service.OfficeOutletServiceImpl;

@RestController
@RequestMapping("/office")
public class OfficeController {
	@Autowired
	OfficeOutletServiceImpl officeservice;

	public void setOfficeservice(OfficeOutletServiceImpl officeservice) {
		this.officeservice = officeservice;
	}

	/*
	 * Method:addOffice add office to database
	 * 
	 * @Transactional
	 * 
	 * @PostMapping CreatedBy:Venkatesh Murty CreatedDate:23 April 2021
	 */
	@Transactional
	@PostMapping(value = "/addoffice", consumes = "application/json")
	public ResponseEntity<String> addOffice(@RequestBody CourierOfficeOutlet courierOffice) {
		boolean status=officeservice.addNewOffice(courierOffice);
		if(status)
		        return new ResponseEntity<>("Office is added Successfully!!!",HttpStatus.OK);
		else
			 return new ResponseEntity<>("There was an error, please try again",HttpStatus.NOT_ACCEPTABLE);
	}

	/*
	 * Method:deleteOffice delete office and related data to it using office id
	 * 
	 * @Transactional
	 * 
	 * @DeleteMapping CreatedBy:Venkatesh Murty CreatedDate:23 April 2021
	 */
	@Transactional
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteOffice(@RequestBody int officeid) {
		CourierOfficeOutlet office = null;
		try {
			office = officeservice.getOfficeInfo(officeid);
		} catch (OutletNotFoundException ex) {
		}
		if (office != null) {
			officeservice.removeNewOffice(office);
			return new ResponseEntity<>("Office is Deleted Successfully!!!",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("There was an error, please try again",HttpStatus.NO_CONTENT);
		}
	}

	/*
	 * Method:getOffice fetch details of an office using office id
	 * 
	 *
	 * 
	 * @GetMapping CreatedBy:Venkatesh Murty CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/{officeid}", produces = "application/json")
	public ResponseEntity<Optional<CourierOfficeOutlet>> getOffice(@PathVariable("officeid") int officeid) {
		Optional<CourierOfficeOutlet> office = null;
		try {
			office = Optional.ofNullable(officeservice.getOfficeInfo(officeid));
		} catch (OutletNotFoundException ex) {
		}
		if (office == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(office, HttpStatus.OK);
	}

	/*
	 * Method:getAllOfficesData fetch details of all office present in database
	 * 
	 *
	 * 
	 * @GetMapping CreatedBy:Saileela CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<CourierOfficeOutlet>> getAllOfficesData() {
		List<CourierOfficeOutlet> offices = officeservice.getAllOfficesData();
		return new ResponseEntity<>(offices, HttpStatus.OK);
	}

	/*
	 * Method:isOfficeOpen check whether office is open using office id
	 * 
	 *
	 * 
	 * @GetMapping CreatedBy:Saileela CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/isofficeopen", consumes = "application/json")
	public ResponseEntity<String> isOfficeOpen(@RequestBody int courierid) {

		boolean status = false;
		try {
			CourierOfficeOutlet office = officeservice.getOfficeInfo(courierid);
			status = officeservice.isOfficeOpen(office);
		} catch (Exception e) {
		}
		if (status) {
			return new ResponseEntity<>("Office is Open", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Office is closed", HttpStatus.OK);
	}

	/*
	 * Method:isOfficeClosed check whether office is closed using office id
	 * 
	 *
	 * 
	 * @GetMapping CreatedBy:Saileela CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/isofficeclosed", consumes = "application/json")
	public ResponseEntity<String> isOfficeClosed(@RequestBody int officeid) {
		boolean status = false;
		try {
			CourierOfficeOutlet office = officeservice.getOfficeInfo(officeid);
			status = officeservice.isOfficeClosed(office);
		} catch (Exception e) {
		}
		if (status) {
			return new ResponseEntity<>("Office is Closed", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Office is Open", HttpStatus.OK);
	}

}