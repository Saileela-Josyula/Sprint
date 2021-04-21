package com.cg.datajpa.mts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;

public class ManagerServiceImpl implements IManagerService {
	@Autowired
	StaffMemberDAOImp dao;
	
	public void setStaffMemberDAOImpl(StaffMemberDAOImp staffDAO) {
		this.dao=staffDAO;
	}
	
	
	
	@Override
	public void addStaffMember(OfficeStaffMember staffmember) {
		// TODO Auto-generated method stub
		dao.addStaffMember(staffmember);
	}

	@Override
	public void removeStaffMember(OfficeStaffMember staffmember) {
		// TODO Auto-generated method stub
		dao.removeStaffMember(staffmember);
	}

	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException {
		// TODO Auto-generated method stub
		OfficeStaffMember member=null;
		try {
			member=dao.getStaffMember(empid);
		}
		catch(StaffMemberNotFoundException ex) {
			
		}
		return member;
	}

	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) {
		// TODO Auto-generated method stub
		List<OfficeStaffMember> data=new ArrayList<>();
		data=dao.getAllStaffMembers(officeoutlet);
		return data;
	}

	@Override
	public boolean getCourierStatus(Courier courier) throws CourierNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		// TODO Auto-generated method stub
		return null;
	}

}
