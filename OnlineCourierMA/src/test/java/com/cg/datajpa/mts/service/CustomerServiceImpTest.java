package com.cg.datajpa.mts.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceImpTest {
	@Autowired
	CustomerServiceImp customerService;
	@MockBean
	CourierDAOImp courierDao;
	@MockBean
	ComplaintDaoImpl complaintDao;
	
	@Test
	public void testInitiateProcess() {
		
		Courier courier=new Courier(null,null);
		when(courierDao.addCourierInfo(courier)).thenReturn(true);
		assertEquals(true, customerService.initiateProcess(courier));
	}
	
	@Test
	public void testMakePaymentCash() {
		assertEquals("Payment done by cash",customerService.makePayment("cash"));
	}
	@Test
	public void testMakePaymentCard() {
		assertEquals("Payment done by card",customerService.makePayment("card"));
	}
	
	@Test
	public void testCheckOnlineTrackingStatus() throws CourierNotFoundException {
		Courier actual=new Courier();
		actual.setCourierid(1);
		actual.setStatus(CourierStatus.intransit);
		when(courierDao.getCourierInfo(1)).thenReturn(actual);
		assertEquals(CourierStatus.intransit, customerService.checkOnlineTrackingStatus(1));
		
	}
	
	@Test 
	public void testCheckOnlineTrackingStatusException() throws CourierNotFoundException {
		when(courierDao.getCourierInfo(100)).thenThrow(CourierNotFoundException.class);
		assertThrows(CourierNotFoundException.class,()-> customerService.checkOnlineTrackingStatus(100));
	}
	@Test
	public void testRegisterComplaint() {
		Complaint complaint=new Complaint(1,1001,"Damaged","Product was damaged",null);
		when(complaintDao.addNewComplaint(complaint)).thenReturn(true);
		assertEquals(true,customerService.registerComplaint(complaint));
	}
	
}
