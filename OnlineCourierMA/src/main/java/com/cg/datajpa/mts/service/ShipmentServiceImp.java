package com.cg.datajpa.mts.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.repository.CourierDAOImp;

public class ShipmentServiceImp implements IShipmentService {
	@Autowired
	CourierDAOImp courdao;
   
	
	public void setCourdao(CourierDAOImp courdao) {
		this.courdao = courdao;
	}

	@Override
	public void initiateShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,1);

	}

	@Override
	public CourierStatus checkShipmentStatus(Courier courier) {
		// TODO Auto-generated method stub
		Courier updatedCourier=courdao.getCourierInfo(courier.getCourierid());
		return updatedCourier.getStatus();
	    
	}

	@Override
	public void closeShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,2);
	}

	@Override
	public void rejectShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,3);
		

	}

}
