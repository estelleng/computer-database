package com.formation.jee.dao;

import java.util.List;

import com.formation.jee.domain.Computer;

public interface ComputerDao {

	List<Computer> getComputers();

	void create(Computer computer);

}