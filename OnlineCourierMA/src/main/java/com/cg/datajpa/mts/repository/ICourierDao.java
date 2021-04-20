package com.cg.datajpa.mts.repository;

import java.time.LocalDate;
import com.cg.datajpa.mts.entities.Courier;
import java.util.List;

import com.cg.datajpa.mts.entities.Courier;

public interface ICourierDao {

	public void addCourierInfo(Courier courier);
	public Courier getCourierInfo(int courierid);
	public void removeCourierInfo(int courierid);
	
	public List<Courier> getAllDeliveredCouriers();
	public List<Courier> getAllDeliveredCouriersByDate(LocalDate date);
	
}
