package com.cg.datajpa.mts.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.OutletNotFoundException;

@Repository
public class OfficeOutletDaoImpl implements IOfficeOutletDao {
	@Autowired
	EntityManager entityManager;

	public OfficeOutletDaoImpl() {
		entityManager = JpaUtils.getEntityManager();
	}

	@Override
	public boolean addNewOffice(CourierOfficeOutlet officeoutlet) {
		try {
			entityManager.persist(officeoutlet);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	@Override
	public boolean removeNewOffice(CourierOfficeOutlet officeoutlet) {
		try {
			CourierOfficeOutlet managed = entityManager.merge(officeoutlet);
			entityManager.remove(managed);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	public boolean updateOffice(CourierOfficeOutlet officeoutlet) {
		try {
			entityManager.merge(officeoutlet);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	@Override
	public CourierOfficeOutlet getOfficeInfo(int officeid) throws OutletNotFoundException {
		CourierOfficeOutlet o = entityManager.find(CourierOfficeOutlet.class, officeid);
		if (o == null)
			throw new OutletNotFoundException("Outlet does not exists");
		else
			return o;
	}

	@Override
	public List<CourierOfficeOutlet> getAllOfficesData() {
		return entityManager.createQuery("Select t from CourierOfficeOutlet t", CourierOfficeOutlet.class).getResultList();
	}
}
