package com.cg.datajpa.mts;

import java.util.List;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;

public class Test3 {

	public static void main(String[] args) {
		
		ComplaintDaoImpl complaintdao=new ComplaintDaoImpl();
		Complaint complaint=new Complaint(402,23102,"Damaged Product","Products inside courier was damaged when I received it, also packaging was damaged",null);
		//complaintdao.addNewComplaint(complaint);
		//complaintdao.removeComplaint(complaint);
		Complaint complaintUpdated=new Complaint(402,23102,"Damaged Product","Products inside courier was damaged when I received it",null);
		//complaintdao.updateComplaint(complaintUpdated);
		try {
			System.out.println(complaintdao.getComplaint(401));
		}
		catch(Exception ex) {}
		List<Complaint> complaints=complaintdao.getAllComplaints();
		for(Complaint c:complaints) {
			System.out.println(c);
		}
	}
}
