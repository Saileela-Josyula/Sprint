package com.cg.mts.entities;

import java.time.LocalTime;
import java.util.List;

public class CourierOfficeOutlet {
	private int officeid;
	private Address address;
	private LocalTime openingTime;
	private LocalTime closingTime;	
	private List<OfficeStaffMember> staffmembers;
	
}
