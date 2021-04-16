package com.cg.datajpa.mts.repository;

import java.util.List;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletNotFoundException;

public interface IOfficeOutletDao {

	public void addNewOffice(CourierOfficeOutlet officeoutlet);
	public void removeNewOffice(CourierOfficeOutlet officeoutlet);
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException;
	public List<CourierOfficeOutlet> getAllOfficesData();
}
