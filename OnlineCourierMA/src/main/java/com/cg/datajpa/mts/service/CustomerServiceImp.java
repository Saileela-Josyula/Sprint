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
	CourierDAOImp c;	
	
	@Autowired
	ComplaintDaoImpl cdao;

	@Autowired
	PaymentsServiceImp paymentService;
	
	

	public CustomerServiceImp() {
		super();
		c=new CourierDAOImp();
		cdao=new ComplaintDaoImpl();
		paymentService=new PaymentsServiceImp();
	}

	@Override
	public boolean initiateProcess(Courier courier)
	{
		courier.setStatus(CourierStatus.iniated);
		Random random=new Random();
		courier.setConsignmentno(random.nextInt(1000)+1000);
		courier.setInitiatedDate(LocalDate.now());
		courier.setDeliveredDate(LocalDate.now().plusDays(7));
		return c.addCourierInfo(courier);
	}

	@Override
	public String makePayment(String method) {
		if(method.equals("cash")) {
			paymentService.processPaymentByCash();
			return "Payment done by cash";
		}
		else{
			paymentService.processPaymentByCard();
			return "Payment done by card";
		}
		
			
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
	public boolean registerComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		return cdao.addNewComplaint(complaint);	
	}
}
