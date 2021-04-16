package com.cg.mts.service;

import java.util.List;

import com.cg.mts.entities.Complaint;
import com.cg.mts.entities.Courier;
import com.cg.mts.entities.CourierOfficeOutlet;
import com.cg.mts.entities.OfficeStaffMember;
import com.cg.mts.exception.ComplaintNotFoundException;
import com.cg.mts.exception.CourierNotFoundException;
import com.cg.mts.exception.StaffMemberNotFoundException;

public interface IManagerService {

	public void addStaffMember(OfficeStaffMember staffmember);
	public void removeStaffMember(OfficeStaffMember staffmember);
	
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException;
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet);
	
	public boolean getCourierStatus(Courier courier) throws CourierNotFoundException;
	
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException;
	public List<Complaint> getAllComplaints();
}
