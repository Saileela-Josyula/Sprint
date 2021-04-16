package com.cg.datajpa.mts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.datajpa.mts.entities.Customer;

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
         
        manager.close();
        emf.close();
	}
}
