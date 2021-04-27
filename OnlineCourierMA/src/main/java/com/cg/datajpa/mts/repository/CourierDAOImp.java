package com.cg.datajpa.mts.repository;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.exception.CourierNotFoundException;

@Repository
public class CourierDAOImp implements ICourierDao {

	@Autowired
	EntityManager eManager;

	public CourierDAOImp() {
		eManager = JpaUtils.getEntityManager();
	}

	@Override
	public boolean addCourierInfo(Courier courier) {
		try {
			eManager.persist(courier);
			return true;
		} catch (Exception ex) {

		}
		return false;
	}

	@Override
	public Courier getCourierInfo(int courierid) throws CourierNotFoundException {
		Courier courier = null;
		courier = eManager.find(Courier.class, courierid);
		if (courier != null)
			return courier;
		else
			throw new CourierNotFoundException("Courier not found");
	}

	@Override
	public void removeCourierInfo(int courierid) {
		eManager.remove(eManager.find(Courier.class, courierid));
	}

	@Override
	public List<Courier> getAllDeliveredCouriers() {
		return eManager.createQuery("Select t from Courier t where t.status=2", Courier.class).getResultList();
	}

	@Override
	public List<Courier> getAllDeliveredCouriersByDate(LocalDate date) {
		TypedQuery<Courier> qry = eManager.createQuery("Select t from Courier t where t.deliveredDate<=?1",
				Courier.class);
		qry.setParameter(1, date);
		return qry.getResultList();
	}

	@Override
	public boolean updateCourierInfo(int courierid, CourierStatus status) throws CourierNotFoundException {
		Courier courier = null;
		courier = eManager.find(Courier.class, courierid);
		if(courier!=null) {
		Query qry = eManager.createQuery("update Courier c set c.status=?1 where c.courierId=?2");
		qry.setParameter(2, courierid);
		qry.setParameter(1, status);
		qry.executeUpdate();
		return true;
		}
		else
			throw new CourierNotFoundException("Courier not found");

	}
}
