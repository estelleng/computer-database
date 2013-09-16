package com.formation.jee.service;

import java.util.List;

import com.formation.jee.domain.Computer;

public interface ComputerService {

	public abstract List<Computer> getComputers();

	public abstract List<Computer> getComputersResearch(String valeurCherchee);

	Computer getComputer(long computerId);

	public abstract void create(Computer computer);

	public void deleteComputer(Computer computer);
	
	public void editComputer(Computer computer);

	public abstract List<Computer> getComputersPages(int nbElements, int currentPage);

}