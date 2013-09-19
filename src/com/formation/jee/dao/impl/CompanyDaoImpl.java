package com.formation.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.formation.jee.dao.CompanyDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Company;

public class CompanyDaoImpl implements CompanyDao {

	public CompanyDaoImpl() {

	}

	/**
	 * M�thode qui permet de r�cup�rer toutes les entreprises figurant dans la base de donn�es.
	 * 
	 * @return la liste de toutes les entreprises
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {

		EntityManager em = null;

		List<Company> companies = null;

		try {
			//Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			//On cree et on execute la requete
			String query = "Select c From Company c";
			companies = em.createQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return companies;
	}
	
	/**
	 * M�thode qui permet de r�cup�rer une compagnie en particulier, en fonction de son identifiant.
	 * 
	 * @param companyId identifiant de l'entreprise � r�cup�rer
	 * @return l'objet "company" recherch�
	 */
	@Override
	public Company getCompany(long companyId) {
		EntityManager em = null;

		Company company = null;

		try {
			//Recuperation de l'EntityManager
			em = DaoManager.INSTANCE.getEntityManager();
			// On cree et on execute la requete ci-dessous
			String query = "Select c From Company c WHERE c.id= :id";
			company = (Company) em.createQuery(query)
					.setParameter("id", companyId).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return company;

	}

	/**
	 *M�thode qui permet de cr�er une nouvelle entreprise et de l'ajouter dans la base de donn�es.
	 *
	 * @param company l'objet "company" � cr�er et � ajouter � la base de donn�es
	 */
	@Override
	public void create(Company company) {
		EntityManager em = null;
		try {
			// Recuperation de l'entityManager qui gere la connexion a la BD
			em = DaoManager.INSTANCE.getEntityManager();
			// Debut de la transaction
			em.getTransaction().begin();

			// Sauvegarde de la compagnie
			em.persist(company);

			// Commit de la transaction
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}
}
