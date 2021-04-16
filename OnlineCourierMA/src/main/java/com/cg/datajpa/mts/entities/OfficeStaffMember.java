package com.cg.datajpa.mts.entities;

public class OfficeStaffMember {

	protected int empid;
	protected String name;
	protected Address address;
	protected String role;
	protected CourierOfficeOutlet office;
	public OfficeStaffMember(){}
	public OfficeStaffMember(int empid, String name, Address address, String role, CourierOfficeOutlet office) {
		this.empid = empid;
		this.name = name;
		this.address = address;
		this.role = role;
		this.office = office;
		
	}
	@Override
	public String toString() {
		return "OfficeStaffMember[empid=" +empid +",name ="+name+",adress="+address+",role="+role+",office="+office+"]";
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
}