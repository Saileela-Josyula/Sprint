package com.cg.datajpa.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.CourierDAOImp;
@Service
public class ShipmentServiceImp implements IShipmentService {
	@Autowired
	CourierDAOImp courdao;
   
	
	public void setCourdao(CourierDAOImp courdao) {
		this.courdao = courdao;
	}

	@Override
	public void initiateShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,CourierStatus.intransit);

	}

	@Override
	public CourierStatus checkShipmentStatus(Courier courier) throws CourierNotFoundException {
		// TODO Auto-generated method stub
		CourierStatus status=null;
		try {
			Courier c=courdao.getCourierInfo(courier.getCourierid());
			status=c.getStatus();
		}
		catch(CourierNotFoundException ex) {
			
		}
		if(status!=null) {
			return status;
		}
		else
			throw new CourierNotFoundException("Courier Not Found");
		

	}

	@Override
	public void closeShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,CourierStatus.delivered);
	}

	@Override
	public void rejectShipmentTransaction(Courier courier) {
		// TODO Auto-generated method stub
		courdao.updateCourierInfoSet(courier,CourierStatus.rejected);
	}

}
