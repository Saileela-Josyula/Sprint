package com.cg.datajpa.mts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.datajpa.mts.entities.Complaint;
import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.entities.Customer;
import com.cg.datajpa.mts.entities.Manager;
import com.cg.datajpa.mts.entities.OfficeStaffMember;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPA-PU");
        EntityManager manager=emf.createEntityManager();
        EntityTransaction tran=manager.getTransaction();
        tran.begin();
        Customer c=manager.find(Customer.class,101);
        if(c!=null) {
        	System.out.println("---------xxxxxxxxxx-----------");
        	   System.out.println(c);
        	   System.out.println(c.getAddr());
        	   System.out.println(c.getAcct());
        	   System.out.println("---------xxxxxxxxxx-----------");
        }
        Courier courier=manager.find(Courier.class, 001);
        if(courier!=null)
        	System.out.println(courier);
        
        Complaint complaint=manager.find(Complaint.class, 401);
        if(complaint!=null)
        	System.out.println(complaint);
     
        CourierOfficeOutlet outlet=manager.find(CourierOfficeOutlet.class, 101);
        if(outlet!=null) {
        	System.out.println("--------zzzzzzzzzzzz--------");
        	System.out.println(outlet.getOfficeid());
        	System.out.println(outlet.getAddress());
        	System.out.println(outlet.getOpeningTime());
        	for(OfficeStaffMember o:outlet.getStaffmembers()) {
        		System.out.println(o);
        	}
        	
        	System.out.println("--------zzzzzzzzzzzz--------");
        	
        }
        OfficeStaffMember member=manager.find(OfficeStaffMember.class, 704);
        if(member!=null) {
        	System.out.println(member);
        }
        /*
        Manager m=manager.find(Manager.class, 201);
        if(m!=null) {
        	System.out.println("====vvvvvvv======");
        	for(OfficeStaffMember om:m.getReportingstaffmembers())
        		System.out.println(om);
        	
        }
        */
        manager.close();
        emf.close();
	}
}
