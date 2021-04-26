package com.cg.datajpa.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.OfficeStaffMember;
import com.cg.datajpa.mts.exception.StaffMemberNotFoundException;
@Repository
public class StaffMemberDAOImp implements IStaffMemberDao {

	@Autowired
    EntityManager em;
	public StaffMemberDAOImp() {
		em=JpaUtils.getEntityManager();
	}
	@Override
	public boolean addStaffMember(OfficeStaffMember staffmember) 
	{
       //em.getTransaction().begin();
		try {
			 em.persist(staffmember);
			 return true;
		}
		catch(Exception ex) {
			
		}
		return false;
       //em.getTransaction().commit();
	}

	@Override
	public boolean removeStaffMember(OfficeStaffMember staffmember)
	{
		//em.getTransaction().begin();
		//OfficeStaffMember o=em.merge(staffmember);
		try {
			em.remove(staffmember);
			return true;
		}
		catch(Exception ex) {
			
		}
		return false;
		
		//em.getTransaction().commit();
	}

	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException 
	{
		OfficeStaffMember member=em.find(OfficeStaffMember.class, empid);
		if(member==null)
			throw new StaffMemberNotFoundException("Staff member data not found");
		else
			return member;
	}

	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) 
	{
		TypedQuery<OfficeStaffMember> qry=em.createQuery("select t from OfficeStaffMember t where t.office.officeid=?1",OfficeStaffMember.class);
		qry.setParameter(1, officeoutlet.getOfficeid());
		List<OfficeStaffMember> list=qry.getResultList();
		return list;
	}

}


