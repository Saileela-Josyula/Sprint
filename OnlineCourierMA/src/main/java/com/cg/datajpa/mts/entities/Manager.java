package com.cg.datajpa.mts.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager extends OfficeStaffMember {

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="mid")
	private List<OfficeStaffMember> reportingstaffmembers;
	public Manager() {
		super();
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