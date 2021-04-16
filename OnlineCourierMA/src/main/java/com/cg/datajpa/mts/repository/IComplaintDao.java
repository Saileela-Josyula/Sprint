package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.Complaint;
import com.cg.mts.exception.ComplaintNotFoundException;

public interface IComplaintDao {

	public void addNewComplaint(Complaint complaint);
	public void removeComplaint(Complaint complaint);
	public void updateComplaint(Complaint complaint);
	
	public Complaint getComplaint(int complaintid) throws ComplaintNotFoundException;
	public List<Complaint> getAllComplaints();
}
