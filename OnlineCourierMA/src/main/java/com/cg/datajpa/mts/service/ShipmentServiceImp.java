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
	CourierDAOImp courierDao;

	public void setCourdao(CourierDAOImp courdao) {
		this.courierDao = courdao;
	}
	/*
	 * Method:initiateShipmentTransaction update status of a courier using courier id
	 * 
	 * CreatedBy:Deepti Pavanika CreatedDate:22 April 2021
	 */
	@Override
	public boolean initiateShipmentTransaction(int courierid) {
		try {
			courierDao.updateCourierInfo(courierid, CourierStatus.intransit);
		} catch (CourierNotFoundException e) {
			
		}
		return true;
	}
	/*
	 * Method:checkShipmentStatus check status of a courier using courier id
	 * 
	 *
	 * 
	 * CreatedBy:Deepti Pavanika CreatedDate:22 April 2021
	 */
	@Override
	public CourierStatus checkShipmentStatus(Courier courier) throws CourierNotFoundException {
		CourierStatus status = null;
		try {
			Courier c = courierDao.getCourierInfo(courier.getCourierid());
			status = c.getStatus();
		} catch (CourierNotFoundException ex) {
		}
		if (status != null) {
			return status;
		} else
			throw new CourierNotFoundException("Courier Not Found");
	}
	/*
	 * Method:closeShipmentTransaction update status of a courier using courier id
	 * 
	 * CreatedBy:Deepti Pavanika CreatedDate:22 April 2021
	 */
	@Override
	public boolean closeShipmentTransaction(int courierid) {
		try {
			courierDao.updateCourierInfo(courierid, CourierStatus.delivered);
		} catch (CourierNotFoundException e) {
		}
		return true;
	}
	/*
	 * Method:rejectShipmentTransaction update status of a courier using courier id
	 * 
	 * CreatedBy:Deepti Pavanika CreatedDate:22 April 2021
	 */
	@Override
	public boolean rejectShipmentTransaction(int courierid) {
		try {
			courierDao.updateCourierInfo(courierid, CourierStatus.rejected);
		} catch (CourierNotFoundException e) {
		}
		return true;
	}
}
