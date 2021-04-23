package com.cg.datajpa.mts;

import java.util.List;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;

public class Test5 {
	
	public static void main(String[] args) {
		StaffMemberDAOImp staffdao=new StaffMemberDAOImp();
		CourierOfficeOutlet office=new CourierOfficeOutlet(null,null,null,null);
		OfficeStaffMember s1=new OfficeStaffMember("Anurag",null,office,202);
		OfficeStaffMember s2=new OfficeStaffMember("Venkatesh",null,office,202);
		
		//staffdao.addStaffMember(s1);
		//staffdao.addStaffMember(s2);
		
		//staffdao.removeStaffMember(s1);
		//staffdao.removeStaffMember(s2);
		try {
			System.out.println(staffdao.getStaffMember(702));
		}
		catch(Exception ex) {
			
		}
		
		List<OfficeStaffMember> members=staffdao.getAllStaffMembers(office);
		for(OfficeStaffMember o:members) {
			System.out.println(o);
		}
		
	}
}
