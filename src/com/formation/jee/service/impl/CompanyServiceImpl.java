package com.formation.jee.service.impl;

import java.util.List;

import com.formation.jee.dao.CompanyDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Company;
import com.formation.jee.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {

	CompanyDao companyDao;

	public CompanyServiceImpl() {
		companyDao = DaoManager.INSTANCE.getCompanyDao();
	}


	/* (non-Javadoc)
	 * @see com.formation.jee.service.impl.CompanyService#getCompanies()
	 */
	@Override
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}

	
	/* (non-Javadoc)
	 * @see com.formation.jee.service.impl.CompanyService#create(com.formation.jee.domain.Company)
	 */
	@Override
	public void create(Company company) {
		companyDao.create(company);
	}
}
