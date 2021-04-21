package com.cg.datajpa.mts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;

public class ManagerServiceImpl implements IManagerService {
	@Autowired
	StaffMemberDAOImp dao;
	
	@Autowired
	CourierDAOImp da;
	
	@Autowired
	ComplaintDaoImpl d;
	
	
	
	
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
	public CourierStatus getCourierStatus(Courier courier) throws CourierNotFoundException {
		// TODO Auto-generated method stub
		CourierStatus member=null;
		Courier updatedcourier= null;
		
			updatedcourier=da.getCourierInfo(courier.getCourierid());
		
		if(updatedcourier==null) {
			throw new CourierNotFoundException();
		}
		else {
			return updatedcourier.getStatus();
		}
		
	}

	@Override
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException {
		// TODO Auto-generated method stub
		Complaint member=null;
		try {
			member=d.getComplaint(complaintid);
		}
		catch(ComplaintNotFoundException ex) {
			
		}
		return member;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		// TODO Auto-generated method stub
		List<Complaint> data=new ArrayList<>();
		data=d.getAllComplaints();
		return data;
	}
}
