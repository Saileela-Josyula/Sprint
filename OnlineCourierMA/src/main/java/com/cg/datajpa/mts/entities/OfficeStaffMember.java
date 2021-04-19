package com.cg.datajpa.mts.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role")
@Entity
@Table(name="officemember")
public class OfficeStaffMember {
	
	@Id
	@Column(name="empid")
	protected int empid;
	@Column(name="name")
	protected String name;
	@OneToOne
	@JoinColumn(name="addid")
	protected Address address;
	@Column(name="role")
	protected String role;
	@ManyToOne
	@JoinColumn(name="officeid",updatable=false,insertable=false)
	protected CourierOfficeOutlet office;
	
	@ManyToOne
	@JoinColumn(name="mid",updatable=false,insertable=false)
	protected Manager manager;
	public OfficeStaffMember(){}
	
	public OfficeStaffMember(int empid, String name, Address address, String role, CourierOfficeOutlet office,
			Manager manager) {
		super();
		this.empid = empid;
		this.name = name;
		this.address = address;
		this.role = role;
		this.office = office;
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "OfficeStaffMember[empid=" +empid +",name ="+name+",adress="+address+",role="+role+"]";
	}
	protected int getEmpid() {
		return empid;
	}
	protected void setEmpid(int empid) {
		this.empid = empid;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected Address getAddress() {
		return address;
	}
	protected void setAddress(Address address) {
		this.address = address;
	}
	protected String getRole() {
		return role;
	}
	protected void setRole(String role) {
		this.role = role;
	}
	protected CourierOfficeOutlet getOffice() {
		return office;
	}
	protected void setOffice(CourierOfficeOutlet office) {
		this.office = office;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}