package com.cg.datajpa.mts.service;

import java.util.List;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.OutletClosedException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;

public interface IOfficeOutletService {
	
	public void addNewOffice(CourierOfficeOutlet officeoutlet);
	public void removeNewOffice(CourierOfficeOutlet officeoutlet);
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException;
	public List<CourierOfficeOutlet> getAllOfficesData();
	
	public boolean isOfficeOpen(CourierOfficeOutlet officeoutlet) throws OutletClosedException;
	public boolean isOfficeClosed(CourierOfficeOutlet officeoutlet)throws OutletClosedException;
	
}
