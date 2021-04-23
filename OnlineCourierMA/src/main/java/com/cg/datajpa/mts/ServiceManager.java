package com.cg.datajpa.mts;

import java.time.LocalDate;
import java.util.List;

import com.cg.datajpa.mts.entities.Address;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;
import com.cg.datajpa.mts.service.ManagerServiceImpl;

public class ServiceManager {
	
	public static void main(String[] args) {
		ManagerServiceImpl serviceManager=new ManagerServiceImpl();
		serviceManager.setStaffMemberDAOImpl(new StaffMemberDAOImp());
		serviceManager.setCourierDAOImp(new CourierDAOImp());
		serviceManager.setComplaintDAOImp(new ComplaintDaoImpl());
		
		OfficeStaffMember s1=new OfficeStaffMember("Megha",null,null,201);
		OfficeStaffMember s2=new OfficeStaffMember("Pratyush",null,null,201);
		//serviceManager.addStaffMember(s2);
		
		//serviceManager.removeStaffMember(s1);
		try {
			System.out.println(serviceManager.getStaffMember(702));
		}
		catch(StaffMemberNotFoundException ex) {
			
		}
		
		Courier courier=new Courier(CourierStatus.iniated,null,null,LocalDate.now(),LocalDate.parse("2021-04-25"));
		try {
			System.out.println(serviceManager.getCourierStatus(courier));
		}
		catch(CourierNotFoundException c) {
			
		}
		
		try {
			System.out.println(serviceManager.getRegistedComplaint(402));
		}
		catch(ComplaintNotFoundException c) {
			
		}
		
		List<Complaint> complaints=serviceManager.getAllComplaints();
		for(Complaint c:complaints) {
			System.out.println(c);
		}
		Address address=new Address("139","Jamshedpur","Jharkhand","India",831001);
		CourierOfficeOutlet office=new CourierOfficeOutlet(address,null,null,null);
		List<OfficeStaffMember> data=serviceManager.getAllStaffMembers(office);
		for(OfficeStaffMember member:data) {
			System.out.println(member);
		}
	
	}
}
