package com.cg.datajpa.mts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.CourierDAOImp;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShipmentServiceImplTest {

	@MockBean
	CourierDAOImp courdao;
	
	@Autowired
	ShipmentServiceImp shipment;
	@Test
	public void testInitiateShipmentProcess() {
		Courier courier = new Courier();
		courier.setCourierid(100);
		when(courdao.updateCourierInfoSet(100, CourierStatus.intransit)).thenReturn(true);
		assertEquals(true, shipment.initiateShipmentTransaction(100));
		
	}

	@Test
	public void testCheckOnlineTrackingStatus() throws CourierNotFoundException {
		Courier actual=new Courier();
		actual.setCourierid(1);
		actual.setStatus(CourierStatus.rejected);
		when(courdao.getCourierInfo(1)).thenReturn(actual);
		assertEquals(CourierStatus.rejected, shipment.checkShipmentStatus(actual));
		
	}

	@Test 
	public void testCheckOnlineTrackingStatusException() throws CourierNotFoundException {
		when(courdao.getCourierInfo(10)).thenThrow(CourierNotFoundException.class);
		assertThrows(CourierNotFoundException.class,()-> shipment.checkShipmentStatus(courdao.getCourierInfo(10)));
	}

	@Test
	public void testCloseShipmentTransaction() {
		Courier courier = new Courier();
		courier.setCourierid(100);
		when(courdao.updateCourierInfoSet(100, CourierStatus.delivered)).thenReturn(true);
		assertEquals(true, shipment.closeShipmentTransaction(100));
		
	}

	@Test
	public void testRejectShipmentProcess() {
		Courier courier = new Courier();
		courier.setCourierid(100);
		when(courdao.updateCourierInfoSet(100, CourierStatus.rejected)).thenReturn(true);
		assertEquals(true, shipment.rejectShipmentTransaction(100));
		
	}
}