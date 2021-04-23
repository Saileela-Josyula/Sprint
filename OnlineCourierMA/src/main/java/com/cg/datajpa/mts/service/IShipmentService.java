package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

public interface IShipmentService {

	public void initiateShipmentTransaction(int courierid);
	public CourierStatus checkShipmentStatus(Courier courier) throws CourierNotFoundException;
	public void closeShipmentTransaction(int courierid);
	public void rejectShipmentTransaction(int courierid);
	
}
