package com.cg.datajpa.mts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
@Table(name="officemember")
public class OfficeStaffMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "member_Sequence")
	@SequenceGenerator(name = "member_Sequence", sequenceName = "member_seq",allocationSize  =1,initialValue = 1)
	@Column(name="empid")
	protected int empid;
	@Column(name="name")
	protected String name;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="addid")
	protected Address address;
	@Column(name="mid")
	int mid;
	
	
	
	public OfficeStaffMember(){}
	
	public OfficeStaffMember(String name, Address address,int mid) {
		super();
		this.name = name;
		this.address = address;
		this.mid=mid;
	}


	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getEmpid() {
		return empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	


	@Override
	public String toString() {
		return "OfficeStaffMember [empid=" + empid + ", name=" + name + ", address=" + address 
				+ ", mid=" + mid + "]";
	}
	
}