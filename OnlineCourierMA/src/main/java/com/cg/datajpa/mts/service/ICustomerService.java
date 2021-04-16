package com.cg.mts.service;

import com.cg.mts.entities.Complaint;
import com.cg.mts.entities.CourierStatus;

public interface ICustomerService {

	public void initiateProcess();
	public void makePayment();
	public CourierStatus checkOnlineTrackingStatus(int consignmentno);
	
	public void registerComplaint(Complaint complaint);
	
	
}
