package com.cg.datajpa.mts.service;

import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletClosedException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;

@Service
public class OfficeOutletServiceImpl implements IOfficeOutletService {

	@Autowired
	OfficeOutletDaoImpl officeDao;

	public OfficeOutletServiceImpl() {
		this.officeDao = new OfficeOutletDaoImpl();
	}

	/*
	 * Method:addNewOffice add office to database
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public boolean addNewOffice(CourierOfficeOutlet officeoutlet) {
		return officeDao.addNewOffice(officeoutlet);
	}

	/*
	 * Method:removeNewOffice delete office and related data to it
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public boolean removeNewOffice(CourierOfficeOutlet officeoutlet) {
		return officeDao.removeNewOffice(officeoutlet);
	}

	/*
	 * Method:getOfficeInfo fetch details of an office using office id
	 * 
	 *
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException {
		CourierOfficeOutlet office = null;
		try {
			office = officeDao.getOfficeInfo(officeid);
		} catch (OutletNotFoundException ex) {
		}
		return office;
	}

	/*
	 * Method:getAllOfficesData fetch details of all office present in database
	 * 
	 *
	 * 
	 * CreatedBy:Saileela CreatedDate:22 April 2021
	 */
	@Override
	public List<CourierOfficeOutlet> getAllOfficesData() {
		List<CourierOfficeOutlet> offices;
		offices = officeDao.getAllOfficesData();
		return offices;
	}

	/*
	 * Method:isOfficeOpen check whether office is open
	 * 
	 *
	 * 
	 * CreatedBy:Saileela CreatedDate:22 April 2021
	 */
	@Override
	public boolean isOfficeOpen(CourierOfficeOutlet officeoutlet) throws OutletClosedException {
		boolean status = false;
		LocalTime currentTime = java.time.LocalTime.now();
		try {
			if (currentTime.compareTo(officeoutlet.getOpeningTime()) >= 0
					&& currentTime.compareTo(officeoutlet.getClosingTime()) < 0) {
				status = true;
			} else
				throw new OutletClosedException("Outlet is closed");
		} catch (OutletClosedException ex) {
		}
		return status;
	}

	/*
	 * Method:isOfficeClosed check whether office is closed
	 * 
	 *
	 * 
	 * CreatedBy:Saileela CreatedDate:22 April 2021
	 */
	@Override
	public boolean isOfficeClosed(CourierOfficeOutlet officeoutlet) throws OutletClosedException {
		boolean status = false;
		LocalTime currentTime = java.time.LocalTime.now();
		try {
			if (currentTime.compareTo(officeoutlet.getOpeningTime()) < 0
					&& currentTime.compareTo(officeoutlet.getClosingTime()) >= 0)
				status = true;
			else
				throw new OutletClosedException("Outlet is closed");
		} catch (OutletClosedException ex) {
		}
		return status;
	}

}
