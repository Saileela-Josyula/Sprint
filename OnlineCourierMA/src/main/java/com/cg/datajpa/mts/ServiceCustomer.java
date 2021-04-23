package com.cg.datajpa.mts;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.repository.ComplaintDaoImpl;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.service.CustomerServiceImp;

public class ServiceCustomer {

	public static void main(String[] args) {
		CustomerServiceImp serviceCustomer=new CustomerServiceImp();
		serviceCustomer.setC(new CourierDAOImp());
		serviceCustomer.setCdoa(new ComplaintDaoImpl());
		/*
		try {
			serviceCustomer.initiateProcess(4);
		}
		catch(CourierNotFoundException ex) {
			
		}
		try {
			System.out.println(serviceCustomer.checkOnlineTrackingStatus(4));
		}
		catch(CourierNotFoundException ex) {
			
		}
		
		try {
			System.out.println(serviceCustomer.checkOnlineTrackingStatus(2));
		}
		catch(CourierNotFoundException ex) {
			
		}
		*/
		Complaint complaint=new Complaint(701,"Missing Products","All products were not there,something are missing",null);
		serviceCustomer.registerComplaint(complaint);
		
	
	}
}
