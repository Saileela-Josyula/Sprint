package com.cg.datajpa.mts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="complaint")
public class Complaint {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Complaint_Sequence")
	@SequenceGenerator(name = "Complaint_Sequence", sequenceName = "complaint_seq",allocationSize  =1,initialValue = 1)
	@Column(name="complaintid")
	private int complaintid;
	@Column(name="consignmentno")
	private int consignmentno;
	@Column(name="shortdescription")
	private String shortdescription;
	@Column(name="detaileddescription")
	private String detaileddescription;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerid")
	private Customer customer;
	
	public Complaint()
	{}
	
	

	public Complaint( int consignmentno, String shortdescription, String detaileddescription,
			Customer customer) {
		super();
		this.consignmentno = consignmentno;
		this.shortdescription = shortdescription;
		this.detaileddescription = detaileddescription;
		this.customer = customer;
	}

	
	
	public Complaint(int complaintid, int consignmentno, String shortdescription, String detaileddescription,
			Customer customer) {
		super();
		this.complaintid = complaintid;
		this.consignmentno = consignmentno;
		this.shortdescription = shortdescription;
		this.detaileddescription = detaileddescription;
		this.customer = customer;
	}



	public String getDetaileddescription() {
		return detaileddescription;
	}



	public void setDetaileddescription(String detaileddescription) {
		this.detaileddescription = detaileddescription;
	}



	public void setConsignmentno(int consignmentno) {
		this.consignmentno = consignmentno;
	}



	public int getComplaintid() {
		return complaintid;
	}

	
	public int getConsignmentno() {
		return consignmentno;
	}

	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public String getDetaildescription() {
		return detaileddescription;
	}

	public void setDetaildescription(String detaildescription) {
		this.detaileddescription = detaildescription;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Complaint [complaintid=" + complaintid + ", consignmentno=" + consignmentno + ", shortdescription="
				+ shortdescription + ", detaildescription=" + detaileddescription + ", customer=" + customer + "]";
	}
	
	
	
	
}