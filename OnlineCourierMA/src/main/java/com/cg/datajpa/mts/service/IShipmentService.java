package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

public interface IShipmentService {

	public void initiateShipmentTransaction(Courier courier);
	public CourierStatus checkShipmentStatus(Courier courier) throws CourierNotFoundException;
	public void closeShipmentTransaction(Courier courier);
	public void rejectShipmentTransaction(Courier courier);
	
}
