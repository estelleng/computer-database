package com.formation.jee.service;

import java.util.List;

import com.formation.jee.domain.Company;

public interface CompanyService {

	public abstract List<Company> getCompanies();

	public abstract void create(Company company);

}