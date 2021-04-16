package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.CourierStatus;

public interface ICustomerService {

	public void initiateProcess();
	public void makePayment();
	public CourierStatus checkOnlineTrackingStatus(int consignmentno);
	
	public void registerComplaint(Complaint complaint);
	
	
}
