package com.formation.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.formation.jee.dao.ComputerDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Computer;

public class ComputerDaoImpl implements ComputerDao {

	public ComputerDaoImpl() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputers() {

		EntityManager em = null;
		int nbElementsPage = 25;
		int pageNumber = 0;
		List<Computer> computers = null;

		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();

			String query = "Select c From Computer c";

			Query requete = em.createQuery(query);

			requete.setFirstResult(pageNumber * nbElementsPage);
			requete.setMaxResults((pageNumber + 1) * nbElementsPage);
			computers = requete.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return computers;
	}

	@Override
	public void create(Computer computer) {
		EntityManager em = null;
		try {
			// Recuperation de l'EntityManager qui gere la connexion a la BD
			em = DaoManager.INSTANCE.getEntityManager();
			// Debut de la transaction
			em.getTransaction().begin();

			// Sauvegarde de l'ordinateur
			em.persist(computer);

			// Commit de la transaction
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void editComputer(Computer computer) {
		EntityManager em = null;
		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();

			// Debut de la transaction
			em.getTransaction().begin();

			// On cherche l'ordinateur que l'on souhaite editer et on modifie
			// les champs avec les infos saisies par l'utilisateur
			Computer c = em.find(Computer.class, computer.getId());
			c.setName(computer.getName());
			c.setIntroduced(computer.getIntroduced());
			c.setDiscontinued(computer.getDiscontinued());
			c.setCompany(computer.getCompany());

			// Commit de la transaction
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}

	public void deleteComputer(Computer computer) {
		EntityManager em = null;
		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			// Debut de transaction
			em.getTransaction().begin();
			computer = em.merge(computer);

			// Suppression de l'ordinateur
			em.remove(computer);

			// Commit de la transaction
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputersResearch(String filter) {
		EntityManager em = null;

		List<Computer> computersResearch = null;

		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();

			// On cree et on execute la requete ci-dessous
			String query = "Select c From Computer c WHERE c.name LIKE :name";
			computersResearch = em.createQuery(query)
					.setParameter("name", "%" + filter + "%").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return computersResearch;
	}

	@Override
	public Computer getComputer(long computerId) {
		EntityManager em = null;

		Computer computer = null;

		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			
			// On crée et on execute la requete ci-dessous
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputersPages(int nbElements, int currentPage) {

		EntityManager em = null;

		List<Computer> computers = null;

		try {
			//Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();

			String query = "Select c From Computer c";

			Query requete = em.createQuery(query);

			if (currentPage == 1) {
				requete.setFirstResult(currentPage - 1);
				requete.setMaxResults(nbElements);

			} else {

				requete.setFirstResult((currentPage - 1) * nbElements - 1);
				requete.setMaxResults(nbElements);

			}

			computers = requete.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return computers;

	}

	@Override
	public int getComputerCount() {

		EntityManager em = null;

		int count = 0;

		try {
			//Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();

			String query = "Select c From Computer c";
			//On cree la requete
			Query requete = em.createQuery(query);

			//On recupere le nombre d'ordinateurs
			count = requete.getResultList().size();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return count;

	}
}
