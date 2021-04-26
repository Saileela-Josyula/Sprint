package com.cg.datajpa.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
@Repository
public class OfficeOutletDaoImpl implements IOfficeOutletDao {
	@Autowired
	EntityManager em;
	public OfficeOutletDaoImpl() {
		em=JpaUtils.getEntityManager();
	}
	
	@Override
	public boolean addNewOffice(CourierOfficeOutlet officeoutlet) {
		//em.getTransaction().begin();
		try {
			em.persist(officeoutlet);
			return true;
		}
		catch(Exception ex) {
			
		}
		return false;
		//em.getTransaction().commit();
	}

	@Override
	public boolean removeNewOffice(CourierOfficeOutlet officeoutlet) {
		//.getTransaction().begin();
		try {
			CourierOfficeOutlet managed=em.merge(officeoutlet);
			em.remove(managed);
			return true;
		}
		catch(Exception ex) {
			
		}
		return false;
		//em.getTransaction().commit();
	}
	
	public boolean updateOffice(CourierOfficeOutlet officeoutlet) {
		try {
			em.merge(officeoutlet);
			return true;
		}
		catch(Exception ex) {
			
		}
		return false;
		
	}

	@Override
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException {
		CourierOfficeOutlet o=em.find(CourierOfficeOutlet.class, officeid);
		if(o==null)
			throw new OutletNotFoundException("Outlet does not exists");
		else 
			return o;
	}

	@Override
	public List<CourierOfficeOutlet> getAllOfficesData() {
		List<CourierOfficeOutlet> outlets = em.createQuery("Select t from CourierOfficeOutlet t",CourierOfficeOutlet.class).getResultList();
		return outlets;
	}

}
