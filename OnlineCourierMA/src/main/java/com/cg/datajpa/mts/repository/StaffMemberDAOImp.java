package com.cg.datajpa.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
@Repository
public class StaffMemberDAOImp implements IStaffMemberDao {

	@Autowired
    EntityManager entityManager;
	public StaffMemberDAOImp() {
		entityManager=JpaUtils.getEntityManager();
	}
	@Override
	public boolean addStaffMember(OfficeStaffMember staffmember) 
	{
		try {
			 entityManager.persist(staffmember);
			 return true;
		}
		catch(Exception ex) {
		}
		return false;
	}
	@Override
	public boolean removeStaffMember(OfficeStaffMember staffmember)
	{
		try {
			entityManager.remove(staffmember);
			return true;
		}
		catch(Exception ex) {	
		}
		return false;
	}

	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException 
	{
		OfficeStaffMember member=entityManager.find(OfficeStaffMember.class, empid);
		if(member==null)
			throw new StaffMemberNotFoundException("Staff member data not found");
		else
			return member;
	}

	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) 
	{
		TypedQuery<OfficeStaffMember> query=entityManager.createQuery("select t from OfficeStaffMember t where t.office.officeid=?1",OfficeStaffMember.class);
		query.setParameter(1, officeoutlet.getOfficeid());
		return query.getResultList();
	}

}


