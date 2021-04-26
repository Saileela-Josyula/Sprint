package com.cg.datajpa.mts.repository;

import java.util.List;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;

public interface IStaffMemberDao {
	
	public boolean addStaffMember(OfficeStaffMember staffmember);
	public boolean removeStaffMember(OfficeStaffMember staffmember);
	
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException;
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet);

}
