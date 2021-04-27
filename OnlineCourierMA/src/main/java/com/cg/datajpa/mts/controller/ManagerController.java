package com.cg.datajpa.mts.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.OfficeMember;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;
import com.cg.datajpa.mts.service.CustomerServiceImp;
import com.cg.datajpa.mts.service.ManagerServiceImpl;
import com.cg.datajpa.mts.service.OfficeOutletServiceImpl;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerServiceImpl managerService;

	@Autowired
	StaffMemberDAOImp staffDAO;

	@Autowired
	CustomerServiceImp customerService;

	@Autowired
	OfficeOutletServiceImpl officeService;

	public void setOfficeService(OfficeOutletServiceImpl officeService) {
		this.officeService = officeService;
	}

	public void setCustomerServiceImp(CustomerServiceImp customerService) {
		this.customerService = customerService;
	}

	public void setManagerService(ManagerServiceImpl managerService) {
		this.managerService = managerService;
	}

	public void setStaffDAO(StaffMemberDAOImp staffDAO) {
		this.staffDAO = staffDAO;
	}

	/*
	 * Method:addOfficeStaff add staff member using office id
	 * 
	 * @Transactional
	 * 
	 * @PostMapping CreatedBy:Ede Chandini CreatedDate:23 April 2021
	 */
	@Transactional
	@PostMapping(value = "/addstaff", consumes = "application/json")
	public ResponseEntity<String> addOfficeStaff(@RequestBody OfficeMember officeStaffMember) {
		boolean status = false;
		try {
			managerService.addStaffMember(officeStaffMember.getMember(), officeStaffMember.getOfficeid());
			status = true;

		} catch (OutletNotFoundException ex) {
		}
		if (status)
			return new ResponseEntity<>("Office member added successfully", HttpStatus.OK);
		else
			return new ResponseEntity<>("There was an error, please try again", HttpStatus.NO_CONTENT);

	}

	/*
	 * Method:deleteOfficeStaff remove staff member using employee id
	 * 
	 * @Transactional
	 * 
	 * @DeleteMapping CreatedBy:Ede Chandini CreatedDate:23 April 2021
	 */
	@Transactional
	@DeleteMapping(value = "/deletestaff/{empid}")
	public ResponseEntity<String> deleteOfficeStaff(@PathVariable("empid") int empid) {
		OfficeStaffMember member = null;
		try {
			member = staffDAO.getStaffMember(empid);
		} catch (StaffMemberNotFoundException ex) {
		}
		if (member != null) {
			managerService.removeStaffMember(member);
			return new ResponseEntity<>("Office Member deleted Successfully!!!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("There was an error, please try again ", HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Method:getCourierStatus check status of any courier using courier id
	 * 
	 * @GetMapping CreatedBy:Pankaj Kumar Singh CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/courier/{courierid}", produces = "application/json")
	public ResponseEntity<Optional<CourierStatus>> getCourierStatus(@PathVariable("courierid") int courierid) {
		Optional<CourierStatus> status = null;
		try {
			status = Optional.ofNullable(customerService.checkOnlineTrackingStatus(courierid));
		} catch (CourierNotFoundException ex) {
		}
		if (status == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(status, HttpStatus.OK);

	}

	/*
	 * Method:getAllComplaint show all complaints in database
	 * 
	 * @GetMapping CreatedBy:Pankaj Kumar Singh CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/complaint/all", produces = "application/json")
	public ResponseEntity<List<Complaint>> getAllComplaint() {
		return new ResponseEntity<>(managerService.getAllComplaints(), HttpStatus.OK);
	}

	/*
	 * Method:getAllDeliveredCourier fetch all couriers which are delivered
	 * 
	 * @GetMapping CreatedBy:Pankaj Kumar Singh CreatedDate:23 April 2021
	 */
	@GetMapping(value = "/courier/alldelivered", produces = "application/json")
	public ResponseEntity<List<Courier>> getAllDeliveredCourier() {
		return new ResponseEntity<>(managerService.getAllDeliveredCouriers(), HttpStatus.OK);
	}
}
