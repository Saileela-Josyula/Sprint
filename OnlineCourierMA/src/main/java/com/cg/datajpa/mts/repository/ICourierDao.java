package com.cg.datajpa.mts.repository;

import java.time.LocalDate;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

import java.util.List;

import com.cg.datajpa.mts.entities.Courier;

public interface ICourierDao {

	public boolean addCourierInfo(Courier courier);
	public Courier getCourierInfo(int courierid) throws CourierNotFoundException;
	public void removeCourierInfo(int courierid);
	
	public List<Courier> getAllDeliveredCouriers();
	public List<Courier> getAllDeliveredCouriersByDate(LocalDate date);
	public void updateCourierInfoSet(int courierid, CourierStatus s);
	
}
