package com.cg.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.cg.mts.entities.CourierOfficeOutlet;
import com.cg.mts.entities.OfficeStaffMember;
import com.cg.mts.exception.StaffMemberNotFoundException;
@Repository
public class StaffMemberImpl implements IStaffMemberDao{

	@PersistenceUnit
    private EntityManagerFactory emf;
	@Override
	public void addStaffMember(OfficeStaffMember staffmember) 
	{
       EntityManager em=emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(staffmember);
       em.getTransaction().commit();
	   em.close();
	}

	@Override
	public void removeStaffMember(OfficeStaffMember staffmember)
	{
		EntityManager em=emf.createEntityManager();
	       em.getTransaction().begin();
	       em.remove(staffmember);
	       em.getTransaction().commit();
		   em.close();
	}

	@Override
	public OfficeStaffMember getStaffMember(int empid) throws StaffMemberNotFoundException 
	{
		EntityManager em=emf.createEntityManager();
	    OfficeStaffMember sm=em.find(OfficeStaffMember.class, empid);
        em.close();
        if(sm==null)
        	throw new StaffMemberNotFoundException("Staff member does not exists!!!");
        else
		   return sm;
	}

	@Override
	public List<OfficeStaffMember> getAllStaffMembers(CourierOfficeOutlet officeoutlet) 
	{
		EntityManager em=emf.createEntityManager();
		List<OfficeStaffMember> osm=em.createQuery("select s from staffmember s").getResultList();
        em.close();
		return osm;
	}

}
