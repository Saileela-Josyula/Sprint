package com.cg.datajpa.mts.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("manager")
public class Manager extends OfficeStaffMember {
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="manager")
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