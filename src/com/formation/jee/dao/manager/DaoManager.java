package com.formation.jee.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.formation.jee.dao.CompanyDao;
import com.formation.jee.dao.ComputerDao;
import com.formation.jee.dao.impl.CompanyDaoImpl;
import com.formation.jee.dao.impl.ComputerDaoImpl;


/**
 * Classe qui permet de g�rer les diff�rents DAO associ�s aux diff�rents objets du projet,
 * � savoir les "computer" et les "company".
 * @author Roxanne
 *
 */
public enum DaoManager {

	INSTANCE;

	private ComputerDao computerDao;
	private CompanyDao companyDao;
	private EntityManagerFactory emf;
	
	private DaoManager() {
		emf = Persistence.createEntityManagerFactory("epfPC");
		computerDao = new ComputerDaoImpl();
		companyDao = new CompanyDaoImpl();
	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	
	public EntityManager getEntityManager() {

		return emf.createEntityManager();
	}
}
