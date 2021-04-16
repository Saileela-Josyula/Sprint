package com.cg.datajpa.mts.entities;

import java.time.LocalDate;

public class Courier {

	private int courierid;
	private CourierStatus status;
	private Customer sender;
	private Customer receiver;
	private int consignmentno;
	
	private LocalDate initiatedDate;
	private LocalDate deliveredDate;
}
