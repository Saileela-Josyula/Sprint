package com.cg.datajpa.mts.repository;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.datajpa.mts.entities.Courier;



public class CourierDAOImp implements ICourierDao {
	
	@Autowired
	EntityManager eManager;
	public CourierDAOImp() {
		eManager=JpaUtils.getEntityManager();
	}
	@Override
	public void addCourierInfo(Courier courier)
	{
		eManager.getTransaction().begin();
		eManager.persist(courier);
		eManager.getTransaction().commit();
	}
	@Override
	public Courier getCourierInfo(int courierid)
	{	
		return eManager.find(Courier.class, courierid);
	}
	@Override
	public void removeCourierInfo(int courierid)
	{
		eManager.getTransaction().begin();
		eManager.remove(eManager.find(Courier.class, courierid));
		eManager.getTransaction().commit();
	}
	
	@Override
	public List<Courier> getAllDeliveredCouriers()
	{
		List<Courier> cour = eManager.createQuery("Select t from Courier t where t.status=2").getResultList();
		return cour;
	}
	@Override
	public List<Courier> getAllDeliveredCouriersByDate(LocalDate date)
	{	TypedQuery<Courier> qry=eManager.createQuery("Select t from Courier t where t.deliveredDate<=?1",Courier.class);
		qry.setParameter(1, date);
		List<Courier> c=qry.getResultList();
		return c;
	}
	}
	