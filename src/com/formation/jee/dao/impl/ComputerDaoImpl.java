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
	 * Cette m�thode permet de r�cup�rer la liste compl�te des ordinateurs,
	 * de fa�on � pouvoir l'afficher, ou effectuer des traitements.
	 * 
	 * @return la liste totale des ordinateurs de la base de donn�es
	 */
	public List<Computer> getComputers() {

		EntityManager em = null;

		List<Computer> computers = null;

		try {
			// R�cup�ration de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			//S�lection de tous les ordinateurs
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
	 * Cette m�thode permet de cr�er un ordinateur et de l'ajouter � la base de donn�es
	 * 
	 * @param computer l'�l�ment "ordinateur" � cr�er
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
	 * Cette m�thode permet de modifier un ordinateur qui existe d�j� dans la base de donn�es
	 * et de changer une ou plusieurs de ses informations associ�es
	 * 
	 * @param computer l'�l�ment "ordinateur" � modifier
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
	 * Cette m�thode permet de supprimer un ordinateur de la base de donn�es
	 * 
	 * @param computer l'�l�ment "ordinateur" � supprimer
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
	 * Cette m�thode permet de r�cup�rer les ordinateurs qui correspondent � un 
	 * crit�re de recherche particulier
	 * 
	 * @param filter crit�re de recherche de l'ordinateur
	 * @return la liste des ordinateurs correspondant � la recherche
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
	 * Cette m�thode permet de recher un ordinateur en particulier dans toute la base de donn�es
	 * � partir de son identifiant
	 * 
	 * @param computerId identifiant de l'ordinateur � r�cup�rer
	 * @return l'ordinateur recherch�
	 */
	@Override
	public Computer getComputer(long computerId) {
		EntityManager em = null;

		Computer computer = null;

		try {
			// Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			
			// On cr�e et on execute la requete ci-dessous
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
	 * Cette m�thode permet de r�cup�rer les ordinateurs associ�s � une page particuli�re
	 * 
	 * @param nbElements nombre d'�l�ments � afficher par page
	 * @param currentPage page courante, ou num�ro de la page dont il faut r�cup�rer les ordinateurs
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
			
			//Si on est positionn� sur la premi�re page
			if (currentPage == 1) {
				//Alors les ordinateurs � envoyer auront l'id qui va de 0 � nbElements
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
	 * M�thode qui permet de r�cup�rer le nombre total d'ordinateurs figurant dans la base de donn�es.
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
