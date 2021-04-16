package com.cg.datajpa.mts.repository;

import java.util.List;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.exception.ComplaintNotFoundException;

public interface IComplaintDao {

	public void addNewComplaint(Complaint complaint);
	public void removeComplaint(Complaint complaint);
	public void updateComplaint(Complaint complaint);
	
	public Complaint getComplaint(int complaintid) throws ComplaintNotFoundException;
	public List<Complaint> getAllComplaints();
}
