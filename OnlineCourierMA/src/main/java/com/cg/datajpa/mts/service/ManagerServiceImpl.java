package com.cg.datajpa.mts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;

@Service
public class ManagerServiceImpl implements IManagerService {
	@Autowired
	StaffMemberDAOImp staffMemberDao;
	
	@Autowired
	CourierDAOImp couerirDao;

	@Autowired
	ComplaintDaoImpl complaintDao;

	@Autowired
	OfficeOutletDaoImpl officeDao;
	
	public ManagerServiceImpl() {
		this.staffMemberDao=new StaffMemberDAOImp();
		this.couerirDao=new CourierDAOImp();
		this.complaintDao=new ComplaintDaoImpl();
		this.officeDao=new OfficeOutletDaoImpl();
	}

	@Override
	public boolean addStaffMember(OfficeStaffMember staffmember, int officeid) throws OutletNotFoundException {
		
		CourierOfficeOutlet office = null;
		office = officeDao.getOfficeInfo(officeid);
		if (office != null) {
			List<OfficeStaffMember> members = office.getStaffmembers();
			members.add(staffmember);
			return officeDao.updateOffice(office);
		} else {
			throw new OutletNotFoundException("Office not found");
		}
		

	}

	@Override
	public boolean removeStaffMember(OfficeStaffMember staffmember) {
		// TODO Auto-generated method stub
		return staffMemberDao.removeStaffMember(staffmember);
	}

	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException {
		// TODO Auto-generated method stub
		OfficeStaffMember member = null;
		try {
			member = staffMemberDao.getStaffMember(empid);
		} catch (StaffMemberNotFoundException ex) {

		}
		if (member != null) {
			return member;
		} else
			throw new StaffMemberNotFoundException("Staff not found");

	}

	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) {
		// TODO Auto-generated method stub
		List<OfficeStaffMember> data = new ArrayList<>();
		data = staffMemberDao.getAllStaffMembers(officeoutlet);
		return data;
	}

	@Override
	public CourierStatus getCourierStatus(Courier courier) throws CourierNotFoundException {
		// TODO Auto-generated method stub
		Courier updatedcourier = null;

		updatedcourier = couerirDao.getCourierInfo(courier.getCourierid());

		if (updatedcourier == null) {
			throw new CourierNotFoundException("Courier not found");
		} else {
			return updatedcourier.getStatus();
		}

	}

	@Override
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException {
		// TODO Auto-generated method stub
		Complaint member = null;
		try {
			member = complaintDao.getComplaint(complaintid);
		} catch (ComplaintNotFoundException ex) {

		}
		return member;
	}

	@Override
	public List<Complaint> getAllComplaints() {
		// TODO Auto-generated method stub
		List<Complaint> data = new ArrayList<>();
		data = complaintDao.getAllComplaints();
		return data;
	}

	public List<Courier> getAllDeliveredCouriers() {
		List<Courier> couriers = new ArrayList<>();
		couriers = couerirDao.getAllDeliveredCouriers();
		return couriers;
	}
}
