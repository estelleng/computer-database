package com.formation.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.formation.jee.dao.ComputerDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Computer;

public class ComputerDaoImpl implements ComputerDao {

	public ComputerDaoImpl(){
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputers() {

		EntityManager em = null;

		List<Computer> computers = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			computers = em.createNamedQuery("findAllComputers").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return computers;
	}
	
	@Override
	public void create(Computer computer) {
		EntityManager em = null;
			try {
				//Recuperation de l'entityManager qui gere la connexion a la BD
				em = DaoManager.INSTANCE.getEntityManager();
				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
				em.getTransaction().begin();
				
				//Sauvegarde de l'utilisateur
				em.persist(computer);
				
				//Commit de la transaction = on applique toutes les operations ci dessus
				em.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputersResearch(String filter) {
		EntityManager em = null;

		List<Computer> computersResearch = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			computersResearch = em.createNamedQuery("findComputersResearch").setParameter("name", "%"+filter+"%").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return computersResearch;
	}
}
