package com.cg.mts.service;

import com.cg.mts.entities.Courier;

public interface IShipmentService {

	public void initiateShipmentTransaction(Courier courier);
	public void checkShipmentStatus(Courier courier);
	public void closeShipmentTransaction(Courier courier);
	public void rejectShipmentTransaction(Courier courier);
	
}
