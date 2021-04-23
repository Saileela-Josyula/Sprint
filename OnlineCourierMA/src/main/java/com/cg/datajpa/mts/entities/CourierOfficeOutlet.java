package com.cg.datajpa.mts.entities;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="officeoutlet")
public class CourierOfficeOutlet {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "office_Sequence")
	@SequenceGenerator(name = "office_Sequence", sequenceName = "office_seq",allocationSize  =1,initialValue = 1)
	@Column(name="officeid")
	private int officeid;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addid")
	private Address address;
	@Column(name="openingtime")
	private LocalTime openingTime;
	@Column(name="closingtime")
	private LocalTime closingTime;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="officeid")
	private List<OfficeStaffMember> staffmembers;
	
	
	public CourierOfficeOutlet()
	{}
	

	public CourierOfficeOutlet( Address address, LocalTime openingTime, LocalTime closingTime,
			List<OfficeStaffMember> staffmembers) {
		super();
		this.address = address;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.staffmembers = staffmembers;
	}


	public int getOfficeid() {
		return officeid;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public LocalTime getOpeningTime() {
		return openingTime;
	}


	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}


	public LocalTime getClosingTime() {
		return closingTime;
	}


	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}


	public List<OfficeStaffMember> getStaffmembers() {
		return staffmembers;
	}


	public void setStaffmembers(List<OfficeStaffMember> staffmembers) {
		this.staffmembers = staffmembers;
	}


	@Override
	public String toString() {
		return "CourierOfficeOutlet [officeid=" + officeid + ", address=" + address + ", openingTime=" + openingTime
				+ ", closingTime=" + closingTime + ", staffmembers=" + staffmembers + "]";
	}
	
}