package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.CourierOfficeOutlet;
import com.cg.mts.entities.OfficeStaffMember;
import com.cg.mts.exception.StaffMemberNotFoundException;

public interface IStaffMemberDao {
	
	public void addStaffMember(OfficeStaffMember staffmember);
	public void removeStaffMember(OfficeStaffMember staffmember);
	
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException;
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet);

}
