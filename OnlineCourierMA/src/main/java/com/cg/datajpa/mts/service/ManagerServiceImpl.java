package com.cg.datajpa.mts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.repository.OfficeOutletDaoImpl;
import com.cg.datajpa.mts.repository.StaffMemberDAOImp;

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
		this.staffMemberDao = new StaffMemberDAOImp();
		this.couerirDao = new CourierDAOImp();
		this.complaintDao = new ComplaintDaoImpl();
		this.officeDao = new OfficeOutletDaoImpl();
	}

	/*
	 * Method:addStaffMember add staff member using office id
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
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

	/*
	 * Method:removeStaffMember remove staff member
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public boolean removeStaffMember(OfficeStaffMember staffmember) {
		return staffMemberDao.removeStaffMember(staffmember);
	}

	/*
	 * Method:getStaffMember fetch an employee data using employee id
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException {
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

	/*
	 * Method:getAllStaffMembers get all staff members working in office office
	 * 
	 * CreatedBy:Ede Chandini CreatedDate:22 April 2021
	 */
	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) {
		List<OfficeStaffMember> officeMembers;
		officeMembers = staffMemberDao.getAllStaffMembers(officeoutlet);
		return officeMembers;
	}

	/*
	 * Method:getCourierStatus check status of any courier
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public CourierStatus getCourierStatus(Courier courier) throws CourierNotFoundException {
		Courier updatedcourier = null;
		updatedcourier = couerirDao.getCourierInfo(courier.getCourierid());
		if (updatedcourier == null) {
			throw new CourierNotFoundException("Courier not found");
		} else {
			return updatedcourier.getStatus();
		}
	}

	/*
	 * Method:getRegistedComplaint show registerd complaint using complaint id
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public Complaint getRegistedComplaint(int complaintid) throws ComplaintNotFoundException {
		Complaint complaint = null;
		try {
			complaint = complaintDao.getComplaint(complaintid);
		} catch (ComplaintNotFoundException ex) {
		}
		return complaint;
	}

	/*
	 * Method:getAllComplaint show all complaints in database
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	@Override
	public List<Complaint> getAllComplaints() {
		List<Complaint> complaints;
		complaints = complaintDao.getAllComplaints();
		return complaints;
	}

	/*
	 * Method:getAllDeliveredCourier fetch all couriers which are delivered
	 * 
	 * CreatedBy:Venkatesh Murty CreatedDate:22 April 2021
	 */
	public List<Courier> getAllDeliveredCouriers() {
		List<Courier> deliveredCouriers;
		deliveredCouriers = couerirDao.getAllDeliveredCouriers();
		return deliveredCouriers;
	}
}
