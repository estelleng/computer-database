package com.formation.jee.dao;

import java.util.List;

import com.formation.jee.domain.Computer;

public interface ComputerDao {

	List<Computer> getComputers();

	void create(Computer computer);

	List<Computer> getComputersResearch(String valeurCherchee);
	
	Computer getComputer(long computerId);
	
	void deleteComputer(Computer computer);
	

}