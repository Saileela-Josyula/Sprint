package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

public interface IShipmentService {

	public boolean initiateShipmentTransaction(int courierid);
	public CourierStatus checkShipmentStatus(Courier courier) throws CourierNotFoundException;
	public boolean closeShipmentTransaction(int courierid);
	public boolean rejectShipmentTransaction(int courierid);
	
}
