package com.formation.jee.service;

import java.util.List;

import com.formation.jee.domain.Computer;

public interface ComputerService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.formation.jee.service.impl.UserService#getUsers()
	 */
	public abstract List<Computer> getComputers();

	public abstract void create(Computer computer);

}