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
	/**
	 * Cette méthode permet de récupérer la liste complète des ordinateurs,
	 * de façon à pouvoir l'afficher, ou effectuer des traitements.
	 * 
	 * @return la liste totale des ordinateurs de la base de données
	 */
	public List<Computer> getComputers() {

		EntityManager em = null;

		List<Computer> computers = null;

		try {
			// Récupération de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			//Sélection de tous les ordinateurs
			String query = "Select c From Computer c";

			Query requete = em.createQuery(query);

			computers = requete.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return computers;
	}

	/**
	 * Cette méthode permet de créer un ordinateur et de l'ajouter à la base de données
	 * 
	 * @param computer l'élément "ordinateur" à créer
	 */
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
	
	/**
	 * Cette méthode permet de modifier un ordinateur qui existe déjà dans la base de données
	 * et de changer une ou plusieurs de ses informations associées
	 * 
	 * @param computer l'élément "ordinateur" à modifier
	 */
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

	/**
	 * Cette méthode permet de supprimer un ordinateur de la base de données
	 * 
	 * @param computer l'élément "ordinateur" à supprimer
	 */
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
	
	/**
	 * Cette méthode permet de récupérer les ordinateurs qui correspondent à un 
	 * critère de recherche particulier
	 * 
	 * @param filter critère de recherche de l'ordinateur
	 * @return la liste des ordinateurs correspondant à la recherche
	 */
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

	/**
	 * Cette méthode permet de recher un ordinateur en particulier dans toute la base de données
	 * à partir de son identifiant
	 * 
	 * @param computerId identifiant de l'ordinateur à récupérer
	 * @return l'ordinateur recherché
	 */
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
	
	/**
	 * Cette méthode permet de récupérer les ordinateurs associés à une page particulière
	 * 
	 * @param nbElements nombre d'éléments à afficher par page
	 * @param currentPage page courante, ou numéro de la page dont il faut récupérer les ordinateurs
	 * @return la liste des ordinateurs de la page
	 */
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
			
			//Si on est positionné sur la première page
			if (currentPage == 1) {
				//Alors les ordinateurs à envoyer auront l'id qui va de 0 à nbElements
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

	/**
	 * Méthode qui permet de récupérer le nombre total d'ordinateurs figurant dans la base de données.
	 * 
	 * @return le nombre d'ordinateurs existants
	 */
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
