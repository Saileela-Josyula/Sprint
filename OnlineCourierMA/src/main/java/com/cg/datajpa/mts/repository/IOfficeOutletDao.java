package com.cg.datajpa.mts.repository;

import java.util.List;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletNotFoundException;

public interface IOfficeOutletDao {

	public boolean addNewOffice(CourierOfficeOutlet officeoutlet);
	public boolean removeNewOffice(CourierOfficeOutlet officeoutlet);
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException;
	public List<CourierOfficeOutlet> getAllOfficesData();
}
