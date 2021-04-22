package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

public interface ICustomerService {

	public void initiateProcess(int courierid)throws CourierNotFoundException;
	public void makePayment(String method);
	public CourierStatus checkOnlineTrackingStatus(int consignmentno)throws CourierNotFoundException;
	
	public void registerComplaint(Complaint complaint);
	
	
}
