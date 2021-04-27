package com.cg.datajpa.mts.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory entitymanagerfactory = null;
	private static EntityManager entitymanager = null;
	static {
		entitymanagerfactory = Persistence.createEntityManagerFactory("JPA-PU");
	}

	public static EntityManager getEntityManager() {
		if (entitymanager == null || entitymanager.isOpen())
			entitymanager = entitymanagerfactory.createEntityManager();
		return entitymanager;
	}
}