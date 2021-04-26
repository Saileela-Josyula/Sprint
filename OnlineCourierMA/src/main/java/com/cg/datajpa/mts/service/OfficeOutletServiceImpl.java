package com.cg.datajpa.mts.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletClosedException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.repository.IOfficeOutletDao;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;
@Service
public class OfficeOutletServiceImpl implements IOfficeOutletService {
	
	@Autowired
	OfficeOutletDaoImpl dao;
	public OfficeOutletServiceImpl() {
		this.dao=new OfficeOutletDaoImpl();
	}
	
	@Override
	public boolean addNewOffice(CourierOfficeOutlet officeoutlet) {
		// TODO Auto-generated method stub
		return dao.addNewOffice(officeoutlet);
	}

	@Override
	public boolean removeNewOffice(CourierOfficeOutlet officeoutlet) {
		// TODO Auto-generated method stub
		return dao.removeNewOffice(officeoutlet);
	}

	@Override
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException {
		CourierOfficeOutlet o=null;
		try {
			o=dao.getOfficeInfo(officeid);
		}
		catch(OutletNotFoundException ex) {
			
		}
		return o;
	}

	@Override
	public List<CourierOfficeOutlet> getAllOfficesData() {
		List<CourierOfficeOutlet> data=new ArrayList<>();
		data=dao.getAllOfficesData();
		return data;
	}

	@Override
	public boolean isOfficeOpen(CourierOfficeOutlet officeoutlet) throws OutletClosedException {
		boolean status=false;
		LocalTime currentTime=java.time.LocalTime.now();
		try {
			if(currentTime.compareTo(officeoutlet.getOpeningTime())>=0 && currentTime.compareTo(officeoutlet.getClosingTime())<0) {
				status=true;
			}
			else
				throw new OutletClosedException("Outlet is closed");
		}
		catch(OutletClosedException ex) {
			
		}
		return status;
	}

	@Override
	public boolean isOfficeClosed(CourierOfficeOutlet officeoutlet) throws OutletClosedException {
		boolean status=false;
		LocalTime currentTime=java.time.LocalTime.now();
		try {
			if(officeoutlet.getOpeningTime().compareTo(currentTime)<0 && currentTime.compareTo(officeoutlet.getClosingTime())>=0)
				status=true;
			else
				throw new OutletClosedException("Outlet is closed");
		}
		catch(OutletClosedException ex) {
			
		}
		return status;
	}
	
}
