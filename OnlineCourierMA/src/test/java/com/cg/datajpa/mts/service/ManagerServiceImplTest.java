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
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
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