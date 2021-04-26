package com.cg.datajpa.mts.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;
import com.cg.datajpa.mts.service.ManagerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ManagerServiceImplTest {
	@Autowired
	ManagerServiceImpl managerservice;

	@MockBean
	StaffMemberDAOImp dao;
	@MockBean
	CourierDAOImp courierDao;
	@MockBean
	ComplaintDaoImpl complaintDao;
	@MockBean
	OfficeOutletDaoImpl officeDao;
	//@MockBean
	//StaffMemberDAOImp staffDao;
	
	@Test
	public void testAddStaffMember() throws OutletNotFoundException
	{
		OfficeStaffMember os=new OfficeStaffMember();
		CourierOfficeOutlet office=new CourierOfficeOutlet();
		office.setStaffmembers(new ArrayList<>());
		when(officeDao.getOfficeInfo(1)).thenReturn(office);
		when(officeDao.updateOffice(office)).thenReturn(true);
		assertEquals(true,managerservice.addStaffMember(os,1));
	}
	
	@Test
	public void testAddStaffMemberExcetption() throws OutletNotFoundException{
		
	}/*
	@Test
	public void testRemoveStaffMember() throws StaffMemberNotFoundException {
		OfficeStaffMember os=new OfficeStaffMember();
		when(staffDao.removeStaffMember(os)).thenReturn(true);
		assertEquals(true,managerservice.removeStaffMember(os));
	}
	
	@Test
	public void testGetStaffMember()  throws StaffMemberNotFoundException
	{
		OfficeStaffMember os=new OfficeStaffMember();
		when(staffDao.getStaffMember(1)).thenReturn(os);
		assertEquals(os,managerservice.getStaffMember(1));	
	}
	*/
	
	@Test
	public void testGetCourierStatus() throws CourierNotFoundException {
		Courier c = new Courier();
		c.setCourierid(1);
		c.setStatus(CourierStatus.iniated);
		when(courierDao.getCourierInfo(1)).thenReturn(c);
		assertEquals(CourierStatus.iniated, managerservice.getCourierStatus(c));
		
	}

	@Test
	public void testGetRegisteredComplaint() throws ComplaintNotFoundException {
		Complaint comp = new Complaint();
		comp = complaintDao.getComplaint(3);
		when(comp).thenReturn(comp);
		assertEquals(comp, managerservice.getRegistedComplaint(3));
	}

	@Test
	public void testGetAllComplaints() {
		List<Complaint> list = new ArrayList<>();
		list.add(new Complaint());
		list.add(new Complaint());
		when(complaintDao.getAllComplaints()).thenReturn(list);
		assertEquals(2, managerservice.getAllComplaints().size());
	}

	@Test
	public void testGetALLDeliveredCouriers() {
		List<Courier> list = new ArrayList<>();
		list.add(new Courier());
		list.add(new Courier());
		list.add(new Courier());
		when(courierDao.getAllDeliveredCouriers()).thenReturn(list);
		assertEquals(3, managerservice.getAllDeliveredCouriers().size());
	}
	
}