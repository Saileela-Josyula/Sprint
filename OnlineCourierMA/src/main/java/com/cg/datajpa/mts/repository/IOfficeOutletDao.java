package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.CourierOfficeOutlet;
import com.cg.mts.exception.OutletNotFoundException;

public interface IOfficeOutletDao {

	public void addNewOffice(CourierOfficeOutlet officeoutlet);
	public void removeNewOffice(CourierOfficeOutlet officeoutlet);
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException;
	public List<CourierOfficeOutlet> getAllOfficesData();
}
