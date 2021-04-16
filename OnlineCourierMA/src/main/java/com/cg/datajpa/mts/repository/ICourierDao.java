package com.cg.mts.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.mts.entities.Courier;

public interface ICourierDao {

	public void addCourierInfo(Courier courier);
	public void getCourierInfo(int courierid);
	public void removeCourierInfo(int courierid);
	
	public List<Courier> getAllDeliveredCouriers();
	public List<Courier> getAllDeliveredCouriersByDate(LocalDate date);
	
}
