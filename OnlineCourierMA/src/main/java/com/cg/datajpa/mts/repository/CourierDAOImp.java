package com.cg.datajpa.mts.repository;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;



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
	@Override
	public void updateCourierInfoSet(Courier status,CourierStatus s) {
		eManager.getTransaction().begin();
		Query qry= eManager.createQuery("update Courier c set c.status=?2 where c.courierid=?1");
		qry.setParameter(1, status.getCourierid());
		qry.setParameter(2, s);
		qry.executeUpdate();
		eManager.getTransaction().commit();
		
	}
	}
	