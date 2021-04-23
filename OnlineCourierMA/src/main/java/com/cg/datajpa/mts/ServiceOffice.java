package com.cg.datajpa.mts;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.cg.datajpa.mts.entities.Address;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;
import com.cg.datajpa.mts.service.OfficeOutletServiceImpl;

public class ServiceOffice {
	public static void main(String[] args) {
		OfficeOutletServiceImpl outletService=new OfficeOutletServiceImpl();
		outletService.setOfficeOutletDAO(new OfficeOutletDaoImpl());
		CourierOfficeOutlet office=new CourierOfficeOutlet();
		office.setOpeningTime(LocalTime.parse("09:00:00"));
		office.setClosingTime(LocalTime.parse("17:00:00"));
		try {
			System.out.println(outletService.isOfficeOpen(office));
		}
		catch(Exception ex) {
			
		}
		try {
			System.out.println(outletService.isOfficeClosed(office));
		}
		catch(Exception ex) {
			
		}
		CourierOfficeOutlet newOffice=new CourierOfficeOutlet();
		
		Address a1=new Address("RV Road","Jsr","JH","India",83001);
		newOffice.setAddress(a1);
		newOffice.setOpeningTime(LocalTime.parse("08:00:00"));
		newOffice.setClosingTime(LocalTime.parse("19:00:00"));
		OfficeStaffMember s1=new OfficeStaffMember("Vivek",a1,newOffice,202);
		OfficeStaffMember s2=new OfficeStaffMember("Santosh",a1,newOffice,202);
		List<OfficeStaffMember> employees=Arrays.asList(s1,s2);
		newOffice.setStaffmembers(employees);
		System.out.println(newOffice);
		//outletService.addNewOffice(newOffice);
		//outletService.removeNewOffice(newOffice);
		CourierOfficeOutlet outlet=null;
		try {
		outlet=outletService.getOfficeInfo(101);
		}
		catch(Exception ex) {
			
		}
		System.out.println(outlet);
		System.out.println("------xxxxxxxxxx---------");
		List<CourierOfficeOutlet> outlets=outletService.getAllOfficesData();
		for(CourierOfficeOutlet o:outlets){
			System.out.println(o);
		}
		
		
	}
}
