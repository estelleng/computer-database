package com.formation.jee.service.impl;

import java.util.List;

import com.formation.jee.dao.ComputerDao;
import com.formation.jee.dao.manager.DaoManager;
import com.formation.jee.domain.Computer;
import com.formation.jee.service.ComputerService;

public class ComputerServiceImpl implements ComputerService {

	ComputerDao computerDao;

	public ComputerServiceImpl() {
		computerDao = DaoManager.INSTANCE.getComputerDao();
	}


	@Override
	public List<Computer> getComputers() {
		return computerDao.getComputers();
	}

	
	@Override
	public void create(Computer computer) {
		computerDao.create(computer);
	}

	@Override
	public void deleteComputer(Computer computer){
		computerDao.deleteComputer(computer);
	}
	
	@Override
	public void editComputer(Computer computer){
		computerDao.editComputer(computer);
	}

	@Override
	public List<Computer> getComputersResearch(String filter) {
		return computerDao.getComputersResearch(filter);
	}

	@Override
	public Computer getComputer(long computerId) {
		// TODO Auto-generated method stub
		return computerDao.getComputer(computerId);
	}
}
