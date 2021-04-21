package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;

public interface IShipmentService {

	public void initiateShipmentTransaction(Courier courier);
	public CourierStatus checkShipmentStatus(Courier courier);
	public void closeShipmentTransaction(Courier courier);
	public void rejectShipmentTransaction(Courier courier);
	
}
