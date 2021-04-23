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
	public void addNewOffice(CourierOfficeOutlet officeoutlet) {
		//em.getTransaction().begin();
		em.persist(officeoutlet);
		//em.getTransaction().commit();
	}

	@Override
	public void removeNewOffice(CourierOfficeOutlet officeoutlet) {
		//.getTransaction().begin();
		CourierOfficeOutlet managed=em.merge(officeoutlet);
		em.remove(managed);
		//em.getTransaction().commit();
	}
	
	public void updateOffice(CourierOfficeOutlet officeoutlet) {
		em.merge(officeoutlet);
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
		List<CourierOfficeOutlet> outlets = em.createQuery("Select t from CourierOfficeOutlet t").getResultList();
		return outlets;
	}

}
