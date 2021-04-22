package com.cg.datajpa.mts.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;

@Repository
public class ComplaintDaoImpl implements IComplaintDao
{
	@Autowired
	EntityManager em;
	
	public ComplaintDaoImpl() {
		em=JpaUtils.getEntityManager();
	}
	
	@Override
	public void addNewComplaint(Complaint complaint) {
		//em.getTransaction().begin();
		em.persist(complaint);
		//em.getTransaction().commit();
	}

	
	@Override
	public void removeComplaint(Complaint complaint) {
		//em.getTransaction().begin();
		Complaint c=em.merge(complaint);
		em.remove(c);
		//em.getTransaction().commit();
	}

	@Override
	public void updateComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		//em.getTransaction().begin();
		Complaint c=em.merge(complaint);
		em.merge(c);
		//em.getTransaction().commit();
	}

	@Override
	public Complaint getComplaint(int complaintid) throws ComplaintNotFoundException {
		// TODO Auto-generated method stub
		Complaint c=em.find(Complaint.class,complaintid);
		 if(c!=null)
		 {
			 return c;
		 }
		 else
			 throw new ComplaintNotFoundException("Complaint does not exist");
	}

	@Override
	public List<Complaint> getAllComplaints()
	{
		List<Complaint> comp=(List<Complaint>) em.createQuery("Select t from Complaint t").getResultList();
		return comp;
	}
}