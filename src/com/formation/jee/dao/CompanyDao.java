package com.formation.jee.dao;

import java.util.List;

import com.formation.jee.domain.Company;

public interface CompanyDao {

	public abstract List<Company> getCompanies();

	public abstract void create(Company company);

	Company getCompany(long companyId);

}