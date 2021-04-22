package com.cg.datajpa.mts.service;

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
	CourierDAOImp c;	
	public void setC(CourierDAOImp c) {
		this.c = c;
	}

	@Autowired
	ComplaintDaoImpl cdao;
	public void setCdoa(ComplaintDaoImpl cdao) {
		this.cdao = cdao;
	}
	
	@Autowired
	PaymentsServiceImp paymentService;
	public void setPaymentService(PaymentsServiceImp ps) {
		this.paymentService=ps;
	}
	
	@Override
	public void initiateProcess(int courierid)throws CourierNotFoundException
	{
		// TODO Auto-generated method stub
		CourierStatus st=CourierStatus.iniated;
		Courier courier=c.getCourierInfo(courierid);
		
		 if(courier!=null)
		 {
			 courier.setStatus(st);
			 c.updateCourierInfoSet(courier, st);
		 }
		
			 throw new CourierNotFoundException("Courier not found");			
	}

	@Override
	public void makePayment(String method) {
		if(method.equals("cash"))
			paymentService.processPaymentByCash();
		else if(method.equals("card"))
			paymentService.processPaymentByCard();
			
	}

	@Override
	public CourierStatus checkOnlineTrackingStatus(int courierid) throws CourierNotFoundException {
		// TODO Auto-generated method stub
		CourierStatus st=null;
			Courier courier=c.getCourierInfo(courierid);
			
			 if(courier!=null)
			 {
				 st=courier.getStatus();
				 return st;
			 }
			 else {
				 throw new CourierNotFoundException("Courier not found");
			 }
				
	}

	@Override
	public void registerComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		cdao.addNewComplaint(complaint);	
	}
}
