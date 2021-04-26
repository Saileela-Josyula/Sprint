package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

public interface ICustomerService {

	public boolean initiateProcess(Courier courier);
	public String makePayment(String method);
	public CourierStatus checkOnlineTrackingStatus(int consignmentno)throws CourierNotFoundException;
	
	public boolean registerComplaint(Complaint complaint);

	
	
}
