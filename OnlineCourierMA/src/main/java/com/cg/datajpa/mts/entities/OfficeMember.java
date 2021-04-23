package com.cg.datajpa.mts.entities;

public class OfficeMember {
	
	OfficeStaffMember member;
	int officeid;
	public OfficeMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OfficeMember(OfficeStaffMember member, int officeid) {
		super();
		this.member = member;
		this.officeid = officeid;
	}
	public OfficeStaffMember getMember() {
		return member;
	}
	public void setMember(OfficeStaffMember member) {
		this.member = member;
	}
	public int getOfficeid() {
		return officeid;
	}
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}
	
}
