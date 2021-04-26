package com.cg.datajpa.mts.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.datajpa.mts.entities.Address;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeMember;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.OutletClosedException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeOutletServiceImplTest 
{
	
	@Autowired
	OfficeOutletServiceImpl officeService;
	@MockBean
	OfficeOutletDaoImpl officedao;
	@Test
	public void testAddNewOffice() {
		CourierOfficeOutlet office=new CourierOfficeOutlet();
		when(officedao.addNewOffice(office)).thenReturn(true);
		assertEquals(true, officeService.addNewOffice(office));
	}
	
	@Test
	public void removeNewOffice()
	{
		CourierOfficeOutlet office=new CourierOfficeOutlet();
		when(officedao.removeNewOffice(office)).thenReturn(true);
		assertEquals(true,officeService.removeNewOffice(office) );
	}
	@Test
	public void  testGetOfficeInfo() throws OutletNotFoundException
	{
		CourierOfficeOutlet office=new CourierOfficeOutlet();
		when(officedao.getOfficeInfo(1)).thenReturn(office);
		assertEquals(office,officeService.getOfficeInfo(1));
	}
	
	@Test
	public void  testGetAllOfficesData()
	{
		List<CourierOfficeOutlet> list=new ArrayList<>();
		list.add(new CourierOfficeOutlet());
		list.add(new CourierOfficeOutlet());
		when(officedao.getAllOfficesData()).thenReturn(list);
		assertEquals(2,officeService.getAllOfficesData().size());
	}
	
	@Test
	public void testIsOfficeOpen() throws OutletClosedException
	{
		CourierOfficeOutlet office=new CourierOfficeOutlet(null,LocalTime.now().parse("09:00:00"),LocalTime.now().parse("19:00:00"),null);
		assertEquals(false, officeService.isOfficeOpen(office));
	}
	@Test
	public void testIsOfficeClosed() throws OutletClosedException 
	{
		CourierOfficeOutlet office=new CourierOfficeOutlet(null,LocalTime.now().parse("09:00:00"),LocalTime.now().parse("19:00:00"),null);
		assertEquals(true, officeService.isOfficeClosed(office));
	}

}