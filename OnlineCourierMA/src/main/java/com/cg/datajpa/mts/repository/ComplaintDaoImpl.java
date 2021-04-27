package com.cg.datajpa.mts.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;

@Repository
public class ComplaintDaoImpl implements IComplaintDao {
	@Autowired
	EntityManager entitymanager;

	public ComplaintDaoImpl() {
		entitymanager = JpaUtils.getEntityManager();
	}

	@Override
	public boolean addNewComplaint(Complaint complaint) {
		entitymanager.persist(complaint);
		return true;
	}

	@Override
	public void removeComplaint(Complaint complaint) {
		Complaint meged = entitymanager.merge(complaint);
		entitymanager.remove(meged);
	}

	@Override
	public void updateComplaint(Complaint complaint) {
		Complaint merged = entitymanager.merge(complaint);
		entitymanager.merge(merged);
	}

	@Override
	public Complaint getComplaint(int complaintid) throws ComplaintNotFoundException {
		Complaint complaint = entitymanager.find(Complaint.class, complaintid);
		if (complaint != null) {
			return complaint;
		} else
			throw new ComplaintNotFoundException("Complaint does not exist");
	}

	@Override
	public List<Complaint> getAllComplaints() {
		List<Complaint> complaints = (List<Complaint>) entitymanager.createQuery("Select t from Complaint t",Complaint.class).getResultList();
		return complaints;
	}
}