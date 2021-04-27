package com.cg.datajpa.mts.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;

@Service
public class CustomerServiceImp implements ICustomerService {

	@Autowired
	CourierDAOImp courierDao;

	@Autowired
	ComplaintDaoImpl complaintDao;

	@Autowired
	PaymentsServiceImp paymentService;

	public CustomerServiceImp() {
		super();
		courierDao = new CourierDAOImp();
		complaintDao = new ComplaintDaoImpl();
		paymentService = new PaymentsServiceImp();
	}
	/*
	 * Method:initiateProcess Add courier to database and set courier status as
	 * 							initiated,also set consignment no,initiated date and delivery date
	 * 
	 * CreatedBy:Pawan Kumar BM CreatedDate:22 April 2021
	 */
	@Override
	public boolean initiateProcess(Courier courier) {
		courier.setStatus(CourierStatus.iniated);
		Random random = new Random();
		courier.setConsignmentno(random.nextInt(1000) + 1000);
		courier.setInitiatedDate(LocalDate.now());
		courier.setDeliveredDate(LocalDate.now().plusDays(7));
		return courierDao.addCourierInfo(courier);
	}
	/*
	 * Method:makePayment Process payment by cash|card
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public String makePayment(String method) {
		if (method.equals("cash")) {
			paymentService.processPaymentByCash();
			return "Payment done by cash";
		} else {
			paymentService.processPaymentByCard();
			return "Payment done by card";
		}
	}
	/*
	 * Method:checkOnlineTrackingStatus Check status of courier using courier id
	 * 
	 * CreatedBy:Pawan Kumar BM CreatedDate:22 April 2021
	 */
	@Override
	public CourierStatus checkOnlineTrackingStatus(int courierid) throws CourierNotFoundException {
		CourierStatus status = null;
		Courier courier = courierDao.getCourierInfo(courierid);
		if (courier != null) {
			status = courier.getStatus();
			return status;
		} else {
			throw new CourierNotFoundException("Courier not found");
		}
	}
	/*
	 * Method:registerComplaint Register new complaint by user
	 * 
	 * CreatedBy:Pawan Kumar BM CreatedDate:22 April 2021
	 */
	@Override
	public boolean registerComplaint(Complaint complaint) {
		return complaintDao.addNewComplaint(complaint);
	}
}
