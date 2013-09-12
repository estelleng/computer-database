package com.formation.jee.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.formation.jee.dao.CompanyDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Company;

public class CompanyDaoImpl implements CompanyDao {

public CompanyDaoImpl(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.formation.jee.dao.impl.CompanyDao#getCompanies()
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {

		EntityManager em = null;

		List<Company> companies = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			companies = em.createNamedQuery("findAllCompanies").getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return companies;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Company getCompany(long companyId){
		EntityManager em = null;
		
		Company company = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//Ici on appelle la namedQuery declaree en annotation dans la classe domain.User
			System.out.println("coucou !!");
			company = (Company) em.createNamedQuery("findCompany").setParameter("company_id",companyId).getSingleResult();
			System.out.println("company name  "+company.getName());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		return company;
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.formation.jee.dao.impl.CompanyDao#create(com.formation.jee.domain.Company)
	 */

	@Override
	public void create(Company company) {
		EntityManager em = null;
			try {
				//Recuperation de l'entityManager qui gere la connexion a la BD
				em = DaoManager.INSTANCE.getEntityManager();
				//Debut de transaction (obligatoire pour des operations d'ecriture sur la BD)
				em.getTransaction().begin();
				
				//Sauvegarde de l'utilisateur
				em.persist(company);
				
				//Commit de la transaction = on applique toutes les operations ci dessus
				em.getTransaction().commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(em != null)
					em.close();
			}
	}
}
