package com.cg.datajpa.mts.entities;

import java.util.List;

public class Manager extends OfficeStaffMember {

	private List<OfficeStaffMember> reportingstaffmembers;
	public Manager() {
		
	}

	public Manager(List<OfficeStaffMember> reportingstaffmembers) {
		
		this.reportingstaffmembers = reportingstaffmembers;
	}

	public List<OfficeStaffMember> getReportingstaffmembers() {
		return reportingstaffmembers;
	}

	public void setReportingstaffmembers(List<OfficeStaffMember> reportingstaffmembers) {
		this.reportingstaffmembers = reportingstaffmembers;
	}

	@Override
	public String toString() {
		return "Manager [reportingstaffmembers=" + reportingstaffmembers + "]";
	}
}