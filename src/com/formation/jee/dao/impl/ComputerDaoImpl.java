package com.formation.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		int nbElementsPage = 25;
		int pageNumber=0;
		List<Computer> computers = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			String query = "Select c From Computer c";
			
			Query requete = em.createQuery(query);
			
			requete.setFirstResult(pageNumber*nbElementsPage);
			requete.setMaxResults((pageNumber+1)*nbElementsPage);
			computers = requete.getResultList();
			
			//computers = em.createQuery(query).getResultList();
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
	
	public void deleteComputer(Computer computer){
		EntityManager em = null;
		try {
			//Recuperation de l'entityManager qui gere la connexion a la BD
			em = DaoManager.INSTANCE.getEntityManager();
			//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
			em.getTransaction().begin();
			
			computer= em.merge(computer);
			
			//Suppression de l'ordinateur
			em.remove(computer);
			
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
			String query= "Select c From Computer c WHERE c.name LIKE :name";
			computersResearch = em.createQuery(query).setParameter("name", "%"+filter+"%").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return computersResearch;
	}

	@Override
	public Computer getComputer(long computerId) {
		EntityManager em = null;

		Computer computer = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			// Ici on appelle la namedQuery declaree en annotation dans la
			// classe domain.User
			String query = "Select c From Computer c WHERE c.id= :id";
			computer = (Computer) em.createQuery(query)
					.setParameter("id", computerId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return computer;
		
	}
}
