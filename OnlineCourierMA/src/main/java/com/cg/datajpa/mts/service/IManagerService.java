package com.cg.datajpa.mts.service;

import java.util.List;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;

public interface IManagerService {

	public void addStaffMember(OfficeStaffMember staffmember,int officeid)throws OutletNotFoundException;
	public void removeStaffMember(OfficeStaffMember staffmember);
	
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException;
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet);
	
	public CourierStatus getCourierStatus(Courier courier) throws CourierNotFoundException;
	
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException;
	public List<Complaint> getAllComplaints();
}
