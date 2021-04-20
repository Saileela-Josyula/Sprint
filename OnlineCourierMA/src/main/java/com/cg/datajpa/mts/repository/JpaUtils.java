package com.cg.datajpa.mts.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils
{
	private static EntityManagerFactory emf=null;
	private static EntityManager em=null;
	static
	{
		emf=Persistence.createEntityManagerFactory("JPA-PU");
	}
	public static EntityManager getEntityManager()
	{
		if(em==null || em.isOpen())
			em=emf.createEntityManager();
		return em;		
	}
}