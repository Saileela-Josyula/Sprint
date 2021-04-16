package com.cg.datajpa.mts.service;

import com.cg.datajpa.mts.entities.Courier;

public interface IShipmentService {

	public void initiateShipmentTransaction(Courier courier);
	public void checkShipmentStatus(Courier courier);
	public void closeShipmentTransaction(Courier courier);
	public void rejectShipmentTransaction(Courier courier);
	
}
